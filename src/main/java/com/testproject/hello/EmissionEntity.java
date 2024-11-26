package com.testproject.hello;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "emissions")
public class EmissionEntity {

    @Id
    @Column(name = "emission_id")	
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "category")
    private String category;

    @Column(name = "year")
    private Integer year;

    @Column(name = "scenario")
    private String scenario;

    @Column(name = "gas_units")
    private String gas_units;

    @Column(name = "predicted_value")
    private Double predicted_value;

    @Column(name = "actual_value")
    private Double actual_value;

    @Column(name = "description")
    private String description;

    public EmissionEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getScenario() {
        return scenario;
    }

    public void setScenario(String scenario) {
        this.scenario = scenario;
    }

    public String getGas_units() {
        return gas_units;
    }

    public void setGas_units(String gas_units) {
        this.gas_units = gas_units;
    }

    public Double getPredicted_value() {
        return predicted_value;
    }

    public void setPredicted_value(Double predicted_value) {
        this.predicted_value = predicted_value;
    }

    public Double getActual_value() {
        return actual_value;
    }

    public void setActual_value(Double actual_value) {
        this.actual_value = actual_value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    

    


    

}
