# Spring-Cloud

# 学习Spring Cloud的记录以及一些总结。爬过的坑。


## Hystrix Dashboard and Trubine 
Hystrix Dashboard 针对单点，而Trubine把相同服务的节点状态以一个整体集群的形式展现出来.</br>
通过 http://localhost:port/hystrix.stream 来进行观察微服务的状态。
```
注解
@EnableHystrixDashboard
@EnableTurbine

配置
turbine.appConfig=node01,node02 微服务名称即 配置Eureka中的serviceId列表
turbine.aggregator.clusterConfig= default 指定聚合哪些集群 默认default
turbine.clusterNameExpression= new String("default") 默认default
假设想要监控的应用配置了eureka.instance.metadata-map.cluster: xxx，则需要配置，
同时turbine.aggregator.clusterConfig: xxx

相关的依赖
<dependency>
	<groupId>org.springframework.cloud</groupId>
	<artifactId>spring-cloud-starter-hystrix</artifactId>
</dependency>
<dependency>
	<groupId>org.springframework.cloud</groupId>
	<artifactId>spring-cloud-starter-hystrix-dashboard</artifactId>
</dependency>
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
<dependency>
	<groupId>org.springframework.cloud</groupId>
	<artifactId>spring-cloud-starter-turbine</artifactId>
</dependency>
<dependency>
  <groupId>org.springframework.cloud</groupId>
  <artifactId>spring-cloud-netflix-turbine</artifactId>
</dependency>
```
