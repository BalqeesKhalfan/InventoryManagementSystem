package com.TRA.tra24Springboot.Services;

import com.TRA.tra24Springboot.BO.InventoryReportObject;
import com.TRA.tra24Springboot.DTO.InventoryDTO;
import com.TRA.tra24Springboot.DTO.ProductDTO;
import com.TRA.tra24Springboot.Utils.DateHelperUtils;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

@Service
public class ReportService {
    @Autowired
    InventoryServices inventoryServices;
    @Autowired
    private JavaMailSender mailSender;

    public void createInventoryReport() throws Exception {
        List<InventoryDTO> inventoryDTOList = inventoryServices.getAll();

        List<InventoryReportObject> inventoryReportObjectList = convertInventoryListToInventoryReportBO(inventoryDTOList);
        UUID uuid = UUID.randomUUID();
        String timeStamp = DateHelperUtils.formatDate(new Date(), "yyyy-MM-dd_HH-mm-ss");

        String pathToSave = "C:\\Users\\TRA0030\\Desktop\\";

        File templateFile = ResourceUtils.getFile("C:\\Users\\TRA0030\\Documents\\BalqeesSpringBootInvoivingProject\\InvoicingSystem\\src\\main\\resources\\Inventory_Report.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(templateFile.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(inventoryReportObjectList);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, new HashMap<>(), dataSource);
        String fileName = pathToSave + uuid + "_" + timeStamp + ".pdf";
        JasperExportManager.exportReportToPdfFile(jasperPrint, fileName);
        System.out.println("Report is printed: " + fileName);

        // Send the report via email and Slack
        sendReportByEmail(fileName);
    }

    public List<InventoryReportObject> convertInventoryListToInventoryReportBO(List<InventoryDTO> dtos) {
        List<InventoryReportObject> inventoryReportObjectList = new ArrayList<>();
        for (InventoryDTO dto : dtos) {

            List<ProductDTO> productDTOS = dto.getProducts();
            for (ProductDTO productDTO : productDTOS) {
                if (productDTO.getProductDetailsDTO() != null
                        || productDTO.getProductDetailsDTO().getProductName() != null) {
                    InventoryReportObject obj = new InventoryReportObject();
                    obj.setInventoryId(dto.getInventoryId());
                    obj.setLocation(dto.getLocation());
                    obj.setProductName(productDTO.getProductDetailsDTO().getProductName());
                    inventoryReportObjectList.add(obj);
                }

            }
        }
        return inventoryReportObjectList;
    }

    private void sendReportByEmail(String filePath) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo("b.k.malshuraqia@gmail.com"); // Change to your email
        helper.setSubject("Hourly Inventory Report");
        helper.setText("Attached is the latest inventory report.");

        File reportFile = new File(filePath);
        helper.addAttachment(reportFile.getName(), reportFile);

        mailSender.send(message);
        System.out.println("Email sent successfully with attachment: " + reportFile.getName());
    }
}