package com.manton.ptGFTMarcelAnton.domain.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.manton.ptGFTMarcelAnton.application.usecase.GetPrice;
import com.manton.ptGFTMarcelAnton.domain.dto.RequestDto;
import com.manton.ptGFTMarcelAnton.domain.dto.ResponseDto;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.stream.Stream;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static resources.ObjectMother.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private GetPrice getPrice;

    private static final ObjectMapper objectMapper = new ObjectMapper();

    private static Stream<Arguments> priceGroups() {
        return Stream.of(
                Arguments.of("2020-06-14 10:00:00", PRICE_FIRST_ENTITY, PRICE_GROUP_FIRST_ENTITY),
                Arguments.of("2020-06-14 16:00:00", PRICE_SECOND_ENTITY, PRICE_GROUP_SECOND_ENTITY),
                Arguments.of("2020-06-14 21:00:00", PRICE_FIRST_ENTITY, PRICE_GROUP_FIRST_ENTITY),
                Arguments.of("2020-06-15 10:00:00", PRICE_THIRD_ENTITY, PRICE_GROUP_THIRD_ENTITY),
                Arguments.of("2020-06-16 21:00:00", PRICE_FOURTH_ENTITY, PRICE_GROUP_FOURTH_ENTITY)
        );
    }

    @ParameterizedTest(name = "{index} Test for date {0}")
    @MethodSource("priceGroups")
    public void getPriceGroups(String date, double expectedPrice, int expectedPriceGroup) throws Exception {

        RequestDto requestDto = new RequestDto(date, PRODUCT_ID, BRAND_ID);
        ResponseDto responseDto = ResponseDto.builder()
                .price(expectedPrice)
                .brandId(BRAND_ID)
                .priceGroup(expectedPriceGroup)
                .productId(PRODUCT_ID)
                .build();

        when(getPrice.getProduct(requestDto)).thenReturn(responseDto);

        mockMvc.perform(get("/getProduct")
                        .content(objectMapper.writeValueAsString(requestDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value(expectedPrice))
                .andExpect(jsonPath("$.priceGroup").value(expectedPriceGroup))
                .andExpect(jsonPath("$.brandId").value(BRAND_ID))
                .andExpect(jsonPath("$.productId").value(PRODUCT_ID));
    }
}
