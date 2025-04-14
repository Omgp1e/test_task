package org.example.testcase.service;

import org.example.testcase.dto.analysis.SalesByDayDto;
import org.example.testcase.dto.analysis.SalesByMonthDto;

import java.time.*;
import java.util.List;

public interface AnalysisService {
    List<SalesByMonthDto> getSalesByMonth();
    List<SalesByDayDto> getSalesByDay(List<String> chains, List<String> products, LocalDate start, LocalDate end);
}
