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
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "customers", schema = "ref")
public class Customer {
    @Id
    @Column(name = "ch3_ship_to_code", nullable = false)
    private Long ch3ShipToCode;

    @Column(name = "ch3_ship_to_name", nullable = false)
    private String ch3ShipToName;

    @Column(name = "chain_name", nullable = false, length = 100)
    private String chainName;
}
