package com.bystrov.recipes5.repository;

import com.bystrov.recipes5.entity.recipe.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Integer> {

    @Query("select r from Recipe r where r.name = ?1")
    Recipe getRecipeByName(String name);

    @Query("select r.name from Recipe r where r.name like CONCAT('%', ?1, '%')")
    List<String> getRecipesNamesByName(String name);

    @Query("select count(r) from Recipe r where r.name like CONCAT('%', ?1, '%')")
    Integer countByNameLike(String name);
}
