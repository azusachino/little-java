package cn.az.boot.service;

import cn.az.boot.entity.OrderInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author az
 * @since 11/14/20
 */
public interface IOrderInfoService extends IService<OrderInfo> {

    /**
     * 添加指定的
     *
     * @param orderInfoList 集合
     */
    int batchAdd(List<OrderInfo> orderInfoList);


    /**
     * 删除指定的
     *
     * @param orderInfoList 集合
     */
    int batchDelete(List<OrderInfo> orderInfoList);

}
