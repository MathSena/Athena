package com.mathsena.transactionservice.service;

import com.mathsena.transactionservice.dto.CategoryDTO;
import com.mathsena.transactionservice.exception.ResourceNotFoundException;
import com.mathsena.transactionservice.model.Category;
import com.mathsena.transactionservice.model.TransactionType;
import com.mathsena.transactionservice.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

  private final CategoryRepository categoryRepository;

  public CategoryService(CategoryRepository categoryRepository) {
    this.categoryRepository = categoryRepository;
  }

  @Transactional
  public CategoryDTO createCategory(CategoryDTO dto) {
    Category category = new Category(
        dto.getName(),
        dto.getType(),
        dto.getIcon(),
        dto.getColor()
    );

    Category savedCategory = categoryRepository.save(category);
    return mapToDTO(savedCategory);
  }

  @Transactional(readOnly = true)
  public List<CategoryDTO> getAllCategories() {
    return categoryRepository.findAll().stream()
        .map(this::mapToDTO)
        .collect(Collectors.toList());
  }

  @Transactional(readOnly = true)
  public List<CategoryDTO> getCategoriesByType(TransactionType type) {
    return categoryRepository.findByType(type).stream()
        .map(this::mapToDTO)
        .collect(Collectors.toList());
  }

  @Transactional(readOnly = true)
  public CategoryDTO getCategoryById(Long id) {
    Category category = categoryRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Categoria", "id", id));
    return mapToDTO(category);
  }

  private CategoryDTO mapToDTO(Category category) {
    return new CategoryDTO(
        category.getId(),
        category.getName(),
        category.getType(),
        category.getIcon(),
        category.getColor()
    );
  }
}