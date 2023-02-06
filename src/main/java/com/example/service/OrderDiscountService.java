package com.example.service;
 
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
import com.example.model.OrderDiscount;
import com.example.model.OrderRequest;
 
@Service
public class OrderDiscountService {
 
    @Autowired
    private KieContainer kieContainer;

    public OrderDiscount getDiscount(OrderRequest orderRequest) {
        // return null;
        OrderDiscount orderDiscount = new OrderDiscount();
        KieSession kieSession = kieContainer.newKieSession();
        kieSession.setGlobal("orderDiscount", orderDiscount);
        kieSession.insert(orderRequest);
        kieSession.fireAllRules();
        System.out.println("Finally" + kieSession.getGlobal("orderDiscount"));
        // System.out.println("Omg" + kieSession.getGlobal("orderDiscount":);
        System.out.println(kieSession.getGlobals());
        kieSession.dispose();
        return orderDiscount;
    }
}