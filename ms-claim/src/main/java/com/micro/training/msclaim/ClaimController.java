package com.micro.training.msclaim;

import com.micro.training.msclaimapi.IClaimController;
import com.micro.training.msclaimapi.models.Claim;
import com.micro.training.msclaimapi.models.ClaimCreateResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class ClaimController implements IClaimController {

    @Value("${server.port}") // For load balancing info
    private Integer localPort;

    @Override
    public String remove(final String claimId) {
        return "Claim : " + claimId + " removed";
    }

    @Override
    public ClaimCreateResponse create(final Claim claimParam) {
        return new ClaimCreateResponse(UUID.randomUUID()
                                           .toString(),
                                       "claim:" + claimParam.getFileNo() + " created " + localPort);
    }
}
