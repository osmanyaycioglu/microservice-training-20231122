package com.micro.training.msagreement.integration;

import com.micro.training.msclaimapi.IClaimController;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "CLAIM",contextId = "abc")
public interface IClaimFeignClient extends IClaimController {
}
