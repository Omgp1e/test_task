package org.example.testcase.repository;

import org.example.testcase.entity.fact.Actual;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ActualRepository extends JpaRepository<Actual, Long> {
    @Modifying
    @Query(value = """
        UPDATE fact.actuals a
        SET promo_flag = CASE
            WHEN (a.actual_sales_value / NULLIF(a.volume_units, 0)) < p.regular_price_per_unit
            THEN 'PROMO'
            ELSE 'REGULAR'
        END
        FROM ref.customers c,
             ref.price p
        WHERE p.chain_name = c.chain_name
            AND p.material_no = a.material_no
            AND a.ch3_ship_to_code = c.ch3_ship_to_code
        """, nativeQuery = true)
    void updatePromoFlags();

    @Modifying
    @Query(value = """
        UPDATE fact.actuals a
        SET promo_flag = CASE
            WHEN NULLIF(a.actual_sales_value, 0) IS NULL\s
                 OR NULLIF(a.volume_units, 0) IS NULL THEN 'REGULAR'
            WHEN (a.actual_sales_value / a.volume_units) < p.regular_price_per_unit THEN 'PROMO'
            ELSE 'REGULAR'
        END
        FROM ref.customers c, ref.price p
        WHERE c.chain_name = p.chain_name
            AND p.material_no = a.material_no
            AND a.ch3_ship_to_code = c.ch3_ship_to_code
            AND p.chain_name = :chainName
            AND p.material_no = :materialNo
        """, nativeQuery = true)
    void updatePromoFlagsForProduct(@Param("chainName") String chainName, @Param("materialNo") Long materialNo);

    @Query(value = """
        SELECT 
            c.chain_name AS chainName,
            p.l3_product_category_name AS categoryName,
            DATE_TRUNC('month', a.date) AS month,
            SUM(CASE WHEN a.promo_flag = 'REGULAR' THEN a.volume_units ELSE 0 END) AS regularUnits,
            SUM(CASE WHEN a.promo_flag = 'PROMO' THEN a.volume_units ELSE 0 END) AS promoUnits,
            SUM(CASE WHEN a.promo_flag = 'REGULAR' THEN a.actual_sales_value ELSE 0 END) AS regularSales,
            SUM(CASE WHEN a.promo_flag = 'PROMO' THEN a.actual_sales_value ELSE 0 END) AS promoSales,
            CASE 
                WHEN SUM(a.actual_sales_value) > 0 
                THEN ROUND(100.0 * 
                        SUM(CASE WHEN a.promo_flag = 'PROMO' THEN a.actual_sales_value ELSE 0 END) / 
                                SUM(a.actual_sales_value), 2)
                ELSE 0
            END AS promoSharePercent
        FROM fact.actuals a
        JOIN ref.customers c ON a.ch3_ship_to_code = c.ch3_ship_to_code
        JOIN ref.products p ON a.material_no = p.material_no
        GROUP BY c.chain_name, p.l3_product_category_name, DATE_TRUNC('month', a.date)
        """, nativeQuery = true)
    List<Object[]> findMonthlySalesAggregated();

    @Query(value = """
        SELECT 
            a.date,
            c.chain_name,
            p.material_desc_rus,
            a.volume_units,
            a.actual_sales_value,
            a.promo_flag
        FROM fact.actuals a
        JOIN ref.customers c ON a.ch3_ship_to_code = c.ch3_ship_to_code
        JOIN ref.products p ON a.material_no = p.material_no
        WHERE c.chain_name IN (:chains)
          AND p.material_desc_rus IN (:products)
          AND a.date BETWEEN :startDate AND :endDate
        ORDER BY a.date
        """, nativeQuery = true)
    List<Object[]> findSalesByDay(
            @Param("chains") List<String> chains,
            @Param("products") List<String> products,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );
}
