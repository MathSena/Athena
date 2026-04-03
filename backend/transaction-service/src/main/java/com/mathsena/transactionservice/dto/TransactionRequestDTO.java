package com.mathsena.transactionservice.dto;

import com.mathsena.transactionservice.model.TransactionType;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TransactionRequestDTO {

  @NotBlank(message = "Descrição é obrigatória")
  @Size(max = 255, message = "Descrição deve ter no máximo 255 caracteres")
  private String description;

  @NotNull(message = "Valor é obrigatório")
  @DecimalMin(value = "0.01", message = "Valor deve ser maior que zero")
  private BigDecimal amount;

  @NotNull(message = "Tipo é obrigatório")
  private TransactionType type;

  private Long categoryId;

  @NotNull(message = "Data da transação é obrigatória")
  private LocalDate transactionDate;

  @Size(max = 50, message = "Método de pagamento deve ter no máximo 50 caracteres")
  private String paymentMethod;

  @Size(max = 1000, message = "Observações devem ter no máximo 1000 caracteres")
  private String notes;

  // Construtores
  public TransactionRequestDTO() {}

  // Getters e Setters
  public String getDescription() { return description; }
  public void setDescription(String description) { this.description = description; }

  public BigDecimal getAmount() { return amount; }
  public void setAmount(BigDecimal amount) { this.amount = amount; }

  public TransactionType getType() { return type; }
  public void setType(TransactionType type) { this.type = type; }

  public Long getCategoryId() { return categoryId; }
  public void setCategoryId(Long categoryId) { this.categoryId = categoryId; }

  public LocalDate getTransactionDate() { return transactionDate; }
  public void setTransactionDate(LocalDate transactionDate) { this.transactionDate = transactionDate; }

  public String getPaymentMethod() { return paymentMethod; }
  public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }

  public String getNotes() { return notes; }
  public void setNotes(String notes) { this.notes = notes; }
}