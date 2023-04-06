package com.example.model;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Deal {
    private Integer id;
    private Integer customerId;
    private Double amount;
    private Double tenor;
    private Double roi;
}