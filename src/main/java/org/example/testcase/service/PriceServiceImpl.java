package org.example.testcase.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.testcase.dto.PriceRequestDto;
import org.example.testcase.dto.PriceResponseDto;
import org.example.testcase.entity.ref.Price;
import org.example.testcase.entity.ref.PriceId;
import org.example.testcase.mapper.PriceMapper;
import org.example.testcase.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PriceServiceImpl implements PriceService {

    private final PriceRepository priceRepository;
    private final PromoFlagCalculationService promoFlagCalculationService;
    private final PriceMapper priceMapper;

    @Override
    public PriceResponseDto getPrice(PriceId id) {
        Price price = priceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Price not found for: " + id));
        return priceMapper.toDto(price);
    }

    @Override
    public List<PriceResponseDto> getAllPrices() {
        return priceRepository.findAll().stream()
                .map(priceMapper::toDto)
                .toList();
    }

    @Override
    @Transactional
    public PriceResponseDto createPrice(PriceRequestDto request) {
        Price price = priceMapper.toEntity(request);
        Price saved = priceRepository.save(price);

        promoFlagCalculationService.recalculatePromoFlagsFor(
                saved.getChainName(),
                saved.getMaterialNo()
        );

        return priceMapper.toDto(saved);
    }

    @Override
    @Transactional
    public PriceResponseDto updatePrice(PriceId id, PriceRequestDto request) {
        Price existing = priceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Price not found for: " + id));

        existing.setRegularPricePerUnit(request.getRegularPricePerUnit());

        Price updated = priceRepository.save(existing);

        promoFlagCalculationService.recalculatePromoFlagsFor(
                id.getChainName(),
                id.getMaterialNo()
        );

        return priceMapper.toDto(updated);
    }

    @Override
    @Transactional
    public void deletePrice(PriceId id) {
        priceRepository.deleteById(id);
    }
}