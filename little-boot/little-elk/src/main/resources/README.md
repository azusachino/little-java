# ELK

## elastic search

```shell
vim elasticsearch.yml

network.host: 0.0.0.0
http.port: 9200
http.cors.enabled: true
http.cors.allow-origin: '*'
```

由于ES的启动不能用root账号直接启动，需要新创建用户，然后切换新用户去启动

```shell
groupadd elsearch
useradd elsearch -g elsearch -p elasticsearch
cd /data/deploy/elk/
chown -R elsearch:elsearch elasticsearch-6.4.3
-- 切换用户，启动
su elsearch
cd elasticsearch-6.4.3/bin
sh elasticsearch &
```

启动过程中可能出现的问题：

1、max file descriptors [4096] for elasticsearch process is too low, increase to at least [65536]

2、max virtual memory areas vm.max_map_count [65530] is too low, increase to at least [262144]

1. 将当前用户的软硬限制调大

```shell
# vim /etc/security/limits.conf
-- 在后面增加一下配置后，保存退出
es soft nofile 65535
es hard nofile 65537
-- 不需要重启，重新登录即生效
-- 查看修改命名是否生效
# ulimit -n 65535
# ulimit -n
-- 结果65535
# ulimit -H -n 65537
# ulimit -H -n
-- 结果65537
```

2. 调大elasticsearch用户拥有的内存权限

```sh
-- 切换到root用户
# sysctl -w vm.max_map_count=262144
-- 查看修改结果
# sysctl -a|grep vm.max_map_count
-- 结果显示：vm.max_map_count = 262144

-- 永久生效设置
# vim /etc/sysctl.conf
-- 在文件最后增加以下内容，保存后退出：
vm.max_map_count=262144
```

## kibana

```sh
# tar -zxvf kibana-6.4.2-linux-x86_64.tar.gz
# cd kibana-6.4.2-linux-x86_64/config/
# vim kibana.yml
-- 增加如下配置：
server.port: 5601
server.host: "0.0.0.0"
elasticsearch.url: "http://localhost:9200"
kibana.index: ".kibana"
```

## logstash

```sh
# tar -zxvf logstash-6.4.2.tar.gz
# cd logstash-6.4.2/bin
-- 新增编辑配置文件
# vim logstash.conf
-- 增加以下内容：
input {
    tcp {
        port => 5044
        codec => json_lines
    }
}
output {
    elasticsearch {
        hosts => ["localhost:9200"]
    }
}

nohup sh logstash -f logstash.conf &
```