package cn.az.java.mp.config;

import cn.az.java.mp.filter.MySqlParserFilter;
import cn.az.java.mp.handler.MyTenantHandler;
import com.baomidou.mybatisplus.core.parser.ISqlParser;
import com.baomidou.mybatisplus.extension.parsers.DynamicTableNameParser;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.tenant.TenantSqlParser;
import org.springframework.aop.interceptor.PerformanceMonitorInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author az
 * @since 2020-04-03
 */
@Configuration
public class MybatisPlusConfig {

    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor() {
        return new OptimisticLockerInterceptor();
    }

    @Bean
    public PerformanceMonitorInterceptor performanceMonitorInterceptor() {
        return new PerformanceMonitorInterceptor();
    }

    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor interceptor = new PaginationInterceptor();

        List<ISqlParser> sqlParserList = new ArrayList<>();

        DynamicTableNameParser dynamicTableNameParser = new DynamicTableNameParser();
        dynamicTableNameParser.setTableNameHandlerMap(Map.of());

        TenantSqlParser tenantSqlParser = new TenantSqlParser();
        tenantSqlParser.setTenantHandler(new MyTenantHandler());

        sqlParserList.add(tenantSqlParser);
        sqlParserList.add(dynamicTableNameParser);

        interceptor.setSqlParserList(sqlParserList);
        interceptor.setSqlParserFilter(new MySqlParserFilter());
        return interceptor;
    }
}
