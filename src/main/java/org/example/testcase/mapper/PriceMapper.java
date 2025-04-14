package org.example.testcase.mapper;

import org.example.testcase.dto.PriceRequestDto;
import org.example.testcase.dto.PriceResponseDto;
import org.example.testcase.entity.ref.Price;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring")
public interface PriceMapper {

    PriceMapper INSTANCE = Mappers.getMapper(PriceMapper.class);

    Price toEntity(PriceRequestDto dto);

    PriceResponseDto toDto(Price entity);
}