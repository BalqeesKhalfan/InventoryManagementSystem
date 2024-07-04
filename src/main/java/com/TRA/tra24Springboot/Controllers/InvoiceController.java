package com.TRA.tra24Springboot.Controllers;

import com.TRA.tra24Springboot.Models.Invoice;
import com.TRA.tra24Springboot.Models.Product;
import com.TRA.tra24Springboot.Services.InvoiceService;
import com.TRA.tra24Springboot.Services.SlackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/invoice")
public class InvoiceController {
    @Autowired
    InvoiceService invoiceService;
    @Autowired
    SlackService slackService;
    @PostMapping("create")
    public Invoice addInvoice(@RequestBody Invoice invoice) {
        slackService.sendMessage("Balqees","new Invoice has been added");
        return invoiceService.createInvoice(invoice);
    }
    @Scheduled(cron = "0 0 9 * * ?")
    @PostMapping("dueDate")
    public void senDueDateReminder() {
        Integer remainingDays = 3;
        List<Invoice> invoices = invoiceService.getInvoiceDueInNextDays(remainingDays);
        for (Invoice invoice : invoices) {
            StringBuilder message = new StringBuilder();
            message.append("Reminder: Invoice #")
                    .append(invoice.getId())
                    .append(" is due on ")
                    .append(invoice.getDueDate().toString());
            slackService.sendMessage("practice", message.toString());
        }
    }
}


