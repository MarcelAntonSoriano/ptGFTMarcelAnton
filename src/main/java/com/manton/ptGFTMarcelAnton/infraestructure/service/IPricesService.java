package com.manton.ptGFTMarcelAnton.infraestructure.service;

import com.manton.ptGFTMarcelAnton.application.object.ProductReq;
import com.manton.ptGFTMarcelAnton.application.object.ProductRes;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IPricesService {

    List<ProductRes> getProductService(ProductReq productReq);
}
