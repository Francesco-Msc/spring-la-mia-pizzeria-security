package org.lessons.java.spring_la_mia_pizzeria_relazioni.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "offers")
public class Offer {
    
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "The offer must have a name")
    private String title;

    @NotNull (message = "The offer must have a starting date")
    @FutureOrPresent (message = "The offer starting date cannot be in the past")
    private LocalDate startDate;

    @NotNull(message = "The offer must have an ending date")
    @FutureOrPresent (message = "The offer ending date cannot be in the past")
    private LocalDate endDate;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "pizza_id", nullable = false)
    private Pizza pizza;

    public Pizza getPizza() {
        return this.pizza;
    }

    public void setPizza(Pizza pizza) {
        this.pizza = pizza;
    }
    
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getTitle() {
        return this.title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public LocalDate getStartDate() {
        return this.startDate;
    }
    
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
    
    public LocalDate getEndDate() {
        return this.endDate;
    }
    
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}
