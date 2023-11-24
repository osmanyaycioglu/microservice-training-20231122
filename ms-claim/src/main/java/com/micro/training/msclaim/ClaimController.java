package com.micro.training.msclaim;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/claim/management")
public class ClaimController {

    @Value("${server.port}") // For load balancing info
    private Integer localPort;

    @PostMapping("/create")
    public ClaimCreateResponse create(@RequestBody Claim claimParam) {
        return new ClaimCreateResponse(UUID.randomUUID()
                                           .toString(),
                                       "claim:" + claimParam.getFileNo() + " created " + localPort);
    }

}
