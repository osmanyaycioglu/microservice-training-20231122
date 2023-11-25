package com.micro.training.msagreement.integration.error;

import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class RunnerTest implements CommandLineRunner {
    @Autowired
    private BeanA beanA;

    @Autowired
    private RetryRegistry retryRegistry;

    @Autowired
    private CircuitBreakerRegistry circuitBreakerRegistry;

    @Override
    public void run(final String... args) throws Exception {
        Retry                claimRetryLoc     = retryRegistry.retry("claimRetry");
        Retry.Metrics        metricsLoc        = claimRetryLoc.getMetrics();
        Retry.EventPublisher eventPublisherLoc = claimRetryLoc.getEventPublisher();
        eventPublisherLoc.onRetry(e -> System.out.println("****** Retrying : " + e));
        for (int i = 0; i < 20; i++) {
            System.out.println("Calling A : " + i);

            try {
                beanA.callMe();
            } catch (Exception eParam) {
                System.out.println("Error : " + eParam.getMessage());
            }
            System.out.println("Retry : s: "
                               + metricsLoc.getNumberOfSuccessfulCallsWithoutRetryAttempt()
                               + " f : "
                               + metricsLoc.getNumberOfFailedCallsWithoutRetryAttempt()
                               + " sr : "
                               + metricsLoc.getNumberOfSuccessfulCallsWithRetryAttempt()
                               + " fr : "
                               + metricsLoc.getNumberOfFailedCallsWithRetryAttempt()

            );
        }
    }
}
