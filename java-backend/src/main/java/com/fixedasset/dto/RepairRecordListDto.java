package com.fixedasset.dto;

import java.time.LocalDate;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.persistence.Transient;

import com.fixedasset.entity.RepairRecord;

// @EqualsAndHashCode(callSuper = true)
@Data
public class RepairRecordListDto extends RepairRecord{
    @Transient private String assetCode;
    @Transient private String assetName;

    @Transient private Long id;

    @Transient private int assetId;

    @Transient private String repairReason;

    @Transient private int maintenanceReriod;

    @Transient private String maintenanceName;

    @Transient private LocalDate maintenanceDate;

    @Transient private LocalDate maintenanceFinishDate;

    @Transient private LocalDate repairInvoiceDate;

    @Transient private String repairInvoiceNo;

    @Transient private Double repairAmount;

    @Transient private String remark;

    @Transient private LocalDate created;

    @Transient private LocalDate updated;
}
