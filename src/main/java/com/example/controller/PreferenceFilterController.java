



package com.example.controller;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
 
import com.example.model.Customer;
import com.example.service.FilterService;
import com.example.service.FilterServiceMultiThread;
 
@RestController
public class PreferenceFilterController {
 
    @Autowired
    private FilterService filterService;
    private FilterServiceMultiThread filterServiceMultiThread;
 
    @PostMapping("/filter")
    public String runFilter() {
        String res = filterService.runFilter();
        // String res = filterServiceMultiThread.runFilter();
        // return new ResponseEntity<>("discount", HttpStatus.OK);
        return res;
    }
}