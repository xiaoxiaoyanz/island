package com.wucc.island.web.redistest;

import com.wucc.island.api.ministry.AdtfMinistryBillServiceI;
import com.wucc.island.entity.ministry.AdtfMinistryBill;
import com.wucc.island.web.IslandTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 * Redis - 缓存测试
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2018-11-15 16:53
 */
@Slf4j
public class MinistryServiceTest extends IslandTest {
    @Autowired
    private AdtfMinistryBillServiceI adtfMinistryBillService;

    /**
     * 获取两次，查看日志验证缓存
     */
    @Test
    public void getTwice() {
        // 模拟查询id为1的用户
        AdtfMinistryBill adtfMinistryBillById1 = adtfMinistryBillService.getAdtfMinistryBillById(123123L);
        log.debug("【adtfMinistryBillById1】= {}", adtfMinistryBillById1);

        // 再次查询
        AdtfMinistryBill adtfMinistryBillById2 = adtfMinistryBillService.getAdtfMinistryBillById(123123L);
        log.debug("【adtfMinistryBillById2】= {}", adtfMinistryBillById2);
        // 查看日志，只打印一次日志，证明缓存生效
    }

    /**
     * 先存，再查询，查看日志验证缓存
     */
    @Test
    public void getAfterSave() {

        AdtfMinistryBill adtfMinistryBillSet = new AdtfMinistryBill();
        adtfMinistryBillSet.setId(123123L);
        adtfMinistryBillSet.setVersion(123123);
        adtfMinistryBillService.save(adtfMinistryBillSet);

        AdtfMinistryBill adtfMinistryBillById = adtfMinistryBillService.getAdtfMinistryBillById(123123L);
        log.debug("【adtfMinistryBillById】= {}", adtfMinistryBillById);
        // 查看日志，只打印保存用户的日志，查询是未触发查询日志，因此缓存生效
    }

    /**
     * 测试删除，查看redis是否存在缓存数据
     */
    @Test
    public void deleteMinistry() {
        // 查询一次，使redis中存在缓存数据
        adtfMinistryBillService.deleteById(1L);
    }

}
