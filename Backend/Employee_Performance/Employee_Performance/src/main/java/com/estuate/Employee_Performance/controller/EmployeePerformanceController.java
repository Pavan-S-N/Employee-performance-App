package com.estuate.Employee_Performance.controller;

import com.estuate.Employee_Performance.service.EmployeePerformanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/performance")
public class EmployeePerformanceController {

    @Autowired
    private EmployeePerformanceService employeeperformanceService;

    @CrossOrigin(origins = "http://localhost:5173/")
    @GetMapping("/actual_percentages")
    public Map<String, Double> getActualPercentages() {
        return employeeperformanceService.calculateActualPercentages();
    }

    @CrossOrigin(origins = "http://localhost:5173/")
    @GetMapping("/deviations")
    public List<String> getRatingAdjustments() {
        return employeeperformanceService.suggestRatingAdjustments();
    }
}


//
//package com.estuate.Employee_Performance.controller;
//
//import com.estuate.Employee_Performance.entity.Employee;
//import com.estuate.Employee_Performance.service.EmployeePerformanceService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.Map;
//
//@RestController
//@RequestMapping("/performance")
//public class EmployeePerformanceController {
//
//    @Autowired
//    private EmployeePerformanceService employeePerformanceService;
//
//    @CrossOrigin(origins = "http://localhost:5173/")
//    @PostMapping("/add")
//    public String addEmployee(@RequestBody Employee employee) {
//        employeePerformanceService.createEmployee(employee);
//        return "Employee added successfully!";
//    }
//
//    @CrossOrigin(origins = "http://localhost:5173/")
//    @PostMapping("/add-all")
//    public String addAllEmployees(@RequestBody List<Employee> employees) {
//        employeePerformanceService.createEmployees(employees);
//        return "All employees added successfully!";
//    }
//
//    @CrossOrigin(origins = "http://localhost:5173/")
//    @GetMapping("/actual_percentages")
//    public Map<String, Double> getActualPercentages() {
//        return employeePerformanceService.calculateActualPercentages();
//    }
//
//    @CrossOrigin(origins = "http://localhost:5173/")
//    @GetMapping("/deviations")
//    public List<String> getRatingAdjustments() {
//        return employeePerformanceService.suggestRatingAdjustments();
//    }
//
//    @CrossOrigin(origins = "http://localhost:5173/")
//    @GetMapping("/employees")
//    public List<Employee> getAllEmployees() {
//        return employeePerformanceService.getAllEmployees();
//    }
//}
//
