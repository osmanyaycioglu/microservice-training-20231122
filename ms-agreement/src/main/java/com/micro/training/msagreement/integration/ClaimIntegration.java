package com.micro.training.msagreement.integration;

import com.micro.training.msagreement.integration.models.Claim;
import com.micro.training.msagreement.integration.models.ClaimCreateResponse;
import com.micro.training.mscommon.error.ErrorObj;
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
    private static final Logger logger = LoggerFactory.getLogger(ClaimIntegration.class);
    private final RestTemplate restTemplate;
    private final EurekaDiscoveryClient discoveryClient;


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
                logger.error("[ClaimIntegration][createClaim]-> *Error* : " + exParam.getMessage(),exParam);
                throw exParam;
            }
        } catch (Exception exceptionParam){
            logger.error("[ClaimIntegration][createClaim]-> *Error* : " + exceptionParam.getMessage(),exceptionParam);
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
