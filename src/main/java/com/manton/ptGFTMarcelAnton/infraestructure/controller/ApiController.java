package com.manton.ptGFTMarcelAnton.infraestructure.controller;

import com.manton.ptGFTMarcelAnton.application.object.ProductReq;
import com.manton.ptGFTMarcelAnton.application.object.ProductRes;
import com.manton.ptGFTMarcelAnton.application.usecase.GetPrice;
import com.manton.ptGFTMarcelAnton.infraestructure.dto.RequestDto;
import com.manton.ptGFTMarcelAnton.infraestructure.dto.ResponseDto;
import com.manton.ptGFTMarcelAnton.infraestructure.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ApiController {

    private final GetPrice getPrice;
    private final ProductMapper productMapper;


    @GetMapping("/getProduct")
    public ResponseDto getProducts(@RequestBody RequestDto requestDto) {
        ProductReq productReq = productMapper.mapRequestDtoToProductReq(requestDto);
        ProductRes productRes = getPrice.getProduct(productReq);
        return productMapper.mapProductRestoResponseDto(productRes);    }

}
