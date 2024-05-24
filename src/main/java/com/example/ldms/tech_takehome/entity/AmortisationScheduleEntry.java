package com.example.ldms.tech_takehome.entity;

import jakarta.persistence.Embeddable;

@Embeddable
public class AmortisationScheduleEntry {
    private int month_number;
    private double principalPayment;
    private double interestPayment;
    private double remainingBalance;

    public int getMonth() {
        return month_number;
    }

    public void setMonth(int month_number) {
        this.month_number = month_number;
    }

    public double getPrincipalPayment() {
        return principalPayment;
    }

    public void setPrincipalPayment(double principalPayment) {
        this.principalPayment = principalPayment;
    }

    public double getInterestPayment() {
        return interestPayment;
    }

    public void setInterestPayment(double interestPayment) {
        this.interestPayment = interestPayment;
    }

    public double getRemainingBalance() {
        return remainingBalance;
    }

    public void setRemainingBalance(double remainingBalance) {
        this.remainingBalance = remainingBalance;
    }
}
