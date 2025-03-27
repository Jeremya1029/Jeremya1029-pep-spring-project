package com.example.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Account;
import com.example.repository.AccountRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AccountService {

    AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public boolean userExists(Integer id)
    {
        return accountRepository.findById(id).isPresent();
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

    public Account loginAccount(Account account)
    {
        Optional<Account> optionalAccount = accountRepository.findByUsername(account.getUsername());
        if(optionalAccount.isPresent())
        {
            Account repoAccount = optionalAccount.get();
            if(repoAccount.getUsername().equals(account.getUsername()) && repoAccount.getPassword().equals(account.getPassword()))
                return repoAccount;
        }
        return null;
    }

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }
}
