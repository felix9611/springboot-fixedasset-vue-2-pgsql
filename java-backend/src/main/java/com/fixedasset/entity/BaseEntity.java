package com.fixedasset.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.time.OffsetDateTime;

/**
 * 功能描述：
 *
 * @Author WaiterXiaoYY
 * @Date 2022/1/13 20:56
 * @Version 1.0
 */
@Data
public class BaseEntity implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private OffsetDateTime created;
    private OffsetDateTime updated;

    private int statu;
}

