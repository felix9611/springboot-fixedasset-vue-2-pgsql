package com.fixedasset.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fixedasset.dto.StockTakeFormListDTO;
import com.fixedasset.entity.StockTake;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface StockTakeMapper extends BaseMapper<StockTake> {
    @Select("Select * from stocktake where active = 1")
    List<StockTake> getALL();

    @Select("Select * from stocktake where active = 2")
    List<StockTake> getALLFinished();

    String querySqlForm = "SELECT st.*, st.created_time as createdAt," +
            "lo.place_code AS placeCode, lo.place_name AS placeName " +
            "FROM stocktake AS st " +
            "LEFT JOIN location AS lo ON st.action_place = lo.id";
    String wrapperSql = "SELECT * from ( " + querySqlForm + " ) AS q ${ew.customSqlSegment}";

    @Select(wrapperSql)
    Page<StockTakeFormListDTO> newPage(Page page, @Param("ew") Wrapper queryWrapper);
}
