package org.example.testcase.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PriceResponseDto {
    private String chainName;
    private Long materialNo;
    private BigDecimal regularPricePerUnit;
}
