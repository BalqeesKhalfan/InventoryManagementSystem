package com.TRA.tra24Springboot.Repositories;


import com.TRA.tra24Springboot.Models.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface InvoiceRepository  extends JpaRepository<Invoice,Integer> {
    @Query("SELECT invo from Invoice invo WHERE invo.id =:invoId")
   Invoice getInvoiceById(@Param("invoId") Integer invoIdId );

}
