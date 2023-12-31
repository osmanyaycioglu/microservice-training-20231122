package com.micro.training.msclaimapi.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClaimCreateResponse {
    private String id;
    private String result;
}
