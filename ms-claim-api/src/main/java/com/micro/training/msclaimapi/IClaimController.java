package com.micro.training.msclaimapi;


import com.micro.training.msclaimapi.models.Claim;
import com.micro.training.msclaimapi.models.ClaimCreateResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public interface IClaimController {

    @PostMapping("/api/v1/claim/management/create")
    public ClaimCreateResponse create(@RequestBody Claim claimParam);

    @GetMapping("/api/v1/claim/management/remove")
    public String remove(@RequestParam String claimId);

}
