package com.TRA.tra24Springboot.Services;

import com.TRA.tra24Springboot.Models.Inventory;
import com.TRA.tra24Springboot.Models.Invoice;
import com.TRA.tra24Springboot.Models.Product;
import com.TRA.tra24Springboot.Repositories.InvoiceRepository;
import com.TRA.tra24Springboot.Utils.DateHelperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class InvoiceService {
    @Autowired
    ProductServices productServices;
    @Autowired
    InvoiceRepository invoiceRepository;

    public Invoice createInvoice(Invoice invoice){
        Product product=new Product();

        Product products=productServices.addProduct(product);
        invoice.setProductList(Arrays.asList(products));
        invoice.setPaidAmount(788.5);
        invoice.setTotalAmount(998.0);
        invoice.setIsActive(Boolean.TRUE);
        invoice.setCreatedDate(new Date());

        Date dueDate = DateHelperUtils.addDays(invoice.getCreatedDate(), 7);
        invoice.setDueDate(dueDate);
        return invoiceRepository.save(invoice);
    }
    public List<Invoice> getInvoiceByDueDate(Date dueDate) {
        return invoiceRepository.getInvoiceByDueDate(dueDate);
    }
    // method to get invoices due in next few days
    public List<Invoice> getInvoiceDueInNextDays(Integer days){
        Date today = new Date();
        Date dueDate = DateHelperUtils.addDays(today, days);
        return invoiceRepository.getInvoicesByDueDateBetween(today, dueDate);
    }
    //method to get overdue invoices
    public List<Invoice> getOverDueInvoices(){
        Date today = new Date();
        return invoiceRepository.getOverdueInvoices(today);
    }

    public  Invoice  getInvoiceById(Integer id){
        return invoiceRepository.getInvoiceById(id);
    }
}
