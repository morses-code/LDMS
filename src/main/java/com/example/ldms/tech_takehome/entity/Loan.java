package com.example.ldms.tech_takehome.entity;

import java.util.List;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double principal;
    private double annualInterestRate;
    private int termInMonths;
    private double balloonPayment;

    @ElementCollection
    private List<AmortisationScheduleEntry> schedule;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getPrincipal() {
        return principal;
    }

    public void setPrincipal(double principal) {
        this.principal = principal;
    }

    public double getAnnualInterestRate() {
        return annualInterestRate;
    }

    public void setAnnualInterestRate(double annualInterestRate) {
        this.annualInterestRate = annualInterestRate;
    }

    public int getTermInMonths() {
        return termInMonths;
    }

    public void setTermInMonths(int termInMonths) {
        this.termInMonths = termInMonths;
    }

    public double getBalloonPayment() {
        return balloonPayment;
    }

    public void setBalloonPayment(double balloonPayment) {
        this.balloonPayment = balloonPayment;
    }

    public List<AmortisationScheduleEntry> getSchedule() {
        return schedule;
    }

    public void setSchedule(List<AmortisationScheduleEntry> schedule) {
        this.schedule = schedule;
    }
}
