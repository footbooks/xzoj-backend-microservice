# 小曾oj判题系统微服务改造

## 模块划分

1. xzoj-backend-common 公共模块（用于存放公共配置类、工具类、常量、全局异常类等）

2. xzoj-backend-model 实体类模块（用于存放与数据库和前端交互的所有实体对象）

3. xzoj-backend-service-client 公共接口模块 （用于存放公共接口，用于系统内部调用）

4. xzoj-backend-judge-service 判题服务模块 （用于实现系统判题逻辑）

5. xzoj-backend-user-service 用户服务模块 （用于实现用户登录、注册等功能的实现）

6. xzoj-backend-question-service 题目服务模块 （用于管理题目相关的操作）

7. xzoj-backend-gateway 微服务网关模块 (用于聚合所有接口，统一处理前端请求，再根据路由转发到各个子模块)

## 技术亮点

1. 根据系统的功能需求，使用了大量的设计模式实现对系统健壮性、稳定性、可维护性的增强，减少冗余代码

2. 由于判题服务中判题功能过重，使用rabbitmq对该功能中的执行代码功能进行异步解耦。

3. 代码沙箱为了保证接口的安全性，采用API签名认证的方式保证接口的安全性。

4. 为了保证内部接口的安全性，在gateway网关层添加访问路径拦截器，禁止前端调用内部接口

5. 为了实现各服务子模块之间的解耦，添加公共接口模块，服务与服务之间的调用使用openFeign进行调用。

## 

**代码沙箱地址：** [footbooks/xiaoZeng-oj-sandBox: 小曾oj系统的代码沙箱 (github.com)](https://github.com/footbooks/xiaoZeng-oj-sandBox)



# 


