package com.mathsena.transactionservice.controller;

import com.mathsena.transactionservice.dto.CategoryDTO;
import com.mathsena.transactionservice.model.TransactionType;
import com.mathsena.transactionservice.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
@CrossOrigin(origins = "*")
public class CategoryController {

  private final CategoryService categoryService;

  public CategoryController(CategoryService categoryService) {
    this.categoryService = categoryService;
  }

  @PostMapping
  public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO dto) {
    CategoryDTO response = categoryService.createCategory(dto);
    return new ResponseEntity<>(response, HttpStatus.CREATED);
  }

  @GetMapping
  public ResponseEntity<List<CategoryDTO>> getAllCategories(
      @RequestParam(required = false) TransactionType type
  ) {
    List<CategoryDTO> categories;

    if (type != null) {
      categories = categoryService.getCategoriesByType(type);
    } else {
      categories = categoryService.getAllCategories();
    }

    return ResponseEntity.ok(categories);
  }

  @GetMapping("/{id}")
  public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable Long id) {
    CategoryDTO response = categoryService.getCategoryById(id);
    return ResponseEntity.ok(response);
  }
}
