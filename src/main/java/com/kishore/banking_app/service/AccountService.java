package com.kishore.banking_app.service;

import com.kishore.banking_app.dto.AccountDto;
import com.kishore.banking_app.entity.Account;
import org.springframework.stereotype.Service;

import java.util.List;

public interface AccountService {
    AccountDto createAccount(AccountDto ac);
    AccountDto findAccountById(Long id);
    AccountDto depositAmount(Long id,double amount);
    AccountDto updateName(Long id,String name);
    AccountDto withdrawAmount(Long id,double amount);
    List<AccountDto> findAllAccounts();
    AccountDto deleteAccount(Long id);
}
