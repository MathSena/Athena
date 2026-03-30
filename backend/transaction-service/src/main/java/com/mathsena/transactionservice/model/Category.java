package com.mathsena.transactionservice.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "categories")
public class Category {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, length = 100)
  private String name;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private TransactionType type;

  @Column(length = 50)
  private String icon;

  @Column(length = 20)
  private String color;

  @Column(name = "created_at", nullable = false, updatable = false)
  private LocalDateTime createdAt;

  @PrePersist
  protected void onCreate() {
    createdAt = LocalDateTime.now();
  }

  public Category() {}

  public Category(String name, TransactionType type, String icon, String color) {
    this.name = name;
    this.type = type;
    this.icon = icon;
    this.color = color;
  }


  public Long getId() { return id; }
  public void setId(Long id) { this.id = id; }

  public String getName() { return name; }
  public void setName(String name) { this.name = name; }

  public TransactionType getType() { return type; }
  public void setType(TransactionType type) { this.type = type; }

  public String getIcon() { return icon; }
  public void setIcon(String icon) { this.icon = icon; }

  public String getColor() { return color; }
  public void setColor(String color) { this.color = color; }

  public LocalDateTime getCreatedAt() { return createdAt; }
}
