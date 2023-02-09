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
}
