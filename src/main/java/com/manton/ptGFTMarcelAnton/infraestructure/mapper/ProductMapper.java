package com.manton.ptGFTMarcelAnton.infraestructure.mapper;

import com.manton.ptGFTMarcelAnton.application.object.ProductReq;
import com.manton.ptGFTMarcelAnton.application.object.ProductRes;
import com.manton.ptGFTMarcelAnton.infraestructure.dto.RequestDto;
import com.manton.ptGFTMarcelAnton.infraestructure.dto.ResponseDto;
import com.manton.ptGFTMarcelAnton.infraestructure.entity.PRICES;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;

@Service
public class ProductMapper {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public ProductReq mapRequestDtoToProductReq(RequestDto requestDto) {


        return ProductReq.builder()
                .brandId(requestDto.brandId())
                .productId(requestDto.productId())
                .date(requestDto.date())
                .build();
    }

    public ResponseDto mapProductRestoResponseDto(ProductRes productRes) {
        return ResponseDto.builder()
                .productId(productRes.getProductId())
                .brandId(productRes.getBrandId())
                .priceGroup(productRes.getPriceGroup())
                .startDate(productRes.getStartDate())
                .endDate(productRes.getEndDate())
                .price(productRes.getPrice()).build();

    }

    public ProductRes mapPricesEntitytoProductRes(PRICES prices) {
        return ProductRes.builder()
                .productId(prices.getProductId())
                .brandId(prices.getBrandId())
                .priceGroup(prices.getPriceList())
                .startDate(prices.getStartDate().format(formatter))
                .endDate(prices.getEndDate().format(formatter))
                .priority(prices.getPriority())
                .price(prices.getPrice()).build();
    }
}
