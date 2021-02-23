package com.wucc.island.api.ministry;

import com.wucc.island.entity.ministry.AdtfMinistryBill;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 转移支付部级单据 服务类
 * </p>
 *
 * @author wucc
 * @since 2021-01-20
 */
public interface AdtfMinistryBillServiceI extends IService<AdtfMinistryBill> {

    AdtfMinistryBill getAdtfMinistryBillById(Long id);

    AdtfMinistryBill updateDdtfMinistryBill(AdtfMinistryBill adtfMinistryBill);

    int deleteById(Long id);
}
