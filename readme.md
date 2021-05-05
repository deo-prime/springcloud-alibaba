# 版本说明：
https://github.com/alibaba/spring-cloud-alibaba/wiki/%E7%89%88%E6%9C%AC%E8%AF%B4%E6%98%8E

# 文档地址
https://spring-cloud-alibaba-group.github.io/github-pages/hoxton/en-us/index.html

https://nacos.io/zh-cn/docs/quick-start-spring-cloud.html
# 20210503 配置管理
在demo-server中添加
```java
        <dependency>
            <groupId>com.alibaba.cloud</groupId>
            <artifactId>spring-cloud-starter-alibaba-nacos-config</artifactId>
        </dependency>
```
在nacos-》配置管理-》配置列表-》新建配置
```java
dataid=demo-service.properties # 这个值和配置文件中的spring.application.name保持一致
group：不变
配置格式选择：properties
配置内容：
avengers.name=Rogers
avengers.weapon=shield
```

新建ConfigController，输出上面的两个配置
在controller上面添加注解：@RefreshScope可以实时刷新配置

如果项目的maven不正常，可以在pom上右键-maven-reimport

## 原理
https://nacos.io/zh-cn/docs/quick-start-spring-cloud.html

在 Nacos Spring Cloud 中，dataId 的完整格式如下：
```java
${prefix}-${spring.profiles.active}.${file-extension}
prefix 默认为 spring.application.name 的值，也可以通过配置项 spring.cloud.nacos.config.prefix来配置。
spring.profiles.active 即为当前环境对应的 profile，详情可以参考 Spring Boot文档。 注意：当 spring.profiles.active 为空时，对应的连接符 - 也将不存在，dataId 的拼接格式变成 ${prefix}.${file-extension}
file-exetension 为配置内容的数据格式，可以通过配置项 spring.cloud.nacos.config.file-extension 来配置。目前只支持 properties 和 yaml 类型。
```