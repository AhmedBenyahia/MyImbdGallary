package test.arelion.myimbdgallary.util;

import test.arelion.myimbdgallary.model.constant.ImdbConstant;

import java.util.UUID;

public class CommonFunction {
    /**
     * Generate api key string.
     * The method use a UUID with a prefix to make sure the key is unique.
     * The prefix define the type of the pricing plan
     *
     * @param pricingPlan the pricing plan name as upper case string
     * @return the generated apiKey
     */
    public static String generateApiKey(String pricingPlan) {
        UUID uuid = UUID.randomUUID();
        if (pricingPlan.equals(ImdbConstant.businessPricingPlan)) {
            return ImdbConstant.businessPricingPlanApiKeyPrefix + uuid;
        } else if (pricingPlan.equals(ImdbConstant.premiumPricingPlan)) {
            return ImdbConstant.premiumPricingPlanApiKeyPrefix + uuid;
        } else {
            return ImdbConstant.freePricingPlanApiKeyPrefix + uuid;
        }
    }
}
