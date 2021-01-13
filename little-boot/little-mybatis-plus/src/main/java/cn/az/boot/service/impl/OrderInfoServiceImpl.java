package cn.az.boot.service.impl;

import cn.az.boot.entity.OrderInfo;
import cn.az.boot.mapper.OrderInfoMapper;
import cn.az.boot.service.IOrderInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author az
 * @since 11/14/20
 */
@Service
public class OrderInfoServiceImpl extends ServiceImpl<OrderInfoMapper, OrderInfo> implements IOrderInfoService {

    /**
     * 添加指定的
     *
     * @param orderInfoList 集合
     */
    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public int batchAdd(List<OrderInfo> orderInfoList) {
        // 1. check whether exist
        List<OrderInfo> searchResult = baseMapper.selectBatchIds(orderInfoList.stream().map(OrderInfo::getId).collect(Collectors.toList()));
        if (!searchResult.isEmpty()) {
            batchDelete(searchResult);
        }
        saveBatch(orderInfoList);
        return 0;
    }

    /**
     * 删除指定的
     *
     * @param orderInfoList 集合
     */
    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public int batchDelete(List<OrderInfo> orderInfoList) {
        throw new RuntimeException("发生了异常");

    }
}
