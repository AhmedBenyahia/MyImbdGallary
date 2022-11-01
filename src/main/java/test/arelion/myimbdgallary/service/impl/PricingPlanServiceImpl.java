package test.arelion.myimbdgallary.service.impl;

import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import org.springframework.stereotype.Service;
import test.arelion.myimbdgallary.model.constant.PricingPlan;
import test.arelion.myimbdgallary.service.PricingPlanService;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class PricingPlanServiceImpl implements PricingPlanService {

    private final Map<String, Bucket> cache = new ConcurrentHashMap<>();

    @Override
    public Bucket resolveBucket(String apiKey) {
        return cache.computeIfAbsent(apiKey, this::newBucket);
    }

    @Override
    public Bucket newBucket(String apiKey) {
        PricingPlan pricingPlan = PricingPlan.resolvePlanFromApiKey(apiKey);
        return Bucket4j.builder()
                .addLimit(pricingPlan.getBandwidth())
                .build();
    }
}
