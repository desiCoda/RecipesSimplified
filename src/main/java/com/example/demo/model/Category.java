package com.example.demo.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Category {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long catgID;
	private String description;
	@ManyToMany(mappedBy="categories") //catagories is the property in table recipe that maps to the recipes prop below.
	private Set<Recipe> recipes;
	public Long getCatgID() {
		return catgID;
	}
	public void setCatgID(Long catgID) {
		this.catgID = catgID;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Set<Recipe> getRecipe() {
		return recipes;
	}
	public void setRecipe(Set<Recipe> recipes) {
		this.recipes = recipes;
	}
	
	
}
