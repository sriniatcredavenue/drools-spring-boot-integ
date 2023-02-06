package com.example.service;
 
import java.util.List;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Customer;
import com.example.model.PrefSetting;
import com.example.model.Deal;
import java.util.List;
 
@Service
public class FilterService {
 
    @Autowired
    private KieContainer kieContainer;
    private KieSession kieSession;

    public String runFilter(Customer CustomerRequest) {
        PrefSetting pref_setting = setPref();
        kieSession = kieContainer.newKieSession();
        insertCustomerData(kieSession, false);
        insertDealData(kieSession);
        kieSession.setGlobal("prefSetting", pref_setting);
        System.out.println("Started to fire the rules");
        kieSession.fireAllRules();
        kieSession.dispose();
        return "Success";
    }

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

    PrefSetting setPref(){
        PrefSetting pref_setting = new PrefSetting();
        pref_setting.setIndustry("fs");
        pref_setting.setSubIndustry("fs");
        pref_setting.setMinAmount(10);
        pref_setting.setMaxAmount(100);
        pref_setting.setMinRoi(10);
        pref_setting.setMaxRoi(100);
        pref_setting.setTenor(100);
        return pref_setting;
    }
}