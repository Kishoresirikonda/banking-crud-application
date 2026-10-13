package com.kishore.banking_app.controller;

import com.kishore.banking_app.dto.AccountDto;
import com.kishore.banking_app.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public ResponseEntity<AccountDto> saveAccount(@RequestBody AccountDto accountDto){
        AccountDto savedAccount = accountService.createAccount(accountDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedAccount);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> getAccountById(@PathVariable Long id){
        AccountDto accountDto = accountService.findAccountById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(accountDto);
    }

    @PutMapping("/{id}/deposit")
    public ResponseEntity<AccountDto> deposit(@PathVariable Long id, @RequestBody Map<String, Double> request){
        double amount = request.get("amount");
        AccountDto accountDto = accountService.depositAmount(id,amount);
        return ResponseEntity.ok(accountDto);
    }

    @PutMapping("/{id}/updateName")
    public ResponseEntity<AccountDto> updateName(@PathVariable Long id,@RequestBody Map<String,String> request){
        String name = request.get("name");
        AccountDto accountDto = accountService.updateName(id,name);
        return ResponseEntity.ok(accountDto);
    }

    @PutMapping("/{id}/withdraw")
    public  ResponseEntity<AccountDto> withdraw(@PathVariable Long id,@RequestBody Map<String,Double> request){
        double amount = request.get("amount");
        AccountDto accountDto = accountService.withdrawAmount(id,amount);
        return ResponseEntity.ok(accountDto);
    }

    @GetMapping("/")
    public ResponseEntity<List<AccountDto>> findAllAccount(){
        List<AccountDto> accountDtoList = accountService.findAllAccounts();
        return ResponseEntity.ok(accountDtoList);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AccountDto> deleteAccount(@PathVariable Long id){
        AccountDto accountDto = accountService.deleteAccount(id);
        return ResponseEntity.ok(accountDto);
    }
}
