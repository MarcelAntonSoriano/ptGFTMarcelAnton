package com.manton.ptGFTMarcelAnton.application.usecase;


import com.manton.ptGFTMarcelAnton.application.object.ProductReq;
import com.manton.ptGFTMarcelAnton.application.object.ProductRes;
import com.manton.ptGFTMarcelAnton.domain.service.IPricesService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class GetPrice {

    @Qualifier("IPricesService")
    private final IPricesService pricesService;

    public ProductRes getProduct(ProductReq productReq) {
        List<ProductRes> products = pricesService.getProductService(productReq);
        return products.stream()
                .max(Comparator.comparing(ProductRes::getPriority))
                .orElseThrow(() -> new NoSuchElementException("No product found"));
    }
}
