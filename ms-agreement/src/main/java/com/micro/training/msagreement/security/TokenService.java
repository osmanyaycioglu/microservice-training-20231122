package com.micro.training.msagreement.security;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
public class TokenService {
    private Map<String, TokenObject> tokenObjectMap = new ConcurrentHashMap<>(10_000,
                                                                              0.9f,
                                                                              100);


    @Scheduled(fixedDelayString = "${app.token.clear.period}")
    public void clearExpiredTokens(){
        LocalDateTime nowDate = LocalDateTime.now();
        List<TokenObject> collectLoc = tokenObjectMap.values()
                                                     .stream()
                                                     .filter(to -> to.getExpirationDate()
                                                                     .isBefore(nowDate))
                                                     .collect(Collectors.toList());
        collectLoc.forEach(to -> tokenObjectMap.remove(to.getToken()));
    }

    @Scheduled(cron = "*/10 * * * * *")
    public void cronTest(){
        System.out.println("Schedule executed");
    }

}
