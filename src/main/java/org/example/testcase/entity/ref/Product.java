package org.example.testcase.entity.ref;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "products", schema = "ref")
public class Product {
    @Id
    @Column(name = "material_no", nullable = false)
    private Long materialNo;

    @Column(name = "material_desc_rus", nullable = false)
    private String materialDescRus;

    @Column(name = "l3_product_category_code", nullable = false)
    private Long l3ProductCategoryCode;

    @Column(name = "l3_product_category_name", nullable = false, length = 100)
    private String l3ProductCategoryName;
}
