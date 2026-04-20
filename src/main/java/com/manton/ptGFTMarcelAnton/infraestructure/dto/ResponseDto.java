package com.manton.ptGFTMarcelAnton.infraestructure.dto;

import lombok.Builder;

@Builder
public record ResponseDto(String productId,
                          String brandId,
                          Integer priceGroup,
                          String startDate,
                          String endDate,
                          Double price) {
}
