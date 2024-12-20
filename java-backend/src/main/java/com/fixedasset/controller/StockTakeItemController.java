package com.fixedasset.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fixedasset.common.lang.Result;
import com.fixedasset.dto.StockTakeFormListDTO;
import com.fixedasset.dto.StockTakeItemListDTO;
import com.fixedasset.entity.StockTakeItem;
import com.fixedasset.service.StockTakeItemService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;

@Tag(name = "Stock Take Item")
@RestController
@RequestMapping("/stock/stock_take/item")
public class StockTakeItemController extends BaseController {
    @Resource private StockTakeItemService stockTakeItemService;

    @Operation(summary = "Page and list")
    @PostMapping("/list")
    public Result list(@RequestBody StockTakeItem stockTakeItem) {
        Page page = new Page(stockTakeItem.getPage(), stockTakeItem.getLimit());
        LambdaQueryWrapper<StockTakeItem> queryWrapper = Wrappers.lambdaQuery();

        queryWrapper.eq(StockTakeItem::getStockTakeId, stockTakeItem.getStockTakeId());

        queryWrapper.orderBy(true, true, StockTakeItem::getId);

        Page<StockTakeItemListDTO> iPage = stockTakeItemService.newPage(page, stockTakeItem);
        return Result.succ(iPage);
    }

    @Operation(summary = "Save stock take item")
    @PostMapping("/save")
    public Result save(@RequestBody StockTakeItem stockTakeItem) {
        stockTakeItemService.saveStock(stockTakeItem);
        return Result.succ(stockTakeItem);
    }

}

