package org.example.testcase.repository;

import org.example.testcase.entity.ref.Price;
import org.example.testcase.entity.ref.PriceId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceRepository extends JpaRepository<Price, PriceId> {
}
