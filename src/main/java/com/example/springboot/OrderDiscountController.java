// package com.example.springboot;

 
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RestController;
 
// import com.example.model.OrderDiscount;
// import com.example.model.OrderRequest;
// import com.example.service.OrderDiscountService;
 
// @RestController
// public class OrderDiscountController {
//     @Autowired
//     private OrderDiscountService orderDiscountService;
//     @PostMapping("/get-discount")
//     public ResponseEntity<OrderDiscount> getDiscount(@RequestBody OrderRequest orderRequest) {
//         System.out.println("Hello" + orderRequest);
//         OrderDiscount discount = orderDiscountService.getDiscount(orderRequest);
//         return new ResponseEntity<>(discount, HttpStatus.OK);
//     }
// }