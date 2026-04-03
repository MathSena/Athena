package com.mathsena.transactionservice.exception;

import java.time.LocalDateTime;
import java.util.Map;

public class ErrorResponse {

  private LocalDateTime timestamp;
  private Integer status;
  private String error;
  private String message;
  private Map<String, String> validationErrors;

  public ErrorResponse() {
    this.timestamp = LocalDateTime.now();
  }

  public ErrorResponse(Integer status, String error, String message) {
    this.timestamp = LocalDateTime.now();
    this.status = status;
    this.error = error;
    this.message = message;
  }

  public LocalDateTime getTimestamp() { return timestamp; }
  public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }

  public Integer getStatus() { return status; }
  public void setStatus(Integer status) { this.status = status; }

  public String getError() { return error; }
  public void setError(String error) { this.error = error; }

  public String getMessage() { return message; }
  public void setMessage(String message) { this.message = message; }

  public Map<String, String> getValidationErrors() { return validationErrors; }
  public void setValidationErrors(Map<String, String> validationErrors) {
    this.validationErrors = validationErrors;
  }
}