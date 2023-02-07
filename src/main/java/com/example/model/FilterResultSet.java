package com.example.model;

import java.util.ArrayList;

// import lombok.Getter;
// import lombok.Setter;

// @Getter
// @Setter

public class FilterResultSet {
    public ArrayList<Integer> passSet = new ArrayList<Integer>();
    public ArrayList<Integer> failSet = new ArrayList<Integer>();

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
}
