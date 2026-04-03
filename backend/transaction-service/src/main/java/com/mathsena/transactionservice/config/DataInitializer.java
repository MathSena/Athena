package com.mathsena.transactionservice.config;

import com.mathsena.transactionservice.model.Category;
import com.mathsena.transactionservice.model.TransactionType;
import com.mathsena.transactionservice.repository.CategoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

  private final CategoryRepository categoryRepository;

  public DataInitializer(CategoryRepository categoryRepository) {
    this.categoryRepository = categoryRepository;
  }

  @Override
  public void run(String... args) {
    if (categoryRepository.count() == 0) {
      System.out.println("📦 Inicializando categorias padrão...");
      initializeCategories();
      System.out.println("✅ Categorias criadas com sucesso!");
    }
  }

  private void initializeCategories() {
    List<Category> expenseCategories = Arrays.asList(
        new Category("Alimentação", TransactionType.EXPENSE, "🍔", "#FF6B6B"),
        new Category("Transporte", TransactionType.EXPENSE, "🚗", "#4ECDC4"),
        new Category("Moradia", TransactionType.EXPENSE, "🏠", "#95E1D3"),
        new Category("Saúde", TransactionType.EXPENSE, "💊", "#F38181"),
        new Category("Educação", TransactionType.EXPENSE, "📚", "#AA96DA"),
        new Category("Lazer", TransactionType.EXPENSE, "🎮", "#FCBAD3"),
        new Category("Compras", TransactionType.EXPENSE, "🛍️", "#FFD93D"),
        new Category("Contas", TransactionType.EXPENSE, "📄", "#6BCB77"),
        new Category("Outros", TransactionType.EXPENSE, "📦", "#B8B8D1")
    );

    List<Category> incomeCategories = Arrays.asList(
        new Category("Salário", TransactionType.INCOME, "💰", "#4CAF50"),
        new Category("Freelance", TransactionType.INCOME, "💻", "#2196F3"),
        new Category("Investimentos", TransactionType.INCOME, "📈", "#9C27B0"),
        new Category("Bonificação", TransactionType.INCOME, "🎁", "#FF9800"),
        new Category("Outros", TransactionType.INCOME, "➕", "#607D8B")
    );

    categoryRepository.saveAll(expenseCategories);
    categoryRepository.saveAll(incomeCategories);
  }
}
