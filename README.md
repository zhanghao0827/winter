# winter后台管理系统

>**演示**：http://120.48.10.133:8000/index.html
>
>**前端项目地址**：https://github.com/zhanghao0827/winter-vue
>
>**个人主页**：https://hao2coding.gitee.io/icoding

## 介绍

winter后台管理系统是一个Web应用脚手架，基础的功能已经实现，在此基础上进行扩展成任意一 个管理系统。 

该系统在性能功能上应达到如下需求:操作简单、界面友好、完全控件式的页面布局， 使得管理系统的操作工作更简便，跟踪出现的提示信息也让用户随时清楚自己的操作情况。 对常见的管理系统的各个方面：信息的录入、浏览、删除、修改、搜索等基础方面都大体实现，实现了网站对信息系统管理的要求

项目采取前后端分离设计，后端完成对数据库的操作，前端完成数据展示，页面跳转。 

项目采用按功能分模块的开发方式，结构如下： 

- winter-admin 为项目入口模块，也是最终需要打包部署的模块 

- winter-system 为系统管理模块，完成对用户，角色，菜单操作 

- winter-core 为核心模块，集成登录，鉴权，验证 token，AOP 切面等功能 

- winter-common 为公共模块，提供各种封装好的工具类 

- winter-monitor 为监控管理模块，提供操作日志，在线用户管理功能



## 快速部署

### 准备工作 

JDK (推荐 1.8 版本) 

Mysql (5.7 或 8.0 版本) 

5/76/7 

Redis >= 3.0 

Maven >= 3.0 

Node >= 10 (推荐长期支持版) 

### 必要配置 

#### 数据库

创 建 mysql 数 据 库 winter （ 字 符 集 为 utf8 ） ， 并 导 入 sql 文 件 `winter/winter-admin/src/main/resources/winter.sql` 

#### 文件上传

推荐使用阿里云OSS，在`org.winter.common.constant.AliyunConstants`配置账户信息及密钥。



### 项目启动 

#### 启动后端 

使用 IntelliJ IDEA(推荐)或 Eclispe 导入后端项目工程 winter，由于 winter 使用 Maven 进行依 

赖管理，IDE 需要集成 Maven 配置，初次导入会自动下载所需依赖包，请耐心等待。(推荐 

Maven 使用阿里云镜像)。 

修改\winter\winter-admin\src\main\resources\application-dev.yml 配置文件，修改 mysql 

和 redis 的连接信息（如：账号，密码，端口等） 

依赖下载完成后，运行 org.winter.admin.WinterApplication 启动类 



#### 启动前端 

1. 进入前端项目 

   cd winter-vue 

2. 安装前端依赖 

   npm install --registry=https://registry.npm.taobao.org 

3. 本地开发模式启动项目 

   npm run dev 

使用浏览器访问：localhost:9528 



### 提示

因为本项目是前后端完全分离的，所以需要前后端都单独启动好，才能进行访问。 

如果 npm install 报错，可以使用安装 yarn 

npm install -g yarn 

yarn config set registry https://registry.npm.taobao.org -g 

使用 yarn install 安装前端所需依赖



## 联系我

>若有任何问题，可添加本人微信


<img src="https://oscimg.oschina.net/oscnet/up-2ca440c372b6aab197b9c34d083da3425ab.jpg" />

