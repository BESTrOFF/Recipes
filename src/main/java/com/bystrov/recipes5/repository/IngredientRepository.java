package com.bystrov.recipes5.repository;

import com.bystrov.recipes5.entity.recipe.Ingredient;
import com.bystrov.recipes5.entity.recipe.UnitOfMeasurement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Integer> {

    @Query("select i from Ingredient i where i.name = ?1 and i.quantity = ?2 and i.unit = ?3")
    Ingredient getIngredientByNameAndQuantityAndUnit(String name, Double quantity, UnitOfMeasurement unit);
}
