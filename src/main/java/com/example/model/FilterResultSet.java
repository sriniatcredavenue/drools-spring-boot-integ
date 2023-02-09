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

    public void setMap(Integer investor_id, Integer passedCustomers){
        ArrayList<Integer> newPassedCustomers = new ArrayList<Integer>();
        newPassedCustomers.add(0, passedCustomers);
        if(this.map.containsKey(investor_id)){
            Set<Integer> old_result = new HashSet<Integer>(this.map.get(investor_id));
            Set<Integer> new_result = new HashSet<Integer>(newPassedCustomers);
            old_result.retainAll(new_result);
            // Integer[] result = old_result.toArray(new Integer[old_result.size()]);
            ArrayList<Integer> result = new ArrayList<Integer>();
            result.addAll(old_result);
            this.map.replace(investor_id, result);
        }
        else{
            this.map.put(investor_id, newPassedCustomers);
        }
    }

    public void viewMap(){
        for(Map.Entry<Integer, ArrayList<Integer> > entry:map.entrySet()){    
            int key=entry.getKey();  
            ArrayList<Integer> value=entry.getValue();  
            System.out.println("Filtered output of" + key + "is" + value);
        }
    }

    public ArrayList<Integer> getMapValue(Integer investor_id){
        return this.map.get(investor_id);
    }
}
