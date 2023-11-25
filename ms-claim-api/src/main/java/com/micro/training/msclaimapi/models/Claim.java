package com.micro.training.msclaimapi.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Claim {
    private String        fileNo;
    private BigDecimal    amount;
    private LocalDateTime claimDate;
}
