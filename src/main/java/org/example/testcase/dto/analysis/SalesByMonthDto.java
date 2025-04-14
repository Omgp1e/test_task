package org.example.testcase.dto.analysis;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.YearMonth;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SalesByMonthDto {
    private String chainName;
    private String categoryName;
    private YearMonth month;
    private Long regularUnits;
    private Long promoUnits;
    private BigDecimal regularSales;
    private BigDecimal promoSales;
    private BigDecimal promoSharePercent;
}
