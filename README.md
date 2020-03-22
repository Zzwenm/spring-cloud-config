# spring-cloud-config
spring cloud config test

# 目录结构
spring-cloud-test 为spring cloud 学习的整个项目

- spring cloud test
  - api-common：
    整个项目的公用类
  - config-server：
    spring-cloud-config 学习中的ConfigServer
  - eureka-client：
    注册到eureka的客户端，作为eureka-consumer的提供者，包含主要业务层、持久层的实现
  - eureka-consumer：
    注册到eureka的客户端，作为eureka-client的消费者，通过gateway网关调用eureka-client的服务接口
  - eureka-server：
    eureka-server服务中心
  - gateway：
    spring-gateway 网关，用于鉴权用户token 
