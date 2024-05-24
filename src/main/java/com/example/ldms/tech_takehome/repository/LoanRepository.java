package com.example.ldms.tech_takehome.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.ldms.tech_takehome.entity.Loan;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {
}
