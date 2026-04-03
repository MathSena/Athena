package com.mathsena.transactionservice.service;

import com.mathsena.transactionservice.dto.CategoryDTO;
import com.mathsena.transactionservice.dto.TransactionRequestDTO;
import com.mathsena.transactionservice.dto.TransactionResponseDTO;
import com.mathsena.transactionservice.exception.ResourceNotFoundException;
import com.mathsena.transactionservice.model.Category;
import com.mathsena.transactionservice.model.Transaction;
import com.mathsena.transactionservice.model.TransactionType;
import com.mathsena.transactionservice.repository.CategoryRepository;
import com.mathsena.transactionservice.repository.TransactionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionService {

  private final TransactionRepository transactionRepository;
  private final CategoryRepository categoryRepository;

  public TransactionService(TransactionRepository transactionRepository,
      CategoryRepository categoryRepository) {
    this.transactionRepository = transactionRepository;
    this.categoryRepository = categoryRepository;
  }

  @Transactional
  public TransactionResponseDTO createTransaction(TransactionRequestDTO dto) {
    Transaction transaction = new Transaction();
    transaction.setDescription(dto.getDescription());
    transaction.setAmount(dto.getAmount());
    transaction.setType(dto.getType());
    transaction.setTransactionDate(dto.getTransactionDate());
    transaction.setPaymentMethod(dto.getPaymentMethod());
    transaction.setNotes(dto.getNotes());

    if (dto.getCategoryId() != null) {
      Category category = categoryRepository.findById(dto.getCategoryId())
          .orElseThrow(() -> new ResourceNotFoundException("Categoria", "id", dto.getCategoryId()));
      transaction.setCategory(category);
    }

    Transaction savedTransaction = transactionRepository.save(transaction);
    return mapToResponseDTO(savedTransaction);
  }

  @Transactional(readOnly = true)
  public List<TransactionResponseDTO> getAllTransactions() {
    return transactionRepository.findAll().stream()
        .map(this::mapToResponseDTO)
        .collect(Collectors.toList());
  }

  @Transactional(readOnly = true)
  public TransactionResponseDTO getTransactionById(Long id) {
    Transaction transaction = transactionRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Transação", "id", id));
    return mapToResponseDTO(transaction);
  }

  @Transactional(readOnly = true)
  public List<TransactionResponseDTO> getTransactionsByType(TransactionType type) {
    return transactionRepository.findByType(type).stream()
        .map(this::mapToResponseDTO)
        .collect(Collectors.toList());
  }

  @Transactional(readOnly = true)
  public List<TransactionResponseDTO> getTransactionsByDateRange(LocalDate startDate, LocalDate endDate) {
    return transactionRepository.findByTransactionDateBetween(startDate, endDate).stream()
        .map(this::mapToResponseDTO)
        .collect(Collectors.toList());
  }

  @Transactional
  public TransactionResponseDTO updateTransaction(Long id, TransactionRequestDTO dto) {
    Transaction transaction = transactionRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Transação", "id", id));

    transaction.setDescription(dto.getDescription());
    transaction.setAmount(dto.getAmount());
    transaction.setType(dto.getType());
    transaction.setTransactionDate(dto.getTransactionDate());
    transaction.setPaymentMethod(dto.getPaymentMethod());
    transaction.setNotes(dto.getNotes());

    if (dto.getCategoryId() != null) {
      Category category = categoryRepository.findById(dto.getCategoryId())
          .orElseThrow(() -> new ResourceNotFoundException("Categoria", "id", dto.getCategoryId()));
      transaction.setCategory(category);
    } else {
      transaction.setCategory(null);
    }

    Transaction updatedTransaction = transactionRepository.save(transaction);
    return mapToResponseDTO(updatedTransaction);
  }

  @Transactional
  public void deleteTransaction(Long id) {
    if (!transactionRepository.existsById(id)) {
      throw new ResourceNotFoundException("Transação", "id", id);
    }
    transactionRepository.deleteById(id);
  }

  private TransactionResponseDTO mapToResponseDTO(Transaction transaction) {
    TransactionResponseDTO dto = new TransactionResponseDTO();
    dto.setId(transaction.getId());
    dto.setDescription(transaction.getDescription());
    dto.setAmount(transaction.getAmount());
    dto.setType(transaction.getType());
    dto.setTransactionDate(transaction.getTransactionDate());
    dto.setPaymentMethod(transaction.getPaymentMethod());
    dto.setNotes(transaction.getNotes());
    dto.setCreatedAt(transaction.getCreatedAt());
    dto.setUpdatedAt(transaction.getUpdatedAt());

    if (transaction.getCategory() != null) {
      Category category = transaction.getCategory();
      CategoryDTO categoryDTO = new CategoryDTO(
          category.getId(),
          category.getName(),
          category.getType(),
          category.getIcon(),
          category.getColor()
      );
      dto.setCategory(categoryDTO);
    }

    return dto;
  }
}
