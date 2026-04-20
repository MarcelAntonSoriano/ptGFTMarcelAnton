package com.manton.ptGFTMarcelAnton.application.usecase;

import com.manton.ptGFTMarcelAnton.application.object.ProductReq;
import com.manton.ptGFTMarcelAnton.application.object.ProductRes;
import com.manton.ptGFTMarcelAnton.domain.service.IPricesService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static resources.ObjectMother.*;

class GetPriceTest {

    @InjectMocks
    private GetPrice getPrice;

    @Mock
    private IPricesService pricesService;

    private static final ProductReq REQUEST = new ProductReq("2020-06-14 15:00:00", PRODUCT_ID, BRAND_ID);

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetProductReturnsMappedResponseDto() {
        ProductRes mockProduct = ProductRes.builder().build();

        when(pricesService.getProductService(any())).thenReturn(Collections.singletonList(mockProduct));


        ProductRes response = getPrice.getProduct(REQUEST);


        assertEquals(PRICE_FIRST_ENTITY, response.getPrice());
        assertEquals(PRICE_GROUP_FIRST_ENTITY, response.getPriceGroup());

        verify(pricesService).getProductService(any());
    }
    @Test
    void testGetProduct_EmptyList_ThrowsNoSuchElementException() {
        when(pricesService.getProductService(REQUEST)).thenReturn(Collections.emptyList());

        assertThrows(NoSuchElementException.class, () -> getPrice.getProduct(REQUEST));

        verify(pricesService).getProductService(REQUEST);
    }
}
