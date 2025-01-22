
package com.estuate.Employee_Performance.entity;

import jakarta.persistence.*;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    // Reference to the 'category' primary key of RatingCategory entity
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rating_category", referencedColumnName = "category")
    private RatingCategory ratingCategory;

    public Employee() {
    }

    public Employee(int id, String name, RatingCategory ratingCategory) {
        this.id = id;
        this.name = name;
        this.ratingCategory = ratingCategory;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RatingCategory getRatingCategory() {
        return ratingCategory;
    }

    public void setRatingCategory(RatingCategory ratingCategory) {
        this.ratingCategory = ratingCategory;
    }
}
