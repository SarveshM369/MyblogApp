package com.myblog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myblog.payloads.ApiResponse;
import com.myblog.payloads.CategoryDto;
import com.myblog.service.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
	
	@Autowired
	private CategoryService CategoryService;
	

	//create
	
	@PostMapping()
	public ResponseEntity<CategoryDto> createCategory(@Valid  @RequestBody CategoryDto categoryDto){
		CategoryDto createCategory = this.CategoryService.createCategory(categoryDto);
		
		return new ResponseEntity<CategoryDto>(createCategory,HttpStatus.CREATED);
	}
	
	//update
	@PutMapping("/{catId}")
	public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto, @PathVariable Long catId){
		CategoryDto updateCategory = this.CategoryService.updateCategory(categoryDto, catId);
		
		return new ResponseEntity<CategoryDto>(updateCategory,HttpStatus.OK);
	}
	
	//delete
	
	@DeleteMapping("/{catId}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Long catId){
	          this.CategoryService.deleteCategory(catId);
		  return new ResponseEntity<ApiResponse>(new ApiResponse("category is deleted successfully !!", true),HttpStatus.OK);
	}
	
	//get
	
	@GetMapping("/{catId}")
	public ResponseEntity<CategoryDto> getCategory(@PathVariable Long catId){
        CategoryDto categoryDto = this.CategoryService.getCategory(catId);
	  return new ResponseEntity<CategoryDto>(categoryDto ,HttpStatus.OK);
}
	
	//getAll
	@GetMapping()
	public ResponseEntity<List<CategoryDto>> getCategories(){
         List<CategoryDto> categories = this.CategoryService.getCategories();
         return  ResponseEntity.ok(categories);
}
	
	
}
