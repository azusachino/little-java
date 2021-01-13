package cn.az.java.cloud.rule;

import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.Server;

import java.util.List;
import java.util.Objects;

/**
 * @author az
 * @date 2020/4/12
 */
public class MyRule implements IRule {

    @Override
    public Server choose(Object key) {
        ILoadBalancer loadBalancer = getLoadBalancer();

        List<Server> serverList = loadBalancer.getReachableServers();

        if (Objects.isNull(serverList)) {
            return null;
        }
        return serverList.get(serverList.size() - 1);
    }

    @Override
    public void setLoadBalancer(ILoadBalancer lb) {

    }

    @Override
    public ILoadBalancer getLoadBalancer() {
        return null;
    }
}
