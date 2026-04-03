package com.mathsena.transactionservice.dto;

import com.mathsena.transactionservice.model.TransactionType;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class TransactionResponseDTO {

  private Long id;
  private String description;
  private BigDecimal amount;
  private TransactionType type;
  private CategoryDTO category;
  private LocalDate transactionDate;
  private String paymentMethod;
  private String notes;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;

  public TransactionResponseDTO() {}

  public Long getId() { return id; }
  public void setId(Long id) { this.id = id; }

  public String getDescription() { return description; }
  public void setDescription(String description) { this.description = description; }

  public BigDecimal getAmount() { return amount; }
  public void setAmount(BigDecimal amount) { this.amount = amount; }

  public TransactionType getType() { return type; }
  public void setType(TransactionType type) { this.type = type; }

  public CategoryDTO getCategory() { return category; }
  public void setCategory(CategoryDTO category) { this.category = category; }

  public LocalDate getTransactionDate() { return transactionDate; }
  public void setTransactionDate(LocalDate transactionDate) { this.transactionDate = transactionDate; }

  public String getPaymentMethod() { return paymentMethod; }
  public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }

  public String getNotes() { return notes; }
  public void setNotes(String notes) { this.notes = notes; }

  public LocalDateTime getCreatedAt() { return createdAt; }
  public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

  public LocalDateTime getUpdatedAt() { return updatedAt; }
  public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}
