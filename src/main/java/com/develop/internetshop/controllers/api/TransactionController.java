package com.develop.internetshop.controllers.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.develop.internetshop.entities.Transaction.Transaction;
import com.develop.internetshop.entities.Transaction.TransactionRepository;

/**
* TransactionController
*/
@RestController
@RequestMapping(path = "api/v1/transaction")
public class TransactionController extends BaseApiController<Transaction, String> {
    public TransactionController(TransactionRepository transactionRepository) {
        super(transactionRepository);
    }
}

