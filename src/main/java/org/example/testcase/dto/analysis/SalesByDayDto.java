package org.example.testcase.dto.analysis;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.example.testcase.model.enums.PromoFlag;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SalesByDayDto {
    private LocalDate date;
    private String chainName;
    private String productName;
    private Long units;
    private BigDecimal sales;
    private PromoFlag promoFlag;
}
