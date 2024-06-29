package com.bystrov.recipes5.entity.recipe;


import com.bystrov.recipes5.entity.user.User;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import jakarta.persistence.*;

import javax.swing.*;
import java.util.List;

@Entity
@Table(name = "recipes", schema = "recipes2")
@Data
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recipe_id")
    private int id;

    @Column(name = "recipe_name")
    private String name;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "recipe_ingredient",
            joinColumns = @JoinColumn(name = "recipe_id", referencedColumnName = "recipe_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id", referencedColumnName = "ingredient_id"))
    private List<Ingredient> ingredients;

    @Column(name = "recipe")
    private String recipeDescription;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User author;

    public String getAuthorName() {
        return this.author.getName();
    }
}
