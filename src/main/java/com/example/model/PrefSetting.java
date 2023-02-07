package com.example.model;

 
import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;
 
@Getter
@Setter
public class PrefSetting {
 
    public String industry;
    public ArrayList<String> subIndustry;
    public Integer minAmount;
    public Integer maxAmount;
    public Integer minRoi;
    public Integer maxRoi;
    public Integer tenor;
}

