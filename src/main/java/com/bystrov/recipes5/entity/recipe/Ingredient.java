package com.bystrov.recipes5.entity.recipe;

import jakarta.persistence.*;
import lombok.Data;



@Entity
@Table(name = "ingredients", schema = "recipes2")
@Data
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ingredient_id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "quantity")
    private double quantity;

    @Column(name = "unit_of_measurement")
    @Enumerated(EnumType.STRING)
    private UnitOfMeasurement unit;

    @Override
    public String toString() {
        String string = name + ": " + quantity + " " + unit;

        return string;
    }
}
