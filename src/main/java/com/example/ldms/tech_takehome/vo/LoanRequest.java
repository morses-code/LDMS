package com.example.ldms.tech_takehome.vo;

public class LoanRequest {
    private double principal;
    private double annualInterestRate;
    private int termInMonths;
    private double balloonPayment;

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
}
