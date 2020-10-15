# JAVA日志体系

## 内容概览

1. commons-logging.jar、log4j.jar、sl4j-api.jar等日志架构中间的关系
2. 日志关系的引用原理
3. 统一日志输出

# 组件
## Logging
### log4j

### jul (java.util.logging)
jdk1.4之后增加的java.util.logging包

### simpleLog


## 适配器
### jcl (commons-logging-xx.jar)

* 缺点一是效率较低，
* 二是容易引发混乱， 
* 三是在使用了自定义ClassLoader的程序中，使用JCL会引发内存泄露。

### slf4j
log4j的作者觉得jcl不好用，自己又写了一个新的接口api，那么就是slf4j

