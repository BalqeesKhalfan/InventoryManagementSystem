package com.TRA.tra24Springboot.Services;

import com.TRA.tra24Springboot.Models.Inventory;
import com.TRA.tra24Springboot.Models.Invoice;
import com.TRA.tra24Springboot.Models.Product;
import com.TRA.tra24Springboot.Repositories.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;

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
        invoice.setPaidAmount(78.5);
        invoice.setTotalAmount(45.6);
        invoice.setIsActive(Boolean.TRUE);
        invoice.setCreatedDate(new Date());
        invoice.setDueDate(new Date());
        return invoiceRepository.save(invoice);
    }
    public  Invoice  getInvoiceById(Integer id){
        return invoiceRepository.getInvoiceById(id);
    }
}
