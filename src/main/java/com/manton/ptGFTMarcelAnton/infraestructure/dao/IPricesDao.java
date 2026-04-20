package com.manton.ptGFTMarcelAnton.infraestructure.dao;

import com.manton.ptGFTMarcelAnton.infraestructure.entity.PRICES;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public interface IPricesDao extends CrudRepository<PRICES,Long> {

    @Query("select p from PRICES p where p.startDate <= ?1 and p.endDate >= ?1 and p.productId = ?2 and p.brandId = ?3")
    List<PRICES> getPRICESBy(LocalDateTime startDate, String productId, Integer brandId);

}
