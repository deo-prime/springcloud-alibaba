# 版本说明：
https://github.com/alibaba/spring-cloud-alibaba/wiki/%E7%89%88%E6%9C%AC%E8%AF%B4%E6%98%8E

# 文档地址
[https://spring-cloud-alibaba-group.github.io/github-pages/hoxton/en-us/index.html][spring官方文档]
[https://nacos.io/zh-cn/docs/quick-start-spring-cloud.html][nacos官方文档]

# 踩的坑
1. dataid中的prefix没有和spring.application.name完全一致
2. 在nacos中配置的内容不能包含空格，比如这样配置是不会正常加载的`avengers.name = Stark`

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

# 20210505 config 增加profile
根据dataid格式，中间可以添加profile，则在application.properties中添加：
```java
spring.profiles.active=prod
```
然后在nacos中注册：
```java
dataid: demo-service-prod.properties
group: DEFAULT_GROUP
配置格式：properties
内容：
avengers.name=Stark
avengers.weapon=Mark
```
测试dataid的配置是否可以获取：
```
curl -X GET "http://127.0.0.1:8848/nacos/v1/cs/configs?dataId=demo-service-test.properties&group=DEFAULT_GROUP"
```
由于踩了内容不能含有多于空格的坑，所以顺带着看了一眼代码，确认一下加载的配置信息是否正常：
`org.springframework.cloud.bootstrap.config.PropertySourceBootstrapConfiguration.initialize`




[spring官方文档]: https://spring-cloud-alibaba-group.github.io/github-pages/hoxton/en-us/index.html

[nacos官方文档]: https://nacos.io/zh-cn/docs/quick-start-spring-cloud.html