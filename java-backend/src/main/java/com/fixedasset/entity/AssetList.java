package com.fixedasset.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;

@Component
@Data
@TableName("asset_list")
public class AssetList extends BaseEntity{
    @TableField("asset_code")
    private String assetCode;

    @TableField("asset_name")
    private String assetName;

    @TableField("type_id")
    private int typeId;

    @TableField("unit")
    private String unit;

    @TableField("buy_date")
    private OffsetDateTime buyDate;

    @TableField("description")
    private String description;

    @TableField("sponsor")
    private int sponsor;

    @TableField("sponsor_name")
    private String sponsorName;

    @TableField("cost")
    private double cost;

    @TableField("serial_number")
    private String serialNum;

    @TableField("invoice_no")
    private String invoiceNo;

    @TableField("invoice_date")
    private OffsetDateTime invoiceDate;

    @TableField("invoice_remark")
    private String invoiceRemark;

    @TableField("place_id")
    private int placeId;

    @TableField("dept_id")
    private int deptId;

    @TableField("staff_id")
    private String staffId;

    @TableField("vendor_id")
    private int vendorId;

    @TableField("remark")
    private String remark;

    @TableField("tax_country_code")
    private String taxCountryCode;

    @TableField("tax_code")
    private String taxCode;

    @TableField("tax_rate")
    private double taxRate;

    @TableField("include_tax")
    private int includeTax;

    @TableField("after_before_tax")
    private String afterBeforeTax;

    @TableField("account_code")
    private String accountCode;

    @TableField("account_name")
    private String accountName;

    @TableField("brand_code")
    private String brandCode;

    @TableField("brand_name")
    private String brandName;

    @TableField("cheque_no")
    private String chequeNo;

    @TableField("maintenance_period_start")
    private OffsetDateTime maintenancePeriodStart;

    @TableField("maintenance_period_end")
    private OffsetDateTime maintenancePeriodEnd;

    @TableField("voucher_no")
    private String voucherNo;

    @TableField("Voucher_used_date")
    private OffsetDateTime voucherUsedDate;

    @TableField(exist = false)
    private int page;

    @TableField(exist = false)
    private int limit;

    @TableField(exist = false)
    private OffsetDateTime buyDateTo;

    @TableField(exist = false)
    private OffsetDateTime buyDateFrom;

    @TableField(exist = false)
    private List<AssetListFile> assetListFiles;

    @TableField(exist = false)
    private List<AssetListFile> newAssetListFiles;

}
