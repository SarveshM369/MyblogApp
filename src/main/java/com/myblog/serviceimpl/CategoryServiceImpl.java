package com.myblog.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myblog.entities.Category;
import com.myblog.exceptions.ResourceNotFoundException;
import com.myblog.payloads.CategoryDto;
import com.myblog.repository.CategoryRepository;
import com.myblog.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	

	@Autowired
	private CategoryRepository categoryRepo;
	
	@Autowired
	private ModelMapper modelMapper; 
	
	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
     Category cat = this.modelMapper.map(categoryDto, Category.class);
	 Category addedCat = this.categoryRepo.save(cat);
		
		return this.modelMapper.map(addedCat, CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Long categoryId) {
		
     Category cat = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "Category Id", categoryId));
		
     cat.setCategoryTitle(categoryDto.getCategoryTitle());
     cat.setCategoryDescription(categoryDto.getCategoryDescription());
     
     Category Updatedcat = this.categoryRepo.save(cat);
     
		return this.modelMapper.map(Updatedcat, CategoryDto.class) ;
	}
  
	@Override
	public void deleteCategory(Long categoryId) {
            Category cat = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "category id", categoryId));
	        this.categoryRepo.delete(cat);
            
	}

	@Override
	public CategoryDto getCategory(Long categoryId) {
       Category cat = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "category id", categoryId));
	
		return this.modelMapper.map(cat, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getCategories() {
              List<Category> categories = this.categoryRepo.findAll() ;
              List<CategoryDto> catDtos = categories.stream().map((cat)-> this.modelMapper.map(cat, CategoryDto.class)).collect(Collectors.toList());
		
		
		return catDtos;
	}
	
}