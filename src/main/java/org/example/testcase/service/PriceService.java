package org.example.testcase.service;

import org.example.testcase.dto.PriceRequestDto;
import org.example.testcase.dto.PriceResponseDto;
import org.example.testcase.entity.ref.PriceId;

import java.util.List;

public interface PriceService {
    PriceResponseDto getPrice(PriceId id);
    List<PriceResponseDto> getAllPrices();
    PriceResponseDto createPrice(PriceRequestDto request);
    PriceResponseDto updatePrice(PriceId id, PriceRequestDto request);
    void deletePrice(PriceId id);
}
