package com.fixedasset.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.time.LocalDate;

@Component
@Data
@TableName("stocktake")
public class StockTake implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("action_name")
    private String actionName;

    @TableField("action_place")
    private int actionPlace;

    @TableField("created_time")
    private LocalDate createdTime;

    @TableField("finish_time")
    private LocalDate finishTime;

    @TableField("active")
    private int active;

    @TableField(exist = false)
    private int page;

    @TableField(exist = false)
    private int limit;
}
