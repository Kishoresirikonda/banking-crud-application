package com.kishore.banking_app.service.impl;

import com.kishore.banking_app.dto.AccountDto;
import com.kishore.banking_app.entity.Account;
import com.kishore.banking_app.entity.ErrorResponse;
import com.kishore.banking_app.entity.mapper.AccountMapper;
import com.kishore.banking_app.exception.IdNotFound;
import com.kishore.banking_app.repository.AccountRepository;
import com.kishore.banking_app.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        Account account = AccountMapper.mapToAccount(accountDto);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto findAccountById(Long id) {
        Account account = accountRepository.findById(id).orElseThrow(()->new IdNotFound("Account Not Found:"+id));
        return AccountMapper.mapToAccountDto(account);
    }

    @Override
    public AccountDto depositAmount(Long id, double amount) {
        Account account = accountRepository
                .findById(id)
                .orElseThrow(()->new IdNotFound("Account Not Found:"+id));
        account.setBalance(account.getBalance()+amount);
        Account saveAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(saveAccount);
    }

    @Override
    public AccountDto updateName(Long id, String name) {
        Account account = accountRepository
                .findById(id)
                .orElseThrow(()->new IdNotFound("Account Not found:"+id));
        account.setAccountHolderName(name);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto withdrawAmount(Long id, double amount) {
        Account account = accountRepository
                .findById(id)
                .orElseThrow(()->new IdNotFound("Account Not found:"+id));
        if(account.getBalance()<amount){
            throw  new RuntimeException("Insufficient amount");
        }
        double total = account.getBalance()-amount;
        account.setBalance(total);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public List<AccountDto> findAllAccounts() {
        List<Account> accounts = accountRepository.findAll();
        return accounts.stream().map((account)->AccountMapper.mapToAccountDto(account))
                .collect(Collectors.toList());
    }

    @Override
    public AccountDto deleteAccount(Long id) {
        Account account = null;
            account = accountRepository
                    .findById(id)
                    .orElseThrow(()->new IdNotFound("Account Not found:"+id));
        accountRepository.deleteById(id);
        return AccountMapper.mapToAccountDto(account);
    }


}
