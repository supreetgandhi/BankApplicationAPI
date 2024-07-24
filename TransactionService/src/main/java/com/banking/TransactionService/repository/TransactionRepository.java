package com.banking.TransactionService.repository;

import com.banking.TransactionService.model.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findTransactionByAccountId(String accountId);
    List<Transaction> findTransactionByReferenceId(String referenceId);
}
