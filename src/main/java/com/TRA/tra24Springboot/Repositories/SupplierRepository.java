package com.TRA.tra24Springboot.Repositories;

import com.TRA.tra24Springboot.Models.ContactDetails;
import com.TRA.tra24Springboot.Models.Product;
import com.TRA.tra24Springboot.Models.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SupplierRepository extends JpaRepository <Supplier,Integer>{
    @Query("SELECT s from Supplier s WHERE s.id =:supplierId")
    Supplier getSupplierById(@Param("supplierId") Integer supplierId );

    //Query to get supplier by company name
    @Query("SELECT s FROM Supplier s WHERE s.companyName =:companyName")
    List<Supplier> getSupplierByCompanyName(@Param("companyName") String companyName);
    //Query to get suppliers by country
    @Query("SELECT s FROM Supplier s WHERE s.country =:country")
    List<Supplier> getSupplierByCountry(@Param("country") String country);

    @Query("SELECT s FROM Supplier s WHERE s.minimumOrderQuantity =:minimumOrderQuantity")
    List<Supplier> getSupplierByMinimumOrderQuantity(@Param("minimumOrderQuantity") Integer minimumOrderQuantity);

    @Query("SELECT s FROM Supplier s WHERE s.isActive =:isActive")
    List<Supplier> findBySupplierByIsActive( @Param("isActive") Boolean isActive );


    @Query("SELECT s FROM Supplier s WHERE s.shippingMethods =:shippingMethods")
    List<Supplier> findBySupplierByShippingMethods( @Param("shippingMethods") String shippingMethods );

}
