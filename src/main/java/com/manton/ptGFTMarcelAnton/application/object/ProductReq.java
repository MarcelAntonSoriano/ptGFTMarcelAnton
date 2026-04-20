package com.manton.ptGFTMarcelAnton.application.object;


import lombok.Builder;

@Builder
public record ProductReq(String brandId,
                         String productId,
                         String date) {
}
