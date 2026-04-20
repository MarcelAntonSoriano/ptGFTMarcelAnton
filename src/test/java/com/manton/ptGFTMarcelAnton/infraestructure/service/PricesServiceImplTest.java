package com.manton.ptGFTMarcelAnton.infraestructure.service;

import com.manton.ptGFTMarcelAnton.application.object.ProductReq;
import com.manton.ptGFTMarcelAnton.application.object.ProductRes;
import com.manton.ptGFTMarcelAnton.infraestructure.dao.IPricesDao;
import com.manton.ptGFTMarcelAnton.infraestructure.entity.PRICES;
import com.manton.ptGFTMarcelAnton.infraestructure.mapper.ProductMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static resources.ObjectMother.*;

@ExtendWith(MockitoExtension.class)
public class PricesServiceImplTest {

    @Mock
    private IPricesDao pricesDao;

    @Mock
    private ProductMapper productMapper;

    @InjectMocks
    private PricesServiceImpl pricesService;

    private ProductReq productReq;
    private ProductRes productRes;
    private PRICES prices;

    @BeforeEach
    public void setUp() {

        productReq = new ProductReq("1", "123", START_DATE_FIRST_ENTITY);
        productRes = ProductRes.builder()
                .price(100.00)
                .priceGroup(1)
                .startDate(START_DATE_FIRST_ENTITY)
                .endDate(END_DATE_FIRST_ENTITY)
                .brandId("1")
                .productId("123")
                .build();
        prices = new PRICES();
    }

    @Test
    public void testGetProduct_ShouldReturnProductList() {
        var mockPricesEntityList = List.of(prices);
        when(pricesDao.getPRICESBy(any(), anyString(), anyInt())).thenReturn(mockPricesEntityList);

        when(productMapper.mapPricesEntitytoProductRes(any())).thenReturn(productRes);

        List<ProductRes> result = pricesService.getProductService(productReq);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(productRes, result.getFirst());

        verify(pricesDao).getPRICESBy(any(), anyString(), anyInt());
        verify(productMapper).mapPricesEntitytoProductRes(any());
    }

    @Test
    public void testGetProduct_ShouldReturnEmptyList_WhenNoDataFound() {

        when(pricesDao.getPRICESBy(any(), anyString(), anyInt())).thenReturn(List.of());

        List<ProductRes> result = pricesService.getProductService(productReq);

        assertNotNull(result);
        assertTrue(result.isEmpty());

        verify(pricesDao).getPRICESBy(any(), anyString(), anyInt());
    }
}
