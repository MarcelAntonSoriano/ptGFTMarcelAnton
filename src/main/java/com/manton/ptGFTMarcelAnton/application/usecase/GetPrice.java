package com.manton.ptGFTMarcelAnton.application.usecase;


import com.manton.ptGFTMarcelAnton.application.object.ProductRes;
import com.manton.ptGFTMarcelAnton.domain.dto.RequestDto;
import com.manton.ptGFTMarcelAnton.domain.dto.ResponseDto;
import com.manton.ptGFTMarcelAnton.infraestructure.mapper.ProductMapper;
import com.manton.ptGFTMarcelAnton.infraestructure.service.IPricesService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class GetPrice {

    private final ProductMapper productMapper;
    @Qualifier("IPricesService")
    private final IPricesService pricesService;

    public ResponseDto getProduct(RequestDto requestDto) {

        List<ProductRes> products = pricesService.getProductService(productMapper.mapRequestDtoToProductReq(requestDto));

        return productMapper.mapProductRestoResponseDto(products.stream()
                .max(Comparator.comparing(ProductRes::getPriority))
                .orElseThrow(() -> new NoSuchElementException("No product found")));

    }
}
