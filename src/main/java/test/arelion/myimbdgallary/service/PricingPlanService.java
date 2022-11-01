package test.arelion.myimbdgallary.service;

import io.github.bucket4j.Bucket;

public interface PricingPlanService {
    Bucket resolveBucket(String apiKey);

    Bucket newBucket(String apiKey);
}
