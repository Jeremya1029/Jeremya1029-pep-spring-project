package com.example.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Account;
import com.example.repository.AccountRepository;

import java.util.List;

@Service
@Transactional
public class AccountService {

    AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public boolean usernameExists(String username) {
        return accountRepository.findByUsername(username).isPresent();
    }

    public Account registerAccount(Account account) {
        if (account.getUsername() == null || account.getUsername().isBlank())
            return null;
        if(account.getPassword().length() < 4)
            return null;

        if(usernameExists(account.getUsername())){
            return new Account();
        }

        return accountRepository.save(account);
    }

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }
}
