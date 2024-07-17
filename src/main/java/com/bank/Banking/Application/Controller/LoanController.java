package com.bank.Banking.Application.Controller;

import com.bank.Banking.Application.Model.Loan;
import com.bank.Banking.Application.Services.LoanService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/loan")
@Tag(name = "Loan API", description = "Manage loans")
public class LoanController {

    @Autowired
    private LoanService loanService;

    @PostMapping("/apply")
    public ResponseEntity<Loan> applyLoan(@RequestBody Loan loan) {
        Loan savedLoan = loanService.applyLoan(loan);
        return new ResponseEntity<>(savedLoan, HttpStatus.CREATED);
    }

    @PutMapping("/repay/{loanId}")
    public ResponseEntity<Loan> repayLoan(@PathVariable Long loanId, @RequestParam double amount) {
        Loan updatedLoan = loanService.repayLoan(loanId, amount);
        return new ResponseEntity<>(updatedLoan, HttpStatus.OK);
    }
}

