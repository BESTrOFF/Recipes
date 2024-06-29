package com.bystrov.recipes5.service;


import com.bystrov.recipes5.entity.recipe.Ingredient;
import com.bystrov.recipes5.entity.recipe.Recipe;
import com.bystrov.recipes5.entity.recipe.UnitOfMeasurement;
import com.bystrov.recipes5.repository.IngredientRepository;
import com.bystrov.recipes5.repository.RecipeRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class RecipeService {
    private final RecipeRepository recipeRepository;
    private final EntityManager entityManager;

//    String jpql = "SELECT r FROM Recipe r WHERE SIZE(r.ingredients) = :ingredientCount AND r.ingredients IN :ingredients";
//    List<Recipe> recipes = entityManager.createQuery(jpql, Recipe.class)
//            .setParameter("ingredientCount", ingredients.size())
//            .setParameter("ingredients", ingredients)
//            .getResultList();


    @Transactional
    public List<String> getNamesPage(int page, int limit) {
        String query = "SELECT r.name FROM Recipe r ORDER BY r.id";
        TypedQuery<String> typedQuery = entityManager.createQuery(query, String.class);

        if (page == 1) {
            typedQuery.setFirstResult(page - 1);
        } else {
            typedQuery.setFirstResult(page * limit - limit);
        }

        typedQuery.setMaxResults(limit);

        List<String> names = typedQuery.getResultList();

        return names;
    }

    @Transactional
    public Recipe getRecipeByName(String name) {
        return recipeRepository.getRecipeByName(name);
    }

    @Transactional
    public List<String> getRecipesNamesByName(String name) {
        return recipeRepository.getRecipesNamesByName(name);
    }


    @Transactional
    public long getAllCount() {
        return recipeRepository.count();
    }


    @Transactional
    public void save(Recipe recipe) {
        if (!recipe.getName().isEmpty() && !recipe.getRecipeDescription().isEmpty()) {
            Recipe check = recipeRepository.getRecipeByName(recipe.getName());

            if (check == null) {
                recipeRepository.save(recipe);
            }
        }
    }

    @Transactional
    public Integer countLikeName(String name) {
        return recipeRepository.countByNameLike(name);
    }
}
