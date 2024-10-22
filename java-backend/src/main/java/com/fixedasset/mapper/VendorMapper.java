package com.fixedasset.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fixedasset.entity.Vendor;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface VendorMapper extends BaseMapper<Vendor> {

    List<Vendor> getALL();
}
