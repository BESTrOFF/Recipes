package com.bystrov.recipes5.entity.dto;

import com.bystrov.recipes5.entity.recipe.Ingredient;
import lombok.Data;

import java.util.List;

@Data
public class RecipeDto {
    private String name;

    private List<Ingredient> ingredients;

    private String recipeDescription;

    private String author;

    public String getIngredientsAsString() {
        StringBuilder stringBuilder = new StringBuilder();

        for (Ingredient ingredient : ingredients) {
            stringBuilder.append(ingredient.toString() + "\n");
        }

        return stringBuilder.toString();
    }
}
