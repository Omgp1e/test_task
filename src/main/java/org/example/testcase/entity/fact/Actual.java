package org.example.testcase.entity.fact;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.example.testcase.entity.ref.Customer;
import org.example.testcase.entity.ref.Product;
import org.example.testcase.model.enums.PromoFlag;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "actuals", schema = "fact")
public class Actual {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "actuals_seq")
    @SequenceGenerator(name = "actuals_seq", sequenceName = "fact.actuals_seq", allocationSize = 50)
    @Column(name = "id")
    private Long id;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "material_no", nullable = false)
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ch3_ship_to_code", nullable = false)
    private Customer customer;

    @Column(name = "volume_units", nullable = false)
    private Integer volumeUnits;

    @Column(name = "actual_sales_value", nullable = false, precision = 12, scale = 2)
    private BigDecimal actualSalesValue;

    @Enumerated(EnumType.STRING)
    @Column(name = "promo_flag", length = 10)
    private PromoFlag promoFlag;
}
