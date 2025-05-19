package com.easybank.accounts.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Account extends  BaseEntity{
    @Id
    @Column(name = "account_number")
    private Long accountNumber;

    @Column(name= "customer_id")
    private Long customerId;

    @Column(name = "account_type")
    private String accountType;

    @Column(name = "branch_address")
    private String BranchAddress;


}
