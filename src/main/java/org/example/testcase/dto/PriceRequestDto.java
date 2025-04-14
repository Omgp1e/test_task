package org.example.testcase.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PriceRequestDto {
    @NotBlank
    private String chainName;

    @NotNull
    private Long materialNo;

    @NotNull
    @DecimalMin("0.0")
    private BigDecimal regularPricePerUnit;
}
