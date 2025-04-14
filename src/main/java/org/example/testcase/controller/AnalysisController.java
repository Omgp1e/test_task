package org.example.testcase.controller;

import lombok.RequiredArgsConstructor;
import org.example.testcase.dto.analysis.SalesByDayDto;
import org.example.testcase.dto.analysis.SalesByMonthDto;
import org.example.testcase.service.AnalysisService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/analysis")
@RequiredArgsConstructor
public class AnalysisController {

    private final AnalysisService analysisService;

    @GetMapping("/sales/monthly")
    public List<SalesByMonthDto> getSalesByMonth() {
        return analysisService.getSalesByMonth();
    }

    @GetMapping("/sales/daily")
    public List<SalesByDayDto> getSalesByDay(
            @RequestParam List<String> chainNames,
            @RequestParam List<String> productNames,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate
    ) {
        return analysisService.getSalesByDay(chainNames, productNames, startDate, endDate);
    }
}