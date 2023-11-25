package com.micro.training.msagreement.integration.error;

import com.micro.training.msclaimapi.models.Claim;
import com.micro.training.msclaimapi.models.ClaimCreateResponse;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class BeanA {

    private int count = 0;


    @Retry(name = "claimRetry")
    public ClaimCreateResponse callMe() {
        count++;
        if (count % 3 == 0) {
            throw new IllegalStateException();
        }
        if (count > 15){
            throw new IllegalStateException();
        }
        return new ClaimCreateResponse(UUID.randomUUID()
                                           .toString(),
                                       "Counter " + count);
    }

    public ClaimCreateResponse createClaimFeignFallback(Claim claimParam,
                                                        Throwable throwableParam) {
        return new ClaimCreateResponse();
    }
}
