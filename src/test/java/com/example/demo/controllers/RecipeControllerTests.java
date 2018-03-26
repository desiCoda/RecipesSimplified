package com.example.demo.controllers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcProperties.View;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import com.example.demo.commands.RecipeCommand;
import com.example.demo.model.Recipe;
import com.example.demo.services.RecipeService;

public class RecipeControllerTests {

	@Mock
	RecipeService recipeService;
	RecipeController recipeController;
	@Mock
	Model model; // mocked Model interface

	@Before
	public void setup() throws Exception {
		MockitoAnnotations.initMocks(this);
		recipeController = new RecipeController(recipeService);

	}

	@Test
	public void testGetRecipe() throws Exception {
		Recipe recipe = new Recipe();
		recipe.setRecipeID(1L);
		when(recipeService.findById(Mockito.anyLong())).thenReturn(recipe);

		MockMvc mockMVC = MockMvcBuilders.standaloneSetup(recipeController).build();
		mockMVC.perform(get("/recipe/1/show")).andExpect(status().isOk()).andExpect(view().name("recipe/show"))
		.andExpect(MockMvcResultMatchers.model().attributeExists("recipe"));
	}

	@Test
	public void testPostNewRecipeForm() throws Exception {
		RecipeCommand command = new RecipeCommand();
		command.setRecipeID(2L);
		when(recipeService.saveRecipeCommand(Mockito.any())).thenReturn(command);

		MockMvc mockMVC = MockMvcBuilders.standaloneSetup(recipeController).build();
		mockMVC.perform(post("/recipe")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("recipeID","")
				.param("description", "anydesc")
				).andExpect(status().is3xxRedirection())
		.andExpect(view().name("redirect:/recipe/2/show"));
		
	}

}
