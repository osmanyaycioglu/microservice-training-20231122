package com.micro.training.msagreement.integration;

import com.micro.training.msclaimapi.models.Claim;
import com.micro.training.msclaimapi.models.ClaimCreateResponse;
import com.micro.training.mscommon.error.ErrorObj;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.netflix.eureka.EurekaDiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClaimIntegration {
    private static final Logger                logger = LoggerFactory.getLogger(ClaimIntegration.class);
    private final        RestTemplate          restTemplate;
    private final        EurekaDiscoveryClient discoveryClient;
    private final        IClaimFeignClient     claimFeignClient;

    @Retry(name = "claimRetry",fallbackMethod = "createClaimFeignFallback")
    @CircuitBreaker(name = "claimCC")
    public ClaimCreateResponse createClaimFeign(Claim claimParam) {
        return claimFeignClient.create(claimParam);
    }

    public ClaimCreateResponse createClaimFeignFallback(Claim claimParam,
                                                        Throwable throwableParam) {
        return new ClaimCreateResponse();
    }


    public ClaimCreateResponse createClaim(Claim claimParam) {

        try {
            ClaimCreateResponse claimCreateResponseLoc = restTemplate.postForObject("http://CLAIM/api/v1/claim/management/create",
                                                                                    claimParam,
                                                                                    ClaimCreateResponse.class);
            return claimCreateResponseLoc;
        } catch (RestClientResponseException eParam) {
            try {
                ErrorObj responseBodyAsLoc = eParam.getResponseBodyAs(ErrorObj.class);

                throw new IllegalStateException();
            } catch (Exception exParam) {
                logger.error("[ClaimIntegration][createClaim]-> *Error* : " + exParam.getMessage(),
                             exParam);
                throw exParam;
            }
        } catch (Exception exceptionParam) {
            logger.error("[ClaimIntegration][createClaim]-> *Error* : " + exceptionParam.getMessage(),
                         exceptionParam);
            throw exceptionParam;
        }
    }

    private int index;

    public ClaimCreateResponse createClaim2(Claim claimParam) {
        index++;
        List<ServiceInstance> claimLoc = discoveryClient.getInstances("CLAIM");
        for (ServiceInstance instanceLoc : claimLoc) {
        }
        return null;
    }

}
