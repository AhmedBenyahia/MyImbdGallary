package test.arelion.myimbdgallary.model.constant;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Refill;
import lombok.Getter;

import java.time.Duration;


@Getter
public enum PricingPlan {
    FREE (Bandwidth.classic(5, Refill.intervally(5, Duration.ofMinutes(1)))),
    BASIC (Bandwidth.classic(100, Refill.intervally(100, Duration.ofMinutes(1)))),
    PROFESSIONAL (Bandwidth.classic(1000, Refill.intervally(1000, Duration.ofMinutes(1))));

    private final Bandwidth bandwidth;

    PricingPlan(Bandwidth bandwidth) {
        this.bandwidth = bandwidth;
    }

    /**
     * Resolve pricing plan from api key.
     * This method use the prefix of the key to identify the type of the plan
     *
     * @param apiKey the api key
     * @return the pricing plan
     */
    public static PricingPlan resolvePlanFromApiKey(String apiKey) {
        if (apiKey.startsWith(ImdbConstant.freePricingPlanApiKeyPrefix)) {
            return FREE;
        } else if (apiKey.startsWith(ImdbConstant.premiumPricingPlanApiKeyPrefix)) {
            return PROFESSIONAL;
        } else if (apiKey.startsWith(ImdbConstant.businessPricingPlanApiKeyPrefix)) {
            return BASIC;
        }
        return FREE;
    }
}