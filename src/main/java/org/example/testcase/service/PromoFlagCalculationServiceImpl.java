package org.example.testcase.service;

import lombok.RequiredArgsConstructor;
import org.example.testcase.common.annotation.LogExecution;
import org.example.testcase.repository.ActualRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PromoFlagCalculationServiceImpl implements PromoFlagCalculationService {

    private final ActualRepository actualRepository;

    @Transactional
    @LogExecution
    @Override
    public void updateAllPromoFlags() {
        actualRepository.updatePromoFlags();
    }

    @Override
    public void recalculatePromoFlagsFor(String chainName, Long materialNo) {
        actualRepository.updatePromoFlagsForProduct(chainName, materialNo);
    }
}
