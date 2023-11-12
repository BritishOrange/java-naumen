package com.develop.internetshop.entities.Transaction;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * TransactionRepository
 */
@Repository
public interface TransactionRepository extends CrudRepository<Transaction, Long> {
}