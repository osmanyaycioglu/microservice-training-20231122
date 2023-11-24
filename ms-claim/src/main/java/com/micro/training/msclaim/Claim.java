package com.micro.training.msclaim;


import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class Claim {
    private String        fileNo;
    private BigDecimal    amount;
    private LocalDateTime claimDate;
}
