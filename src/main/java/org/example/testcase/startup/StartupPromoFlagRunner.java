package org.example.testcase.startup;

import lombok.*;
import org.example.testcase.service.PromoFlagCalculationService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StartupPromoFlagRunner implements ApplicationRunner {

    private final PromoFlagCalculationService promoFlagCalculationService;

    @Override
    public void run(ApplicationArguments args) {
        promoFlagCalculationService.updateAllPromoFlags();
    }
}
