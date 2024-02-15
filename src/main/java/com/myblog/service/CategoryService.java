package com.myblog.service;

import java.util.List;

import com.myblog.payloads.CategoryDto;

public interface CategoryService {
	
	    //create
		 CategoryDto createCategory(CategoryDto categoryDto);
		 
		 //update
		 CategoryDto updateCategory(CategoryDto categoryDto, Long categoryId);
		 
		  //get All
	     List<CategoryDto> getCategories();
		 
		 
		 //delete
		  void deleteCategory(Long  categoryId);
		 
		 //get
	     CategoryDto getCategory(Long categoryId);
	     
	   

}
