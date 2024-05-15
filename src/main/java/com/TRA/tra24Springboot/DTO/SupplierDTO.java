package com.TRA.tra24Springboot.DTO;

import com.TRA.tra24Springboot.Models.Supplier;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SupplierDTO {
    Integer supplierID;
    String companyName;
    List<OrderDTO> orderDTOs;


    public static SupplierDTO convertToDTO(Supplier supplier){
        SupplierDTO supplierDTO = new SupplierDTO();
        supplierDTO.setSupplierID(supplier.getId());
        supplierDTO.setCompanyName(supplier.getCompanyName());
        supplierDTO.setOrderDTOs(OrderDTO.convertToDTO(supplier.getOrders()));
        return supplierDTO;
    }

    public static List<SupplierDTO> convertToDTOList(List<Supplier> suppliers){
        List<SupplierDTO> supplierDTOS = new ArrayList<>();
        for (Supplier supplier : suppliers){
            supplierDTOS.add(convertToDTO(supplier));
        }
        return supplierDTOS;
    }
}
