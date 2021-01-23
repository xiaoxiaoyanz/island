package com.wucc.island.controller.ministry;


import com.battcn.boot.swagger.model.DataType;
import com.battcn.boot.swagger.model.ParamType;
import com.wucc.island.api.ministry.AdtfMinistryBillServiceI;
import com.wucc.island.common.result.BaseResult;
import com.wucc.island.common.result.BaseResultHandler;
import com.wucc.island.entity.ministry.AdtfMinistryBill;
import com.wucc.island.entity.other.ApiResponse;
import com.wucc.island.entity.other.User;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 转移支付部级单据 前端控制器
 * </p>
 *
 * @author wucc
 * @since 2021-01-20
 */
@RestController
@RequestMapping(value = "/island/ministry")
public class AdtfMinistryBillController {


    private AdtfMinistryBillServiceI adtfMinistryBillService;



    @GetMapping("/getById")
    @ApiOperation(value = "主键查询（DONE）", notes = "返回部级单据实体")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "单据id", dataType = DataType.LONG)})
    public BaseResult<AdtfMinistryBill> getById(@RequestParam(value = "id") Long id) {

        return BaseResultHandler.success(adtfMinistryBillService.getAdtfMinistryBillById(id));
    }


    @GetMapping("/save")
    @ApiOperation(value = "保存实体", notes = "返回部级单据实体")
    public Object save(@RequestBody  AdtfMinistryBill adtfMinistryBill) {

        adtfMinistryBillService.updateDdtfMinistryBill(adtfMinistryBill);
        return BaseResultHandler.success(adtfMinistryBillService.getAdtfMinistryBillById(adtfMinistryBill.getId()));
    }

    @GetMapping("/delete")
    @ApiOperation(value = "删除实体", notes = "返回部级单据实体")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "单据id", dataType = DataType.LONG)})
    public Object delete(@RequestParam(value = "id") Long id) {

        return BaseResultHandler.success(adtfMinistryBillService.deleteById(id));
    }



}
