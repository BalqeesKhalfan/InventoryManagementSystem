package com.TRA.tra24Springboot.Repositories;


import com.TRA.tra24Springboot.Models.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface InvoiceRepository  extends JpaRepository<Invoice,Integer> {
    @Query("SELECT invo from Invoice invo WHERE invo.id =:invoId")
   Invoice getInvoiceById(@Param("invoId") Integer invoIdId );
    @Query("SELECT v FROM Invoice v WHERE v.createdDate =:createdDate")
    public List<Invoice> getInvoiceByCreatedDate(@Param("createdDate") Date createdDate);

    @Query("SELECT v FROM Invoice v WHERE v.dueDate =:dueDate")
    public List<Invoice> getInvoiceByDueDate(@Param("dueDate") Date dueDate);

    @Query("SELECT v FROM Invoice v WHERE v.dueDate BETWEEN :startDate AND :endDate")
    public List<Invoice> getInvoicesByDueDateBetween(@Param("startDate") Date startDate,
                                                     @Param("endDate") Date endDate);

}
