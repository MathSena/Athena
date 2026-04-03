package com.mathsena.transactionservice.controller;

import com.mathsena.transactionservice.dto.TransactionRequestDTO;
import com.mathsena.transactionservice.dto.TransactionResponseDTO;
import com.mathsena.transactionservice.model.TransactionType;
import com.mathsena.transactionservice.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/transactions")
@CrossOrigin(origins = "*")
public class TransactionController {

  private final TransactionService transactionService;

  public TransactionController(TransactionService transactionService) {
    this.transactionService = transactionService;
  }

  @PostMapping
  public ResponseEntity<TransactionResponseDTO> createTransaction(@Valid @RequestBody TransactionRequestDTO dto) {
    TransactionResponseDTO response = transactionService.createTransaction(dto);
    return new ResponseEntity<>(response, HttpStatus.CREATED);
  }

  @GetMapping
  public ResponseEntity<List<TransactionResponseDTO>> getAllTransactions(
      @RequestParam(required = false) TransactionType type,
      @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
      @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate
  ) {
    List<TransactionResponseDTO> transactions;

    if (startDate != null && endDate != null) {
      transactions = transactionService.getTransactionsByDateRange(startDate, endDate);
    } else if (type != null) {
      transactions = transactionService.getTransactionsByType(type);
    } else {
      transactions = transactionService.getAllTransactions();
    }

    return ResponseEntity.ok(transactions);
  }

  @GetMapping("/{id}")
  public ResponseEntity<TransactionResponseDTO> getTransactionById(@PathVariable Long id) {
    TransactionResponseDTO response = transactionService.getTransactionById(id);
    return ResponseEntity.ok(response);
  }

  @PutMapping("/{id}")
  public ResponseEntity<TransactionResponseDTO> updateTransaction(
      @PathVariable Long id,
      @Valid @RequestBody TransactionRequestDTO dto
  ) {
    TransactionResponseDTO response = transactionService.updateTransaction(id, dto);
    return ResponseEntity.ok(response);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteTransaction(@PathVariable Long id) {
    transactionService.deleteTransaction(id);
    return ResponseEntity.noContent().build();
  }
}

