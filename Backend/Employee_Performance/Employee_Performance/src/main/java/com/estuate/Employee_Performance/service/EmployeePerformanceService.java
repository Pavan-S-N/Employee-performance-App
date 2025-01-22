//package com.estuate.Employee_Performance.service;
//
//import com.estuate.Employee_Performance.entity.Employee;
//import com.estuate.Employee_Performance.entity.RatingCategory;
//import com.estuate.Employee_Performance.repository.EmployeeRepo;
//import com.estuate.Employee_Performance.repository.RatingCategoryRepo;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//import java.util.stream.Collectors;
//
//@Service
//public class EmployeePerformanceService {
//
//    @Autowired
//    private EmployeeRepo employeeRepo;
//
//    @Autowired
//    private RatingCategoryRepo ratingCategoryRepo;
//
//    private static final double ADJUSTMENT_THRESHOLD = 5.0; // Threshold for adjustment, can be adjusted
//
//    public Map<String, Double> calculateActualPercentages() {
//        List<Employee> employees = employeeRepo.findAll();  // Get all employees
//        System.out.println("Employees: " + employees);
//        if (employees.isEmpty()) {
//            return Map.of(); // Return an empty map if no employees
//        }
//
//        Map<String, Long> counts = employees.stream()
//                .filter(employee -> {
//                    boolean valid = employee.getRatingCategory() != null && employee.getRatingCategory().getCategory() != null;
//                    System.out.println("Employee: " + employee + ", Valid: " + valid);
//                    return valid;
//                })
//                .collect(Collectors.groupingBy(
//                        employee -> employee.getRatingCategory().getCategory(), // Get category name
//                        Collectors.counting() // Count employees in each category
//                ));
//
//        System.out.println("Counts: " + counts);
//
//        long totalEmployees = employees.size();// Get total number of employees
//        System.out.println("Total Employees: " + totalEmployees);
//
//        // Calculate percentage for each category
//        return counts.entrySet().stream()
//                .collect(Collectors.toMap(
//                        Map.Entry::getKey, // Category name
//                        entry -> (entry.getValue() * 100.0) / totalEmployees // Percentage calculation
//                ));
//    }
//
//    public List<String> suggestRatingAdjustments() {
//        Map<String, Double> actualPercentages = calculateActualPercentages();
//        List<RatingCategory> categories = ratingCategoryRepo.findAll();
//
//        List<String> adjustments = new ArrayList<>();
//        for (RatingCategory category : categories) {
//            double actual = actualPercentages.getOrDefault(category.getCategory(), 0.0);
//            double deviation = actual - category.getStandardPercentage();
//
//            System.out.println("Category: " + category.getCategory() + ", Actual: " + actual + ", Deviation: " + deviation);
//
//            if (Math.abs(deviation) > ADJUSTMENT_THRESHOLD) {
//                adjustments.add("Adjust category " + category.getCategory() + " by " + deviation + "%");
//            }
//        }
//
//        return adjustments;
//    }
//}
//

package com.estuate.Employee_Performance.service;


import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.estuate.Employee_Performance.entity.Employee;
import com.estuate.Employee_Performance.entity.RatingCategory;
import com.estuate.Employee_Performance.repository.EmployeeRepo;
import com.estuate.Employee_Performance.repository.RatingCategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class EmployeePerformanceService {

    private static final Logger logger = LoggerFactory.getLogger(EmployeePerformanceService.class);

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private RatingCategoryRepo ratingCategoryRepo;

    public Employee createEmployee(Employee employee) {
        return employeeRepo.save(employee);
    }

    public void createEmployees(List<Employee> employees) {
        employeeRepo.saveAll(employees);
    }

    public List<Employee> getAllEmployees() {
        return employeeRepo.findAll();
    }

    public Map<String, Double> calculateActualPercentages() {
        List<Employee> employees = employeeRepo.findAll();
        if (employees.isEmpty()) {
            logger.info("No employees found in the database.");
            return Map.of();
        }

        // Log employees and their rating categories
        employees.forEach(employee ->
                logger.info("Employee: " + employee.getName() + ", Rating Category: " + employee.getRatingCategory())
        );

        Map<String, Long> counts = employees.stream()
                .filter(employee -> employee.getRatingCategory() != null) // Filtering out nulls
                .collect(Collectors.groupingBy(
                        employee -> employee.getRatingCategory().getCategory(), // Accessing the 'category' field inside RatingCategory
                        Collectors.counting()
                ));

        if (counts.isEmpty()) {
            logger.info("No valid rating categories found.");
        }

        long totalEmployees = employees.size();

        return counts.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> (entry.getValue() * 100.0) / totalEmployees
                ));
    }


    public List<String> suggestRatingAdjustments() {
        Map<String, Double> actualPercentages = calculateActualPercentages();
        List<RatingCategory> categories = ratingCategoryRepo.findAll();

        List<String> adjustments = new ArrayList<>();
        for (RatingCategory category : categories) {
            double actual = actualPercentages.getOrDefault(category.getCategory(), 0.0);
            double deviation = actual - category.getStandardPercentage();
            adjustments.add("Adjust category " + category.getCategory() + " by " + deviation + "%");
        }
        return adjustments;
    }
}
