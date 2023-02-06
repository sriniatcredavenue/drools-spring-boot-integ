package com.example.model;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Deal {
    private Integer id;
    private Integer customerId;
    private Integer amount;
    private Integer tenor;
    private Integer roi;
}
