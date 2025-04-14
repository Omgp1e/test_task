package org.example.testcase.service;

import jakarta.validation.constraints.*;
import lombok.*;
import org.example.testcase.dto.analysis.SalesByDayDto;
import org.example.testcase.dto.analysis.SalesByMonthDto;
import org.example.testcase.model.enums.PromoFlag;
import org.example.testcase.repository.ActualRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static org.example.testcase.util.RowMapperUtil.date;
import static org.example.testcase.util.RowMapperUtil.decimal;
import static org.example.testcase.util.RowMapperUtil.longVal;
import static org.example.testcase.util.RowMapperUtil.str;
import static org.example.testcase.util.RowMapperUtil.yearMonth;

@Service
@RequiredArgsConstructor
public class AnalysisServiceImpl implements AnalysisService {

    private final ActualRepository actualRepository;

    @Override
    @Transactional(readOnly = true)
    public List<SalesByMonthDto> getSalesByMonth() {
        return actualRepository.findMonthlySalesAggregated().stream()
                .map(this::mapToSalesByMonthDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<SalesByDayDto> getSalesByDay(
            List<String> chains,
            List<String> products,
            LocalDate start,
            LocalDate end) {
        return actualRepository.findSalesByDay(chains, products, start, end).stream()
                .map(this::mapToSalesByDayDto)
                .toList();
    }

    private SalesByMonthDto mapToSalesByMonthDto(Object[] row) {
        return new SalesByMonthDto(
                str(row, 0),
                str(row, 1),
                yearMonth(row, 2),
                longVal(row, 3),
                longVal(row, 4),
                decimal(row, 5),
                decimal(row, 6),
                decimal(row, 7)
        );
    }

    private SalesByDayDto mapToSalesByDayDto(Object[] row) {
        return new SalesByDayDto(
                date(row, 0),
                str(row, 1),
                str(row, 2),
                longVal(row, 3),
                decimal(row, 4),
                PromoFlag.valueOf(str(row, 5).toUpperCase())
        );
    }
}
