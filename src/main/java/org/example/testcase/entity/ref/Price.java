package org.example.testcase.entity.ref;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "price", schema = "ref")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@IdClass(PriceId.class)
public class Price {
    @Id
    @Column(name = "chain_name", nullable = false, length = 100)
    private String chainName;

    @Id
    @Column(name = "material_no", nullable = false)
    private Long materialNo;

    @Column(name = "regular_price_per_unit", nullable = false, precision = 10, scale = 3)
    private BigDecimal regularPricePerUnit;
}
