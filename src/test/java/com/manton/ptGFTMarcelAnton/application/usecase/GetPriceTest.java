package com.manton.ptGFTMarcelAnton.application.usecase;

import com.manton.ptGFTMarcelAnton.application.object.ProductRes;
import com.manton.ptGFTMarcelAnton.domain.dto.RequestDto;
import com.manton.ptGFTMarcelAnton.domain.dto.ResponseDto;
import com.manton.ptGFTMarcelAnton.infraestructure.mapper.ProductMapper;
import com.manton.ptGFTMarcelAnton.infraestructure.service.IPricesService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static resources.ObjectMother.*;

class GetPriceTest {

    @InjectMocks
    private GetPrice getPrice;

    @Mock
    private ProductMapper productMapper;

    @Mock
    private IPricesService pricesService;

    private static final RequestDto REQUEST_DTO = new RequestDto("2020-06-14 15:00:00", PRODUCT_ID, BRAND_ID);

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetProductReturnsMappedResponseDto() {
        ProductRes mockProduct = ProductRes.builder().build();

        when(pricesService.getProductService(any())).thenReturn(Collections.singletonList(mockProduct));

        ResponseDto responseDto = new ResponseDto(PRODUCT_ID, BRAND_ID, PRICE_GROUP_FIRST_ENTITY, START_DATE_FIRST_ENTITY, END_DATE_FIRST_ENTITY, PRICE_FIRST_ENTITY);

        when(productMapper.mapProductRestoResponseDto(mockProduct)).thenReturn(responseDto);

        ResponseDto response = getPrice.getProduct(REQUEST_DTO);

        verify(pricesService).getProductService(any());
        verify(productMapper).mapProductRestoResponseDto(mockProduct);

        assertEquals(PRICE_FIRST_ENTITY, response.price());
        assertEquals(PRICE_GROUP_FIRST_ENTITY, response.priceGroup());
    }
}
