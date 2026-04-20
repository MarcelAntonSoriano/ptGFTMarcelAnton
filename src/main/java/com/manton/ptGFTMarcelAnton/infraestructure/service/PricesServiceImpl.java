package com.manton.ptGFTMarcelAnton.infraestructure.service;

import com.manton.ptGFTMarcelAnton.application.object.ProductReq;
import com.manton.ptGFTMarcelAnton.application.object.ProductRes;
import com.manton.ptGFTMarcelAnton.domain.service.IPricesService;
import com.manton.ptGFTMarcelAnton.infraestructure.dao.IPricesDao;
import com.manton.ptGFTMarcelAnton.infraestructure.entity.PRICES;
import com.manton.ptGFTMarcelAnton.infraestructure.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


@Service
@RequiredArgsConstructor
public class PricesServiceImpl implements IPricesService {

    private final IPricesDao pricesDao;
    private final ProductMapper productMapper;

    @Override
    public List<ProductRes> getProductService(ProductReq productReq) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        List<PRICES> pricesBy = pricesDao.getPRICESBy(LocalDateTime.parse(productReq.date(), formatter), productReq.productId(), Integer.valueOf(productReq.brandId()));


        return pricesBy.stream().map(productMapper::mapPricesEntitytoProductRes).toList();

    }
}
