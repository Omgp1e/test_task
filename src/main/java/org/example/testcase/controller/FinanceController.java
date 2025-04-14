package org.example.testcase.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.testcase.dto.PriceRequestDto;
import org.example.testcase.dto.PriceResponseDto;
import org.example.testcase.entity.ref.PriceId;
import org.example.testcase.service.PriceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/prices")
@RequiredArgsConstructor
public class FinanceController {

    private final PriceService priceService;

    @PostMapping
    public ResponseEntity<PriceResponseDto> createPrice(@Valid @RequestBody PriceRequestDto requestDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(priceService.createPrice(requestDto));
    }

    @GetMapping("/{chainName}/{materialNo}")
    public ResponseEntity<PriceResponseDto> getPriceById(
            @PathVariable String chainName,
            @PathVariable Long materialNo) {

        PriceId id = new PriceId(chainName, materialNo);
        return ResponseEntity.ok(priceService.getPrice(id));
    }

    @GetMapping
    public ResponseEntity<List<PriceResponseDto>> getAllPrices() {
        return ResponseEntity.ok(priceService.getAllPrices());
    }

    @PutMapping("/{chainName}/{materialNo}")
    public ResponseEntity<PriceResponseDto> updatePrice(
            @PathVariable String chainName,
            @PathVariable Long materialNo,
            @Valid @RequestBody PriceRequestDto requestDto) {

        PriceId id = new PriceId(chainName, materialNo);
        return ResponseEntity.ok(priceService.updatePrice(id, requestDto));
    }

    @DeleteMapping("/{chainName}/{materialNo}")
    public ResponseEntity<Void> deletePrice(
            @PathVariable String chainName,
            @PathVariable Long materialNo) {

        PriceId id = new PriceId(chainName, materialNo);
        priceService.deletePrice(id);
        return ResponseEntity.noContent().build();
    }
}
