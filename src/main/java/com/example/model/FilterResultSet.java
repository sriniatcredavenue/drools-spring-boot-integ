package com.example.model;

import java.util.*;

// import lombok.Getter;
// import lombok.Setter;

// @Getter
// @Setter

public class FilterResultSet {
    public ArrayList<Integer> passSet = new ArrayList<Integer>();
    public ArrayList<Integer> failSet = new ArrayList<Integer>();
    public HashMap<Integer,ArrayList<Integer> > map= new HashMap<Integer,ArrayList<Integer> >();

    // public void setPassSet(){
    //     this.passSet = new ArrayList<Integer>();
    // }

    public ArrayList<Integer> getPassSet(){
        return this.passSet;
    }

    public ArrayList<Integer> getFailSet(){
        return this.failSet;
    }

    public void setVariable(int var){
        this.passSet.add(var);
        // return passSet;
    }

    public void addToMap(Integer investor_id, Integer passedCustomers){
        if(!this.map.containsKey(investor_id)){
            this.map.put(investor_id, new ArrayList<Integer>());
        }
        this.map.get(investor_id).add(passedCustomers);
    }

    public void setMap(Integer investor_id, Integer passedCustomers){
        ArrayList<Integer> old_result = this.map.get(investor_id);
        if(!old_result.contains(passedCustomers)){
            old_result.remove(passedCustomers);
        }
        // System.out.println("-------hello " + old_result + "------");
        // ArrayList<Integer> newPassedCustomers = new ArrayList<Integer>();
        // newPassedCustomers.add(0, passedCustomers);
        // if(this.map.containsKey(investor_id)){
        //     ArrayList<Integer> old_result = this.map.get(investor_id);
        //     ArrayList<Integer> new_result = newPassedCustomers;
        //     new_result.retainAll(old_result);
        //     old_result.addAll(new_result);
        //     System.out.println("-------hello " + old_result + "------");
        //     this.map.replace(investor_id, old_result);
        // }
        // else{
        //     this.map.put(investor_id, newPassedCustomers);
        // }
    }

    public void viewMap(){
        for(Map.Entry<Integer, ArrayList<Integer> > entry:this.map.entrySet()){    
            int key=entry.getKey();  
            ArrayList<Integer> value=entry.getValue();  
            System.out.println("Filtered output of" + key + "is" + value);
        }
    }

    public ArrayList<Integer> getMapValue(Integer investor_id){
        return this.map.get(investor_id);
    }
}
