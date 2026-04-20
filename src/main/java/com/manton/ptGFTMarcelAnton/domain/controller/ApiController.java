package com.manton.ptGFTMarcelAnton.domain.controller;

import com.manton.ptGFTMarcelAnton.application.usecase.GetPrice;
import com.manton.ptGFTMarcelAnton.domain.dto.RequestDto;
import com.manton.ptGFTMarcelAnton.domain.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ApiController {

    private final GetPrice getPrice;


    @GetMapping("/getProduct")
    public ResponseDto getProducts(@RequestBody RequestDto requestDto) {
        return getPrice.getProduct(requestDto);
    }

}
