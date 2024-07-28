package com.TRA.tra24Springboot.Services;

import com.TRA.tra24Springboot.DTO.SupplierDTO;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
@Service
public class SupplierReportService {




        @Autowired

        private  SupplierServices supplierServices;

        public void createSupplierReport() throws Exception {
            List<SupplierDTO> supplierDTOS = supplierServices.getSuppliers();
            UUID uuid = UUID.randomUUID();

            String pathToSave = "C:\\Users\\TRA0030\\Desktop\\";

            File templateFile = ResourceUtils.getFile("C:\\Users\\TRA0030\\Documents\\BalqeesSpringBootInvoivingProject\\InvoicingSystem\\src\\main\\resources\\supplier_report.jrxml");
            JasperReport jasperReport = JasperCompileManager.compileReport(templateFile.getAbsolutePath());
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(supplierDTOS);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,new HashMap<>(),dataSource);
            String fileName = pathToSave + uuid+".pdf";
            JasperExportManager.exportReportToPdfFile(jasperPrint,fileName );
            System.out.println("Report is printed: "+ fileName);
        }
}
