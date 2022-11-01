package test.arelion.myimbdgallary.configuration;

import io.github.bucket4j.Bucket;
import io.github.bucket4j.ConsumptionProbe;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import test.arelion.myimbdgallary.model.constant.ImdbConstant;
import test.arelion.myimbdgallary.service.AuthService;
import test.arelion.myimbdgallary.service.PricingPlanService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class RateLimitInterceptor implements HandlerInterceptor {

    private final PricingPlanService pricingPlanService;

    private final AuthService authService;

    public RateLimitInterceptor(PricingPlanService pricingPlanService, @Lazy AuthService authService) {
        this.pricingPlanService = pricingPlanService;
        this.authService = authService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String apiKey = request.getHeader(ImdbConstant.rateLimitApiKeyHeader);
        if (apiKey == null) {
            apiKey = "";
        }
        if (!authService.getUserApiKey(request.getRemoteUser()).equals(apiKey)) {
            response.sendError(HttpStatus.FORBIDDEN.value(),
                    "Your Api Key is invalid");
        }
        Bucket tokenBucket = pricingPlanService.resolveBucket(apiKey);
        ConsumptionProbe probe = tokenBucket.tryConsumeAndReturnRemaining(1);
        if (probe.isConsumed()) {
            response.addHeader(ImdbConstant.rateLimitRemainingHeader, String.valueOf(probe.getRemainingTokens()));
            return true;
        } else {
            long waitForRefill = probe.getNanosToWaitForRefill() / 1_000_000_000;
            response.addHeader(ImdbConstant.rateLimitRetryAfterHeader, String.valueOf(waitForRefill));
            response.sendError(HttpStatus.TOO_MANY_REQUESTS.value(),
                    "You have exhausted your API Request Quota");
            return false;
        }
    }
}
