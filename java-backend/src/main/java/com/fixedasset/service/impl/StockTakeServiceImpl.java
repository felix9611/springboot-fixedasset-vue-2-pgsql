package com.fixedasset.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fixedasset.dto.StockTakeFormListDTO;
import com.fixedasset.dto.StockTakeItemListRecord;
import com.fixedasset.entity.ActionRecord;
import com.fixedasset.entity.AssetList;
import com.fixedasset.entity.Location;
import com.fixedasset.entity.StockTake;
import com.fixedasset.entity.StockTakeItem;
import com.fixedasset.mapper.ActionRecordMapper;
import com.fixedasset.mapper.StockTakeMapper;
import com.fixedasset.service.ActionRecordService;
import com.fixedasset.service.AssetListService;
import com.fixedasset.service.LocationService;
import com.fixedasset.service.StockTakeItemService;
import com.fixedasset.service.StockTakeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;

@Service
public class StockTakeServiceImpl extends ServiceImpl<StockTakeMapper, StockTake> implements StockTakeService {

    @Resource private StockTake stockTake;

    @Resource private StockTakeMapper stockTakeMapper;

    @Resource private ActionRecordService actionRecordService;

    @Resource private LocationService locationService;

    @Resource private AssetListService assetListService;

    @Resource private StockTakeItemService stockTakeItemService;

    public void uploadStockTakeReocrd(StockTake stockTake) {
        LambdaQueryWrapper<StockTake> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StockTake::getActionName, stockTake.getActionName());

        StockTake checkOne = stockTakeMapper.selectOne(queryWrapper);
        if (checkOne == null) {
            stockTake.setActive(2);
            stockTake.setCreatedTime(OffsetDateTime.now());
            stockTake.setCreatedTime(OffsetDateTime.now());
            stockTake.setFinishTime(OffsetDateTime.now());
           // stockTakeMapper.insert(stockTake);

            
            List<StockTakeItemListRecord> stockTakeItemListRecord = stockTake.getStockTakeItemRecords();
            System.out.println( stockTakeItemListRecord.toString() );
            if (stockTakeItemListRecord.size() > 0) {
                for (StockTakeItemListRecord record : stockTake.getStockTakeItemRecords()) {
                    StockTakeItem stockTakeItem = new StockTakeItem();
                    
                    Location location = new Location();
                    location.setPlaceCode(record.getPlaceCode());
                    location.setPlaceCode(record.getPlaceCode());

                    Location locationData = locationService.getData(location);

                    if (locationData == null) {
                        stockTakeItem.setStatus("Incorrect Location Datas or not exist");
                    } else {
                        stockTakeItem.setPlaceId(Math.toIntExact(locationData.getId()));

                        AssetList assetList = new AssetList();
                        assetList.setAssetCode(record.getAssetCode());
                        AssetList assetList2 = assetListService.findOneByAssetCode(assetList);

                        if (assetList2 == null) {
                            stockTakeItem.setStatus("This asset not exist in active data");
                        } else {
                            stockTakeItem.setAssetId(Math.toIntExact(assetList2.getId()));
                            int placeId = Math.toIntExact(locationData.getId());
                            if (assetList2.getPlaceId() == placeId) {
                                stockTakeItem.setStatus("Exist");
                            } else {
                                stockTakeItem.setStatus("Incorrect Location");
                            }
                        }
                    }
                    
                    stockTakeItem.setStockTakeId(Math.toIntExact(stockTake.getId()));
                    stockTakeItem.setAssetCode(record.getAssetCode());
                    stockTakeItem.setRemark(record.getRemark());
                    stockTakeItemService.saveStock(stockTakeItem);
                }

            }

            actionRecordService.createdAction(
                "Save form Upload Excel", 
                "POST", 
                "Stock Take", 
                stockTake.toString(), 
                "Success"
            );

        } else {
            actionRecordService.createdAction(
                "Save form Upload Excel", 
                "POST", 
                "Stock Take", 
                stockTake.toString(), 
                "Failure"
            );
            throw new RuntimeException("Please create new action name!");
        }
    }

    public void create(StockTake stockTake) {
        LambdaQueryWrapper<StockTake> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StockTake::getActionName, stockTake.getActionName());

        StockTake checkOne = stockTakeMapper.selectOne(queryWrapper);
        if (checkOne == null) {
            stockTake.setActive(1);
            stockTake.setCreatedTime(OffsetDateTime.now());
            stockTakeMapper.insert(stockTake);

            actionRecordService.createdAction(
                "Save", 
                "POST", 
                "Stock Take", 
                stockTake.toString(), 
                "Failure"
            );

        } else {
            throw new RuntimeException("Please create new action name!");
        }
    }

    public Page<StockTakeFormListDTO> newPage(Page page, LambdaQueryWrapper<StockTake> queryWrapper) {
        return stockTakeMapper.newPage(page, queryWrapper);
    }

    public List<StockTake> getAllActive() {
        return stockTakeMapper.getALL();
    }

    public List<StockTake> getAllActiveFinish() {
        return stockTakeMapper.getALLFinished();
    }

    public void remove(Long id) {
        stockTake.setId(id);
        stockTake.setActive(0);
        stockTakeMapper.updateById(stockTake);

        actionRecordService.createdAction(
            "Delete Stock  Form", 
            "Delete", 
            "Stock Take", 
            stockTake.toString(), 
            "Success"
        );
    }

    public void finish(Long id) {
        stockTake.setId(id);
        stockTake.setActive(2);
        stockTake.setFinishTime(OffsetDateTime.now());
        stockTakeMapper.updateById(stockTake);

        actionRecordService.createdAction(
            "Finish Stock take", 
            "Delete", 
            "Stock Take", 
            stockTake.toString(), 
            "Success"
        );
    }
}
