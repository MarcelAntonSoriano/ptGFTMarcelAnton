package com.manton.ptGFTMarcelAnton.infraestructure.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name="PRICES")
public class PRICES implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Setter
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "BRAND_ID")
    @Setter
    @Getter
    private String brandId;

    @Column(name = "START_DATE")
    @Setter
    @Getter
    private LocalDateTime startDate;

    @Column(name = "END_DATE")
    @Setter
    @Getter
    private LocalDateTime endDate;

    @Column(name = "PRICE_LIST")
    @Getter
    @Setter
    private Integer priceList;

    @Column(name = "PRODUCT_ID")
    @Getter
    @Setter
    private String productId;

    @Column(name = "PRIORITY")
    @Getter
    @Setter
    private Integer priority;

    @Column(name = "PRICE")
    @Getter
    @Setter
    private Double price;

    @Column(name = "CURR")
    @Getter
    @Setter
    private String currency;

}
