package com.bank.Banking.Application.Services;

import com.bank.Banking.Application.Model.Loan;
import com.bank.Banking.Application.Repository.BankRepo;
import com.bank.Banking.Application.Repository.LoanRepo;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoanService {

    @Autowired
    private LoanRepo loanRepository;
    @Autowired
    private BankRepo bankRepository;

    public Loan applyLoan(Loan loan) {
        loan.setOutstandingAmount(loan.getLoanAmount());
        loan.setStatus("ACTIVE");
        return loanRepository.save(loan);
    }

    public Loan repayLoan(Long loanId, double amount) {
        Loan loan = loanRepository.findById(loanId).orElse(null);
        if (loan != null && loan.getOutstandingAmount() >= amount) {
            loan.setOutstandingAmount(loan.getOutstandingAmount() - amount);
            if (loan.getOutstandingAmount() == 0) {
                loan.setStatus("CLOSED");
            }
            return loanRepository.save(loan);
        }
        throw new RuntimeException("Invalid loan or amount");
    }
}
