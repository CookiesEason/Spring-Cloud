# Spring-Cloud
学习Spring Cloud的记录以及一些总结。爬过的坑。
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
## Spring Cloud Config
* 提供服务端和客户端支持
* 集中管理各环境的配置文件
* 配置文件修改之后，可以快速的生效
### server端 和 配置
```
<dependencies>
	<dependency>
		<groupId>org.springframework.cloud</groupId>
		<artifactId>spring-cloud-config-server</artifactId>
	</dependency>
</dependencies>
cloud:
    config:
      server:
        git:
          uri: https://github.com/CookiesEason/Spring-Cloud/     # 配置git仓库的地址
          search-paths: config-repo                             # git仓库地址下的相对地址，可以配置多个，用,分割。
          username:                                             # git仓库的账号
          password:                                             # git仓库的密码
```
Spring Cloud Config也提供本地存储配置的方式。我们只需要设置属性spring.profiles.active=native，Config Server会默认从应用的src/main/resource目录下检索配置文件。也可以通过spring.cloud.config.server.native.searchLocations=file:E:/properties/属性来指定配置文件的位置。虽然Spring Cloud Config提供了这样的功能，但是为了支持更好的管理内容和版本控制的功能，还是推荐使用git的方式。<br>
启动类注解
```@EnableConfigServer```
如果配置文件的名称为 name-xxx.properties，则访问地址 http://localhost:port/name/xxx
仓库中的配置文件会被转换成web接口，访问可以参照以下的规则：
* /{application}/{profile}[/{label}]
* /{application}-{profile}.yml
* /{label}/{application}-{profile}.yml
* /{application}-{profile}.properties
* /{label}/{application}-{profile}.properties
### client端 和配置
配置文件需要另外创建进行配置，配置详情如下：
```
spring.cloud.config.name=name
spring.cloud.config.profile=dev
spring.cloud.config.uri=http://localhost:port/
spring.cloud.config.label=master
```
> 特别注意：上面这些与spring-cloud相关的属性必须配置在bootstrap.properties中，config部分内容才能被正确加载。因为config的相关配置会先于application.properties，而bootstrap.properties的加载也是先于application.properties。
<br>启动类注解
```@EnableConfigClient```
