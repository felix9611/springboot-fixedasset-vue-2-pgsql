package com.fixedasset.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fixedasset.common.lang.Result;
import com.fixedasset.dto.AssetListViewDTO;
import com.fixedasset.entity.AssetList;
import com.fixedasset.entity.AssetListFile;
import com.fixedasset.entity.WriteOff;
import com.fixedasset.service.AssetListFileService;
import com.fixedasset.service.AssetListService;
// import org.springframework.util.StringUtils;
import com.fixedasset.service.WriteOffService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/asset/assetList")
public class AssetListController extends BaseController {

    @Resource AssetListService assetListService;

    @Resource AssetListFileService assetListFileService;

    @Resource WriteOffService writeOffService;

    @PostMapping("/listAll")
    public Result listAll(@RequestBody AssetList assetList) {
        Page page = new Page(assetList.getPage(), assetList.getLimit());
        LambdaQueryWrapper<AssetList> queryWrapper = Wrappers.lambdaQuery();

        if (!StringUtils.isBlank(assetList.getAssetCode())){
            queryWrapper.like(AssetList::getAssetCode, assetList.getAssetCode());
        }

        if (!StringUtils.isBlank(assetList.getAssetName())){
            queryWrapper.like(AssetList::getAssetName, assetList.getAssetName());
        }

        if (!(assetList.getTypeId() == 0)) {
            queryWrapper.eq(AssetList::getTypeId, assetList.getTypeId());
        }
        if (!(assetList.getPlaceId() == 0)) {
            queryWrapper.eq(AssetList::getPlaceId, assetList.getPlaceId());
        }
        if (!(assetList.getDeptId() == 0)) {
            queryWrapper.eq(AssetList::getDeptId, assetList.getDeptId());
        }

        queryWrapper.orderByDesc(true, AssetList::getAssetCode);
        queryWrapper.eq(AssetList::getStatu, 1);
        Page<AssetListViewDTO> iPage = assetListService.newPage(page, queryWrapper);
        return Result.succ(iPage);
    }

    @PostMapping("/report/listAll")
    public Result reportListAll(@RequestBody AssetList assetList) {
        LambdaQueryWrapper<AssetList> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.orderByDesc(true, AssetList::getAssetCode);
        queryWrapper.eq(AssetList::getStatu, 1);
        List<AssetListViewDTO> iPage = assetListService.newReport(queryWrapper);
        return Result.succ(iPage);
    }

    @PostMapping("/writeOff/listAll")
    public Result listAllWriteOff(@RequestBody AssetList assetList) {
        LambdaQueryWrapper<AssetList> queryWrapper = Wrappers.lambdaQuery();

        if (!StringUtils.isBlank(assetList.getAssetCode())){
            queryWrapper.like(AssetList::getAssetCode, assetList.getAssetCode());
        }

        if (!StringUtils.isBlank(assetList.getAssetName())){
            queryWrapper.like(AssetList::getAssetName, assetList.getAssetName());
        }

        if (!(assetList.getTypeId() == 0)) {
            queryWrapper.eq(AssetList::getTypeId, assetList.getTypeId());
        }
        if (!(assetList.getPlaceId() == 0)) {
            queryWrapper.eq(AssetList::getPlaceId, assetList.getPlaceId());
        }
        if (!(assetList.getDeptId() == 0)) {
            queryWrapper.eq(AssetList::getDeptId, assetList.getDeptId());
        }

        queryWrapper.orderByDesc(true, AssetList::getAssetCode);
        queryWrapper.eq(AssetList::getStatu, 0);
        List<AssetListViewDTO> iPage = assetListService.newPageInWriteOff(queryWrapper);
        return Result.succ(iPage);
    }

    @PostMapping("/getTotalSum")
    public Result getTotalSum(@RequestBody AssetList assetList) {
        return Result.succ(assetListService.sumTotal(assetList));
    }

    @PostMapping("/sumCostWithSponsor")
    public Result sumCostWithSponsor(@RequestBody AssetList assetList) {
        return Result.succ(assetListService.sumCostWithSponsor(assetList));
    }

    @PostMapping("/create")
    public Result create(@RequestBody AssetList assetList) {
        String newAssetCode = assetListService.createNew(assetList);
        return Result.succ(newAssetCode);
    }

    @PostMapping("/update")
     public Result update(@RequestBody AssetList assetList) {
        assetListService.update(assetList);
        return Result.succ(assetList);
    }

    @GetMapping("/{id}")
    public Result getOne(@PathVariable("id") Long id) {
        return Result.succ(assetListService.findOneById(id));
    }

    @GetMapping("/assetCode/{assetCode}")
    public Result getOneByAssetCode(@PathVariable("assetCode") String id) {
        AssetList assetList = new AssetList();
        assetList.setAssetCode(id);
        return Result.succ(assetListService.findOneByAssetCode(assetList));
    }

    @DeleteMapping("/remove/{id}")
    public Result remove(@PathVariable("id") Long id) {
        assetListService.remove(id);
        return Result.succ(id);
    }

    @PostMapping("/findAsset")
    public Result findAsset(@RequestBody AssetList assetList) {
        AssetList assetList1 = assetListService.findOne(assetList);
        return Result.succ(assetList1);
    }

    @PostMapping("/addFile")
    public Result addFile(@RequestBody AssetListFile assetListFile){
        assetListFileService.saveListPicture(assetListFile);
        return Result.succ(assetListFile);
    }

    @PostMapping("/loadFile")
    public  Result loadFile(@RequestBody AssetListFile assetListFile) {
        return Result.succ(assetListFileService.getByAssetId(assetListFile));
    }

    @DeleteMapping("/removeFile/{id}")
    public Result removeFile(@PathVariable("id") Long id) {
        assetListFileService.removeFile(id);
        return Result.succ(id);
    }

    @GetMapping("/getCostWithDept")
    public Result getCostWithDept() { return Result.succ(assetListService.getCostWithDept()); }


    @PostMapping("/getAssetYearCostDeptFind")
    public Result assetYearCostDeptFind(@RequestBody AssetList assetList) { return Result.succ(assetListService.assetYearCostDeptFind(assetList)); }
    @PostMapping("/getItemYearMonthFind")
    public Result getItemYearMonthFind(@RequestBody AssetList assetList) { return Result.succ(assetListService.getItemYearMonthFind(assetList)); }
    @PostMapping("/getAssetYearCostTypeFind")
    public Result getAssetYearCostTypeFind(@RequestBody AssetList assetList) { return Result.succ(assetListService.assetYearCostTypeFind(assetList)); }
    @PostMapping("/groupByTypeFind")
    public Result groupByTypeFind(@RequestBody AssetList assetList) { return Result.succ(assetListService.groupByTypeFind(assetList)); }
    @PostMapping("/getAssetGroupPlaceFind")
    public Result getAssetGroupPlaceFind(@RequestBody AssetList assetList) { return Result.succ(assetListService.getAssetGroupPlaceFind(assetList)); }
    @PostMapping("/getAssetYearQtyTypeFind")
    public Result getAssetYearQtyTypeFind(@RequestBody AssetList assetList) { return Result.succ(assetListService.getAssetYearQtyTypeFind(assetList)); }
    @PostMapping("/getAssetYearQtyDeptFind")
    public Result getAssetYearQtyDeptFind(@RequestBody AssetList assetList) { return Result.succ(assetListService.getAssetYearQtyDeptFind(assetList)); }
    @PostMapping("/getAssetCostYearMonthFind")
    public Result getAssetCostYearMonthFind(@RequestBody AssetList assetList) { return Result.succ(assetListService.getAssetCostYearMonthFind(assetList)); }
    @PostMapping("/getAssetYearQtyPlaceFind")
    public Result getAssetYearQtyPlaceFind(@RequestBody AssetList assetList) { return Result.succ(assetListService.getAssetYearQtyPlaceFind(assetList)); }
    @PostMapping("/getAssetYearCostPlaceFind")
    public Result getAssetYearCostPlaceFind(@RequestBody AssetList assetList) { return Result.succ(assetListService.getAssetYearCostPlaceFind(assetList)); }

    @PostMapping("/writeOff")
    public Result writeItem(@RequestBody WriteOff writeOff) {
        writeOffService.saveWriteOff(writeOff);
        return Result.succ("");
    }
}
