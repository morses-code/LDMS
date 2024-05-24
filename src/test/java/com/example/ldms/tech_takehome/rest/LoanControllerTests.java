package com.example.ldms.tech_takehome.rest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.ldms.tech_takehome.entity.Loan;
import com.example.ldms.tech_takehome.service.LoanService;
import com.example.ldms.tech_takehome.vo.LoanRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(LoanController.class)
public class LoanControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LoanService loanService;

    @InjectMocks
    private LoanController loanController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateLoan() throws Exception {
        var request = new LoanRequest();
        var loan = dummyLoan();

        when(loanService.createLoan(any(Double.class), any(Double.class), any(Integer.class), any(Double.class)))
                .thenReturn(loan);

        mockMvc.perform(post("/api/loan")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(loan.getId()))
                .andExpect(jsonPath("$.principal").value(loan.getPrincipal()))
                .andExpect(jsonPath("$.annualInterestRate").value(loan.getAnnualInterestRate()))
                .andExpect(jsonPath("$.termInMonths").value(loan.getTermInMonths()))
                .andExpect(jsonPath("$.balloonPayment").value(loan.getBalloonPayment()));
    }

    @Test
    void testGetLoans() throws Exception {
        var loan = dummyLoan();

        when(loanService.getLoans()).thenReturn(Collections.singletonList(loan));

        mockMvc.perform(get("/api/loan"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(loan.getId()))
                .andExpect(jsonPath("$[0].principal").value(loan.getPrincipal()))
                .andExpect(jsonPath("$[0].annualInterestRate").value(loan.getAnnualInterestRate()))
                .andExpect(jsonPath("$[0].termInMonths").value(loan.getTermInMonths()))
                .andExpect(jsonPath("$[0].balloonPayment").value(loan.getBalloonPayment()));
    }

    @Test
    void testGetLoan() throws Exception {
        var loan = dummyLoan();

        when(loanService.getLoan(1L)).thenReturn(loan);

        mockMvc.perform(get("/api/loan/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(loan.getId()))
                .andExpect(jsonPath("$.principal").value(loan.getPrincipal()))
                .andExpect(jsonPath("$.annualInterestRate").value(loan.getAnnualInterestRate()))
                .andExpect(jsonPath("$.termInMonths").value(loan.getTermInMonths()))
                .andExpect(jsonPath("$.balloonPayment").value(loan.getBalloonPayment()));
    }

    private Loan dummyLoan() {
        var loan = new Loan();
        loan.setId(1L);
        loan.setPrincipal(1000.0);
        loan.setTermInMonths(12);
        loan.setAnnualInterestRate(5.0);
        loan.setBalloonPayment(0);
        return loan;
    }
}
