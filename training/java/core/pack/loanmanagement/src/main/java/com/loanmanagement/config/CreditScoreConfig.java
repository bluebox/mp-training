package com.loanmanagement.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:creditscores.properties")
public class CreditScoreConfig {

    @Value("${loan.high}")
    private  int highLoan;

    @Value("${loan.medium}")
    private int mediumLoan;

    @Value("${loan.low}")
    private int lowLoan;

    // Getters
    public int getHighLoan() {
        return highLoan;
    }

    public int getMediumLoan() {
        return mediumLoan;
    }

    public int getLowLoan() {
        return lowLoan;
    }
}
