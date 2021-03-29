package cn.az.boot.metrics.controller;

import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.DistributionSummary;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Random;

/**
 * @author az
 * @since 03/28/21 11:30
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Resource
    private MeterRegistry meterRegistry;

    private Counter counter;
    private DistributionSummary summary;

    @PostConstruct
    private void init() {
        this.counter = meterRegistry.counter("requests_order_total", "order", "demo");
        this.summary = meterRegistry.summary("order_amount_total", "totalAmount", "demo");
    }

    @GetMapping
    public String order() {
        Random random = new Random();
        summary.record(random.nextInt(100));
        return "order";
    }

    @Timed
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/demo")
    public String demo() {
        this.counter.increment();
        return "demo";
    }


}
