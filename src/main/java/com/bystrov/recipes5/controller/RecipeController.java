package com.bystrov.recipes5.controller;

import com.bystrov.recipes5.entity.recipe.Recipe;
import com.bystrov.recipes5.entity.dto.RecipeDto;
import com.bystrov.recipes5.service.IngredientService;
import com.bystrov.recipes5.service.RecipeService;
import com.bystrov.recipes5.util.RecipeRecipeDto;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@AllArgsConstructor
public class RecipeController {
    private final RecipeRecipeDto recipeRecipeDto;
    private final RecipeService recipeService;
    private final IngredientService ingredientService;

    @GetMapping("/")
    public String namePage(Model model,
                           @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                           @RequestParam(value = "limit", required = false, defaultValue = "10") int limit) {

        List<String> names = recipeService.getNamesPage(page, limit);

        int pageCount = (int) Math.ceil(1.0 * recipeService.getAllCount() / limit);

        List<Integer> pages = new ArrayList<>();

        for (int i = 1; i <= pageCount; i++) {
            pages.add(i);
        }

        model.addAttribute("names", names);
        model.addAttribute("pages", pages);
        model.addAttribute("limit", limit);

        return "main";
    }

    @GetMapping("/recipe/{name}")
    public String recipe(@PathVariable(value = "name") String name, Model model) {

        RecipeDto recipe = recipeRecipeDto.toDto(recipeService.getRecipeByName(name));

        model.addAttribute("recipe", recipe);

        return "recipe";
    }

    @GetMapping("/create")
    public String toCreateForm(Model model) {
        RecipeDto recipe = new RecipeDto();

        model.addAttribute("dto", recipe);

        return "recipeForm";
    }

    @PostMapping("/saveRecipe")
    public String create(@ModelAttribute("dto") RecipeDto dto,
                         @AuthenticationPrincipal UserDetails userDetails) {

        dto.setAuthor(userDetails.getUsername());
        Recipe recipe = recipeRecipeDto.toRecipe(dto);

        ingredientService.saveIngredients(recipe.getIngredients());

        recipeService.save(recipe);

        return "redirect:/";
    }

    @GetMapping("/search")
    public String search(Model model) {
        String name = "";

        model.addAttribute("name", name);

        return "search";
    }

    @PostMapping("/search")
    public String find(@RequestParam(value = "page", required = false, defaultValue = "1") int page,
                       @RequestParam(value = "limit", required = false, defaultValue = "10") int limit,
                       @RequestParam(value = "name", required = true, defaultValue = "") String name,
                       Model model) {

        List<String> names = recipeService.getRecipesNamesByName(name);

        int pageCount = (int) Math.ceil(1.0 * recipeService.countLikeName(name) / limit);

        List<Integer> pages = new ArrayList<>();

        for (int i = 1; i <= pageCount; i++) {
            pages.add(i);
        }

        model.addAttribute("names", names);
        model.addAttribute("pages", pages);

        return "main";
    }
}