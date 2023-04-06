package com.example.service;
 
import java.util.ArrayList;
import java.util.List;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Customer;
import com.example.model.PrefSetting;
import com.example.model.Rating;
import com.example.model.Deal;
import com.example.model.FilterResultSet;
import com.example.model.FinancialRatio;
import com.example.model.InvestorPref;
import java.util.List;
import java.sql.Time;
import java.time.*;
 
@Service
public class FilterService {
 
    @Autowired
    KieContainer kieContainer;
    KieSession kieSession;

    public int tot_customers_count = 1000;
    public int tot_investors_count = 500;

    public int investors_count = tot_investors_count;
    public int customers_count = tot_customers_count;
    
    Thread[] threads = new Thread[investors_count];

    public String runFilter() {
        System.out.println("Started success");
        FilterResultSet filterResultSet = setFilterResultSet();
        System.out.println("-------" + customers_count + "---------" + investors_count + "--------");
        kieSession = kieContainer.newKieSession();
        insertCustomerData(kieSession, 0);
        insertDealData(kieSession, 0);
        insertRatingData(kieSession, 0);
        insertPrefData(kieSession, 0);
        insertFinancialRatioData(kieSession, 0);
        kieSession.setGlobal("filterResultSet", filterResultSet);
        long start_time = System.currentTimeMillis(); 
        System.out.println("Started to fire the rules" + start_time);
        kieSession.fireAllRules();
        long end_time = System.currentTimeMillis();
        long dt = end_time - start_time;
        System.out.println("Total time execution ------" + dt);
        System.out.println("The final result is------" );
        filterResultSet.viewMap();
        kieSession.dispose();
        return "Success";
    }

    public void executeThreads(){
        try {
            for (Thread thread : threads) {
                thread.join();
            }
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Take data from Mongo/elastic search and insert into kieserssion
    void insertCustomerData(KieSession kieSession, int window){
        for(int i=0;i<customers_count;i++){
            Customer newCustomer = new Customer();
            newCustomer.setId(window * 50 + i);
            newCustomer.setIndustry(getAlphaNumericString());
            newCustomer.setSubIndustry(getAlphaNumericString());
            kieSession.insert(newCustomer);
        }
    }

    void insertRatingData(KieSession kieSession, int window){
        for(int i=0;i<customers_count;i++){
            Rating newRating = new Rating();
            newRating.setCustomerId(window * 50 + i);
            newRating.setRating(getAlphaNumericString());
            newRating.setRatingAgency(getAlphaNumericString());
            kieSession.insert(newRating);
        }
        // System.out.println("Completed after inserting rating data");
    }

    void insertFinancialRatioData(KieSession KieSession, int window){
        for(int i=0;i<customers_count;i++){
            FinancialRatio financialRatio = new FinancialRatio();
            financialRatio.setCustomerId(window * 50 + i);
            financialRatio.setEbitda(getRandomInteger());
            financialRatio.setEbitdaCsf(getRandomInteger());
            financialRatio.setProfitAfterTax(getRandomInteger());
            financialRatio.setProfitAfterTaxCsf(getRandomInteger());
            financialRatio.setTotalDebtEbitda(getRandomInteger());
            financialRatio.setDebtEquityRatio(getRandomInteger());
            financialRatio.setCurrentRatio(getRandomInteger());
            financialRatio.setDscr(getRandomInteger());
            financialRatio.setTotAtnw(getRandomInteger());
            financialRatio.setLppAnnualRevenue(getRandomInteger());
            financialRatio.setLppAnnualEbitda(getRandomInteger());
            financialRatio.setLppNetProfit(getRandomInteger());
            financialRatio.setLppNetWorth(getRandomInteger());
            financialRatio.setLppAbbEmi(getRandomInteger());
            financialRatio.setLppDscr(getRandomInteger());
            kieSession.insert(financialRatio);
        }
    }

    void insertDealData(KieSession kieSession, int window){
        for(int i=0;i<100;i++){
            Deal newDeal = new Deal();
            newDeal.setId(window * 50 + i);
            newDeal.setAmount(getRandomInteger());
            newDeal.setRoi(getRandomInteger());
            newDeal.setTenor(getRandomInteger());
            kieSession.insert(newDeal);
        }
    }

    // Load all the investor preference setting and insert into the kie session
    void insertPrefData(KieSession kieSession, int window){
        
        for(int i=0;i<investors_count;i++){
            InvestorPref pref_setting = new InvestorPref();
            pref_setting.setEntityId(window * 50 + i);
            pref_setting.setIndustry(getAlphaNumericString());
            pref_setting.setSubIndustry(getAlphaNumericString());
            pref_setting.setRating(getAlphaNumericString());
            pref_setting.setRatingAgency(getAlphaNumericString());
            pref_setting.setMinAmount(10);
            pref_setting.setMaxAmount(100);
            pref_setting.setMinRoi(10);
            pref_setting.setMaxRoi(100);
            pref_setting.setTenor(100);

            pref_setting.setMinNetRevenue(getRandomInteger());
            pref_setting.setMinEbitda(getRandomInteger());
            pref_setting.setMinEbitdaCsf(getRandomInteger());
            pref_setting.setMinProfitAfterTax(getRandomInteger());
            pref_setting.setMinProfitAfterTaxCsf(getRandomInteger());
            pref_setting.setMinTotalDebtEbitda(getRandomInteger());
            pref_setting.setMinDebtEquityRatio(getRandomInteger());
            pref_setting.setMinCurrentRatio(getRandomInteger());
            pref_setting.setMinDscr(getRandomInteger());
            pref_setting.setMinTotAtnw(getRandomInteger());
            pref_setting.setMinLppAnnualRevenue(getRandomInteger());
            pref_setting.setMinLppAnnualEbitda(getRandomInteger());
            pref_setting.setMinLppNetProfit(getRandomInteger());
            pref_setting.setMinLppNetWorth(getRandomInteger());
            pref_setting.setMinLppAbbEmi(getRandomInteger());
            pref_setting.setMinLppDscr(getRandomInteger());

            pref_setting.setMaxNetRevenue(getRandomInteger());
            pref_setting.setMaxEbitda(getRandomInteger());
            pref_setting.setMaxEbitdaCsf(getRandomInteger());
            pref_setting.setMaxProfitAfterTax(getRandomInteger());
            pref_setting.setMaxProfitAfterTaxCsf(getRandomInteger());
            pref_setting.setMaxTotalDebtEbitda(getRandomInteger());
            pref_setting.setMaxDebtEquityRatio(getRandomInteger());
            pref_setting.setMaxCurrentRatio(getRandomInteger());
            pref_setting.setMaxDscr(getRandomInteger());
            pref_setting.setMaxTotAtnw(getRandomInteger());
            pref_setting.setMaxLppAnnualRevenue(getRandomInteger());
            pref_setting.setMaxLppAnnualEbitda(getRandomInteger());
            pref_setting.setMaxLppNetProfit(getRandomInteger());
            pref_setting.setMaxLppNetWorth(getRandomInteger());
            pref_setting.setMaxLppAbbEmi(getRandomInteger());
            pref_setting.setMaxLppDscr(getRandomInteger());

            kieSession.insert(pref_setting);
        }
    }

    public String getAlphaNumericString()
    {
        int n = 1;
        String AlphaNumericString = "a";
        StringBuilder sb = new StringBuilder(n);
        for (int i = 0; i < n; i++) {
        int index
            = (int)(AlphaNumericString.length()
            * Math.random());
        sb.append(AlphaNumericString
            .charAt(index));
        }
        return sb.toString();
    }

    public double getRandomInteger(){
        // Math.random() * ( Max - Min )
        return Math.random() * (10 - 1);
    }
    

    FilterResultSet setFilterResultSet(){
        FilterResultSet filterResultSet = new FilterResultSet();
        return filterResultSet;
    }
}