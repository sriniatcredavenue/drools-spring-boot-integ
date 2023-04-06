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
import com.example.model.InvestorPref;
import java.util.List;
import java.sql.Time;
import java.time.*;
 
@Service
public class FilterServiceMultiThread {
 
    @Autowired
    KieContainer kieContainer;
    KieSession kieSession;

    public int tot_customers_count = 100;
    public int tot_investors_count = 100;

    public int investors_count = tot_investors_count;
    public int customers_count = tot_customers_count;
    
    Thread[] threads = new Thread[investors_count];

    public String runFilter() {
        FilterResultSet filterResultSet = setFilterResultSet();
        System.out.println("hello" + threads.length);
        for(int i=0; i<threads.length;i++){
            final int index = i;
            threads[i] = new Thread(){
                public void run(){
                    System.out.println("-------" + customers_count + "---------" + investors_count + "--------" + index);
                    kieSession = kieContainer.newKieSession();
                    insertCustomerData(kieSession, index);
                    insertDealData(kieSession, index);
                    insertRatingData(kieSession, index);
                    insertPrefData(kieSession, index);
                    kieSession.setGlobal("filterResultSet", filterResultSet);
                    long start_time = System.currentTimeMillis(); 
                    System.out.println("Started to fire the rules" + start_time);
                    kieSession.fireAllRules();
                    long end_time = System.currentTimeMillis();
                    long dt = end_time - start_time;
                    System.out.println("Total time execution ------" + dt);
                    System.out.println("The final result is------" );
                    filterResultSet.viewMap();
                }
            };
            threads[i].start();
        }
        executeThreads();
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
        System.out.println("Completed after inserting rating data");
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
            kieSession.insert(pref_setting);
        }
    }

    public String getAlphaNumericString()
    {
        int n = 2;
        String AlphaNumericString = "abc";
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
        return Math.random() * (100 - 1);
    }
    

    FilterResultSet setFilterResultSet(){
        FilterResultSet filterResultSet = new FilterResultSet();
        return filterResultSet;
    }
}