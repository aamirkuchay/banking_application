package com.bank.Banking.Application.Controller;

import com.bank.Banking.Application.Model.Bank;
import com.bank.Banking.Application.Model.TransferDTO;
import com.bank.Banking.Application.Repository.BankRepo;
import com.bank.Banking.Application.Services.BankService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("bank")
@Tag(name = "Banking API", description = "Manage your finances with our Banking API. Securely perform transactions, check balances, and more.")
public class BankController {

    @Autowired
    private BankService bankService;

    @PostMapping("/openAc")
    public ResponseEntity<List<Bank>> openingAccount(@RequestBody List<Bank> bank) {
        List<Bank> savedBanks = bankService.openingAccount(bank);
        return new ResponseEntity<>(savedBanks, HttpStatus.CREATED);
    }

    @PostMapping("/deposit")
    public ResponseEntity<Bank> deposit(@RequestParam String acNumber, @RequestParam double deposit) {
        try {
            Bank bank = bankService.deposit(acNumber, deposit);
            return new ResponseEntity<>(bank, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/withdraw")
    public ResponseEntity<Bank> withdraw(@RequestParam String acNumber, @RequestParam double withdraw) {
        try {
            Bank bank = bankService.withdraw(acNumber, withdraw);
            return new ResponseEntity<>(bank, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/transfer")
    public ResponseEntity<TransferDTO> transfer(@RequestParam String sender, @RequestParam String receiver, @RequestParam double amount) {
        try {
            TransferDTO transferDTO = bankService.transfer(sender, receiver, amount);
            return new ResponseEntity<>(transferDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Bank>> getAllBanks() {
        try {
            List<Bank> banks = bankService.getAll();
            return new ResponseEntity<>(banks, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/getByAcNumber")
    public Bank getByAcNumber(@RequestParam String AcNumber) {
        return bankService.getByAcNumber(AcNumber);
    }


}
