package com.mathsena.transactionservice.repository;

import com.mathsena.transactionservice.model.Transaction;
import com.mathsena.transactionservice.model.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

  List<Transaction> findByType(TransactionType type);

  List<Transaction> findByTransactionDateBetween(LocalDate startDate, LocalDate endDate);

  List<Transaction> findByCategoryId(Long categoryId);

  List<Transaction> findByTypeAndTransactionDateBetween(
      TransactionType type,
      LocalDate startDate,
      LocalDate endDate
  );

  @Query("SELECT COALESCE(SUM(t.amount), 0) FROM Transaction t " +
      "WHERE t.type = 'INCOME' " +
      "AND t.transactionDate BETWEEN :startDate AND :endDate")
  BigDecimal calculateTotalIncome(
      @Param("startDate") LocalDate startDate,
      @Param("endDate") LocalDate endDate
  );

  @Query("SELECT COALESCE(SUM(t.amount), 0) FROM Transaction t " +
      "WHERE t.type = 'EXPENSE' " +
      "AND t.transactionDate BETWEEN :startDate AND :endDate")
  BigDecimal calculateTotalExpense(
      @Param("startDate") LocalDate startDate,
      @Param("endDate") LocalDate endDate
  );

  Long countByTransactionDateBetween(LocalDate startDate, LocalDate endDate);

  List<Transaction> findTop10ByOrderByTransactionDateDesc();
}
