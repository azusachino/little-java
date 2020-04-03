package cn.az.java.mp.injector;

import cn.az.java.mp.method.Truncate;
import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;
import com.baomidou.mybatisplus.extension.injector.methods.InsertBatchSomeColumn;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author az
 * @since 2020-04-03
 */
@Component
public class MySqlInjector extends DefaultSqlInjector {

    @Override
    public List<AbstractMethod> getMethodList(Class<?> mapperClass) {
        List<AbstractMethod> abstractMethods = super.getMethodList(mapperClass);
        abstractMethods.add(new Truncate());
        abstractMethods.add(new InsertBatchSomeColumn(t ->
                t.isLogicDelete() && t.isVersion()
        ));
        return abstractMethods;
    }
}
