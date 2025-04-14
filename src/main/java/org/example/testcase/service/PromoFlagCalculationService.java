package org.example.testcase.service;

public interface PromoFlagCalculationService {
    void updateAllPromoFlags();
    void recalculatePromoFlagsFor(String chainName, Long materialNo);
}
