package cn.az.boot.controller;

import cn.az.boot.entity.OrderInfo;
import cn.az.boot.service.IOrderInfoService;
import com.google.common.base.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author az
 * @since 11/14/20
 */
@RestController
@RequestMapping("/api/order")
public class OrderInfoController {

    @Autowired
    private IOrderInfoService orderInfoService;

    @PostMapping(value = "add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> addNewOrder(@RequestBody OrderInfo orderInfo) {
        Preconditions.checkArgument(orderInfo == null, "参数错误!!!");
        orderInfoService.save(orderInfo);
        return ResponseEntity.ok("");
    }

    @PostMapping(value = "addBatch")
    public ResponseEntity<?> batchAddNewOrder(@RequestBody List<OrderInfo> orderInfoList) {
        Preconditions.checkArgument(orderInfoList.isEmpty(), "参数错误!!!");
        int result = orderInfoService.batchAdd(orderInfoList);
        return ResponseEntity.ok(result);
    }
}
