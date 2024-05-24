package com.example.ldms.tech_takehome.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.ldms.tech_takehome.entity.AmortisationScheduleEntry;
import com.example.ldms.tech_takehome.entity.Loan;
import com.example.ldms.tech_takehome.repository.LoanRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class LoanService {
    private final LoanRepository loanRepository;

    public LoanService(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    public Loan createLoan(double principal, double annualInterestRate, int termInMonths, double balloonPayment) {
        var loan = new Loan();
        loan.setPrincipal(principal);
        loan.setAnnualInterestRate(annualInterestRate);
        loan.setBalloonPayment(balloonPayment);
        loan.setTermInMonths(termInMonths);
        loan.setSchedule(calculateAmortisationSchedule(principal, annualInterestRate, termInMonths, balloonPayment));
        return loanRepository.save(loan);
    }

    public List<Loan> getLoans() {
        return loanRepository.findAll();
    }

    public Loan getLoan(Long id) {
        return loanRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Loan not found"));
    }

    private List<AmortisationScheduleEntry> calculateAmortisationSchedule(double principal, double annualInterestRate,
            int termInMonths, double balloonPayment) {
        var schedule = new ArrayList<AmortisationScheduleEntry>();
        var monthlyInterestRate = annualInterestRate / 12 / 100;
        var monthlyPayment = calculateMonthlyPayment(principal, monthlyInterestRate, termInMonths, balloonPayment);
        var remainingPrincipal = principal;

        for (var month = 1; month <= termInMonths; month++) {
            var interestPayment = remainingPrincipal * monthlyInterestRate;
            var principalPayment = monthlyPayment - interestPayment;
            remainingPrincipal -= principalPayment;

            var entry = new AmortisationScheduleEntry();
            entry.setMonth(month);
            entry.setInterestPayment(interestPayment);
            entry.setPrincipalPayment(principalPayment);
            entry.setRemainingBalance(remainingPrincipal);
            schedule.add(entry);
        }

        // Adjust the remaining balance for the balloon payment
        if (balloonPayment > 0) {
            var finalEntry = new AmortisationScheduleEntry();
            finalEntry.setMonth(termInMonths);
            finalEntry.setInterestPayment(0);
            finalEntry.setPrincipalPayment(balloonPayment);
            finalEntry.setRemainingBalance(0);
            schedule.add(finalEntry);
        }

        return schedule;
    }

    private double calculateMonthlyPayment(double principal, double monthlyInterestRate, int termInMonths,
            double balloonPayment) {
        if (balloonPayment == 0) {
            return principal * (monthlyInterestRate * Math.pow(1 + monthlyInterestRate, termInMonths))
                    / (Math.pow(1 + monthlyInterestRate, termInMonths) - 1);
        } else {
            double amortizedPart = principal - balloonPayment / Math.pow(1 + monthlyInterestRate, termInMonths);
            return (amortizedPart * monthlyInterestRate) / (1 - Math.pow(1 + monthlyInterestRate, -termInMonths));
        }
    }
}
