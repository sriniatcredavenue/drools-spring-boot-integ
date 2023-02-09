package com.example.config;


import org.kie.api.*;
import org.kie.api.builder.*;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieBuilder.*;
import org.kie.api.builder.KieModule;
import org.kie.api.runtime.*;
import org.kie.internal.io.ResourceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DroolsConfig {
    // private static final String RULES_CUSTOMER_RULES_DRL = "rules/preference_filter.drl";
    private static final String RULES_CUSTOMER_RULES_DRL = "rules/preference_filter_multi_investor.drl";
    private static final KieServices kieServices = KieServices.Factory.get();
 
    @Bean
    public KieContainer kieContainer() {
        KieFileSystem kieFileSystem = kieServices.newKieFileSystem();
        kieFileSystem.write(ResourceFactory.newClassPathResource(RULES_CUSTOMER_RULES_DRL));
        KieBuilder kb = kieServices.newKieBuilder(kieFileSystem);
        System.out.println("--------------" + kb + "--------------");
        kb.buildAll();
        KieModule kieModule = kb.getKieModule();
        KieContainer kieContainer = kieServices.newKieContainer(kieModule.getReleaseId());
        return kieContainer;
        // return null;
    }
}
