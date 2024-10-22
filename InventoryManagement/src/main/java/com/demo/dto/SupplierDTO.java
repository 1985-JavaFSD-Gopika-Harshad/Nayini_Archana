package com.demo.dto;

import com.demo.model.Supplier;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SupplierDTO {

    private Long id;
    private String name;
    private String contactInfo;

    public SupplierDTO(Supplier supplier) {
        this.id = supplier.getId();
        this.name = supplier.getName();
        this.contactInfo = supplier.getContactInfo();
    }
}
