package com.wucc.island.service.ministry;

import com.wucc.island.entity.ministry.AdtfMinistryBill;
import com.wucc.island.repository.ministry.AdtfMinistryBillMapper;
import com.wucc.island.api.ministry.AdtfMinistryBillServiceI;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 转移支付部级单据 服务实现类
 * </p>
 *
 * @author wucc
 * @since 2021-01-20
 */
@Service
public class AdtfMinistryBillService extends ServiceImpl<AdtfMinistryBillMapper, AdtfMinistryBill> implements AdtfMinistryBillServiceI {



    @Cacheable(value = "adtfMinistryBill", key = "#id")
    @Override
    public AdtfMinistryBill getAdtfMinistryBillById(Long id) {
        return baseMapper.selectById(id);
    }


    @CachePut(value = "adtfMinistryBill", key = "#adtfMinistryBill.id")
    @Override
    public boolean updateDdtfMinistryBill(AdtfMinistryBill adtfMinistryBill) {
        return this.saveOrUpdate(adtfMinistryBill);
    }

    @CacheEvict(value = "adtfMinistryBill", key = "#id")
    @Override
    public int deleteById(Long id) {
        return baseMapper.deleteById(id);
    }
}
