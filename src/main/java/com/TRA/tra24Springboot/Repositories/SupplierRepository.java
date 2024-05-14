package com.TRA.tra24Springboot.Repositories;

import com.TRA.tra24Springboot.Models.Product;
import com.TRA.tra24Springboot.Models.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SupplierRepository extends JpaRepository <Supplier,Integer>{
    @Query("SELECT s from Supplier s WHERE s.id =:supplierId")
    Supplier getSupplierById(@Param("supplierId") Integer supplierId );
}
