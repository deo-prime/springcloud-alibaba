# 版本说明：
https://github.com/alibaba/spring-cloud-alibaba/wiki/%E7%89%88%E6%9C%AC%E8%AF%B4%E6%98%8E

# 文档地址
https://spring-cloud-alibaba-group.github.io/github-pages/hoxton/en-us/index.html

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
