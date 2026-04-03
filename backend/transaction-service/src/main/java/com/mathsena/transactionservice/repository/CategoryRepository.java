package com.mathsena.transactionservice.repository;

import com.mathsena.transactionservice.model.Category;
import com.mathsena.transactionservice.model.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

  List<Category> findByType(TransactionType type);

  boolean existsByNameAndType(String name, TransactionType type);
}