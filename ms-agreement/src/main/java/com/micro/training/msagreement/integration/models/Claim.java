package com.micro.training.msagreement.integration.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Claim {
    private String        fileNo;
    private BigDecimal    amount;
    private LocalDateTime claimDate;
}
