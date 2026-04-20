package com.manton.ptGFTMarcelAnton.application.object;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public final class ProductRes {
    private final String productId;
    private final String brandId;
    private final Integer priceGroup;
    private final String startDate;
    private final String endDate;
    private final Double price;
    private final Integer priority;
}
