package com.easybank.accounts.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter @Getter @ToString @AllArgsConstructor @NoArgsConstructor
public class Customer extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "customer_id")
    private Long customerId;
    private String name;
    private String email;
    @Column(name = "mobile_number")
    private String mobileNumber;
}
