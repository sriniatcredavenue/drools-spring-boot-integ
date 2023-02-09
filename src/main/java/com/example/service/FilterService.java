package com.example.service;
 
import java.util.ArrayList;
import java.util.List;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Customer;
import com.example.model.PrefSetting;
import com.example.model.Deal;
import com.example.model.FilterResultSet;
import com.example.model.InvestorPref;
import java.util.List;
 
@Service
public class FilterService {
 
    @Autowired
    private KieContainer kieContainer;
    private KieSession kieSession;

    public String runFilter() {
        PrefSetting prefSetting = setPref();
        FilterResultSet filterResultSet = setFilterResultSet();
        kieSession = kieContainer.newKieSession();
        insertCustomerData(kieSession, false);
        insertDealData(kieSession);
        insertPrefData(kieSession);
        kieSession.setGlobal("prefSetting", prefSetting);
        kieSession.setGlobal("filterResultSet", filterResultSet);
        System.out.println("Started to fire the rules");
        kieSession.fireAllRules();
        filterResultSet.viewMap();
        // System.out.println(kieSession.getIdentifier());
        // System.out.println(kieSession.getProcessInstance(0));
        // System.out.println(kieSession.getSessionConfiguration());
        kieSession.dispose();
        return "Success";
    }

    // Take data from Mongo/elastic search and insert into kieserssion
    void insertCustomerData(KieSession kieSession, Boolean flag){
        for(int i=0;i<2;i++){
            Customer newCustomer = new Customer();
            newCustomer.setId(i);
            if(flag){
                newCustomer.setIndustry("fs");
                newCustomer.setSubIndustry("fs");
            }
            else{
                newCustomer.setIndustry("ef");
                newCustomer.setSubIndustry("ef");
            }
            flag = !flag;
            kieSession.insert(newCustomer);
        }
    }

    void insertDealData(KieSession kieSession){
        int amount = 1;
        int roi = 1;
        int tenor = 1;
        for(int i=0;i<100;i++){
            Deal newDeal = new Deal();
            newDeal.setId(i);
            newDeal.setAmount(amount);
            newDeal.setRoi(roi);
            newDeal.setTenor(tenor);
            kieSession.insert(newDeal);
            amount++;
            tenor++;
            roi++;
        }
    }

    // Load all the investor preference setting and insert into the kie session
    void insertPrefData(KieSession kieSession){
        
        for(int i=0;i<3;i++){
            InvestorPref pref_setting = new InvestorPref();
            pref_setting.setIndustry("fs");
            pref_setting.setEntityId(i + 100);
            pref_setting.setSubIndustry("fs");
            pref_setting.setMinAmount(10);
            pref_setting.setMaxAmount(100);
            pref_setting.setMinRoi(10);
            pref_setting.setMaxRoi(100);
            pref_setting.setTenor(100);
            kieSession.insert(pref_setting);
        }
    }

    // Mongod and load the investor preference into drools session
    PrefSetting setPref(){
        ArrayList<String> subIndustry = new ArrayList<String>();
        subIndustry.add("fs");
        subIndustry.add("ef");
        PrefSetting pref_setting = new PrefSetting();
        pref_setting.setIndustry("fs");
        pref_setting.setSubIndustry(subIndustry);
        pref_setting.setMinAmount(10);
        pref_setting.setMaxAmount(100);
        pref_setting.setMinRoi(10);
        pref_setting.setMaxRoi(100);
        pref_setting.setTenor(100);
        return pref_setting;
    }

    FilterResultSet setFilterResultSet(){
        FilterResultSet filterResultSet = new FilterResultSet();
        return filterResultSet;
    }
}