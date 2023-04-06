package com.example.model;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FinancialRatio {
    private Integer id;
    private Integer customerId;
    private Double netRevenue;
    private Double ebitda;
    private Double ebitdaCsf;
    private Double profitAfterTax;
    private Double profitAfterTaxCsf;
    private Double totalDebtEbitda;
    private Double debtEquityRatio;
    private Double currentRatio;
    private Double dscr;
    private Double totAtnw;
    private Double lppAnnualRevenue;
    private Double lppAnnualEbitda;
    private Double lppNetProfit;
    private Double lppNetWorth;
    private Double lppAbbEmi;
    private Double lppDscr;
}