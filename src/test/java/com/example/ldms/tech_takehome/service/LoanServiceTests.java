package com.example.ldms.tech_takehome.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.ldms.tech_takehome.entity.AmortisationScheduleEntry;
import com.example.ldms.tech_takehome.entity.Loan;
import com.example.ldms.tech_takehome.repository.LoanRepository;

public class LoanServiceTests {

    @Mock
    private LoanRepository loanRepository;

    @InjectMocks
    private LoanService loanService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateLoan() {
        Loan loan = new Loan();
        loan.setId(1L);
        loan.setPrincipal(1000.0);
        loan.setAnnualInterestRate(5.0);
        loan.setTermInMonths(12);
        loan.setBalloonPayment(0.0);
        loan.setSchedule(new ArrayList<>());

        when(loanRepository.save(any(Loan.class))).thenReturn(loan);

        Loan result = loanService.createLoan(1000.0, 5.0, 12, 0.0);

        assertNotNull(result);
        assertEquals(loan.getPrincipal(), result.getPrincipal());
        assertEquals(loan.getAnnualInterestRate(), result.getAnnualInterestRate());
        assertEquals(loan.getTermInMonths(), result.getTermInMonths());
        assertEquals(loan.getBalloonPayment(), result.getBalloonPayment());
        assertNotNull(result.getSchedule());
    }

    @Test
    void testGetLoans() {
        Loan loan = new Loan();
        loan.setId(1L);
        loan.setPrincipal(1000.0);
        loan.setAnnualInterestRate(5.0);
        loan.setTermInMonths(12);
        loan.setBalloonPayment(0.0);

        List<Loan> loans = new ArrayList<>();
        loans.add(loan);

        when(loanRepository.findAll()).thenReturn(loans);

        List<Loan> result = loanService.getLoans();

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertEquals(loan.getId(), result.get(0).getId());
    }

    @Test
    void testGetLoan() {
        Loan loan = new Loan();
        loan.setId(1L);
        loan.setPrincipal(1000.0);
        loan.setAnnualInterestRate(5.0);
        loan.setTermInMonths(12);
        loan.setBalloonPayment(0.0);

        when(loanRepository.findById(1L)).thenReturn(Optional.of(loan));

        Loan result = loanService.getLoan(1L);

        assertNotNull(result);
        assertEquals(loan.getId(), result.getId());
    }

    @Test
    void testGetLoanNotFound() {
        when(loanRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(EntityNotFoundException.class, () -> {
            loanService.getLoan(1L);
        });

        assertEquals("Loan not found", exception.getMessage());
    }

    @Test
    void testCalculateAmortisationSchedule() {
        List<AmortisationScheduleEntry> schedule = loanService.calculateAmortisationSchedule(1000.0, 5.0, 12, 0.0);

        assertNotNull(schedule);
        assertFalse(schedule.isEmpty());
        assertEquals(12, schedule.size());
        assertEquals(1, schedule.get(0).getMonth());
        assertTrue(schedule.get(11).getRemainingBalance() < 1); // Remaining balance should be close to zero
    }

    @Test
    void testCalculateMonthlyPayment() {
        double monthlyPayment = loanService.calculateMonthlyPayment(1000.0, 0.00416667, 12, 0.0);

        assertEquals(85.61, monthlyPayment, 0.01);
    }
}
