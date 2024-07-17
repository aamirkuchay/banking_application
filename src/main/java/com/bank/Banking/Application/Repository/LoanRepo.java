package com.bank.Banking.Application.Repository;

import com.bank.Banking.Application.Model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanRepo extends JpaRepository<Loan,Long> {
}
