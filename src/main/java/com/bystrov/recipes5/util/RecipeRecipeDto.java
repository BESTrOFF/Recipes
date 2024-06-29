package com.bystrov.recipes5.util;

import com.bystrov.recipes5.entity.dto.RecipeDto;
import com.bystrov.recipes5.entity.recipe.Recipe;
import com.bystrov.recipes5.service.UserService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class RecipeRecipeDto {
    private final UserService userService;

    public RecipeDto toDto(Recipe recipe) {
        RecipeDto dto = new RecipeDto();

        dto.setName(recipe.getName());
        dto.setIngredients(recipe.getIngredients());
        dto.setRecipeDescription(recipe.getRecipeDescription());
        dto.setAuthor(recipe.getAuthorName());

        return dto;
    }

    public Recipe toRecipe(RecipeDto dto) {
        Recipe recipe = new Recipe();

        recipe.setName(dto.getName());
        recipe.setIngredients(dto.getIngredients());
        recipe.setRecipeDescription(dto.getRecipeDescription());
        recipe.setAuthor(userService.getUserByName(dto.getAuthor()));

        return recipe;
    }
}
