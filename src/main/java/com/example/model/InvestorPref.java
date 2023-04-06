package com.example.model;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class InvestorPref {
    public Integer entityId;
    public String industry;
    public String subIndustry;
    public Integer minAmount;
    public Integer maxAmount;
    public Integer minRoi;
    public Integer maxRoi;
    public Integer tenor;
    public String rating;
    public String ratingAgency;

    private Double minNetRevenue;
    private Double minEbitda;
    private Double minEbitdaCsf;
    private Double minProfitAfterTax;
    private Double minProfitAfterTaxCsf;
    private Double minTotalDebtEbitda;
    private Double minDebtEquityRatio;
    private Double minCurrentRatio;
    private Double minDscr;
    private Double minTotAtnw;
    private Double minLppAnnualRevenue;
    private Double minLppAnnualEbitda;
    private Double minLppNetProfit;
    private Double minLppNetWorth;
    private Double minLppAbbEmi;
    private Double minLppDscr;


    private Double maxNetRevenue;
    private Double maxEbitda;
    private Double maxEbitdaCsf;
    private Double maxProfitAfterTax;
    private Double maxProfitAfterTaxCsf;
    private Double maxTotalDebtEbitda;
    private Double maxDebtEquityRatio;
    private Double maxCurrentRatio;
    private Double maxDscr;
    private Double maxTotAtnw;
    private Double maxLppAnnualRevenue;
    private Double maxLppAnnualEbitda;
    private Double maxLppNetProfit;
    private Double maxLppNetWorth;
    private Double maxLppAbbEmi;
    private Double maxLppDscr;
}
