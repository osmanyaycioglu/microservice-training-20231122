package com.micro.training.msagreement.integration.error;

import com.micro.training.msclaimapi.models.ClaimCreateResponse;

import java.util.function.Predicate;

public class ClaimRetryPredicate implements Predicate<ClaimCreateResponse> {

    @Override
    public boolean test(final ClaimCreateResponse claimCreateResponseParam) {
        String idLoc = claimCreateResponseParam.getId();
        if (idLoc == null) {
            return true;
        }
        return false;
    }

}
