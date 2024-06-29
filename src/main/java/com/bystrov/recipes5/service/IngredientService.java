package com.bystrov.recipes5.service;


import com.bystrov.recipes5.entity.recipe.Ingredient;
import com.bystrov.recipes5.repository.IngredientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class IngredientService {
    private final IngredientRepository repository;

    @Transactional
    public void save(Ingredient ingredient) {
        repository.save(ingredient);
    }

    @Transactional
    public void saveIngredients(List<Ingredient> ingredients) {
        Ingredient check;

        for (int i = 0; i < ingredients.size(); i++) {
            if (!ingredients.get(i).getName().isEmpty() && ingredients.get(i).getQuantity() > 0) {
                check = repository.getIngredientByNameAndQuantityAndUnit(ingredients.get(i).getName(), ingredients.get(i).getQuantity(), ingredients.get(i).getUnit());

                if (check == null) {
                    ingredients.set(i, repository.save(ingredients.get(i)));
                } else {
                    ingredients.set(i, check);
                }
            }
        }
    }
}
