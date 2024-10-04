package com.fixedasset.entity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;

import javax.persistence.Transient;
@Data
@TableName("repair_record")
@Component
public class RepairRecord {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("asset_id")
    private int assetId;

    @TableField("repair_reason")
    private String repairReason;

    @TableField("maintenance_period")
    private int maintenanceReriod;

    @TableField("maintenance_name")
    private String maintenanceName;

    @TableField("maintenance_date")
    private OffsetDateTime maintenanceDate;

    @TableField("maintenance_finish_date")
    private OffsetDateTime maintenanceFinishDate;

    @TableField("repair_invoice_date")
    private OffsetDateTime repairInvoiceDate;

    @TableField("repair_invoice_no")
    private String repairInvoiceNo;

    @TableField("repair_amount")
    private Double repairAmount;

    @TableField("remark")
    private String remark;

    @TableField("created")
    private OffsetDateTime created;

    @TableField("updated")
    private OffsetDateTime updated;

    @TableField("status")
    private int status;

    @TableField(exist = false)
    private int page;

    @TableField(exist = false)
    private int limit;

    @TableField(exist = false)
    private String assetCode;

    @TableField(exist = false)
    private String  assetName;

    @TableField(exist = false)
    private OffsetDateTime repairInvoiceDateTo;

    @TableField(exist = false)
    private OffsetDateTime repairInvoiceDateFrom;
}
