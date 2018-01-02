## 项目 架构图
![项目 架构图](https://github.com/jascao/triple-hao/blob/master/readme-src/1351514861872_.pic.jpg?raw=true)

## dubbo 架构图
![dubbo 架构图](https://github.com/jascao/triple-hao/blob/master/readme-src/1331514861855_.pic.jpg?raw=true)

## mongodb sharding 架构图
![mongodb sharding 架构图](https://github.com/jascao/triple-hao/blob/master/readme-src/1301514861787_.pic.jpg?raw=true)

## 项目管理后台代码地址
https://github.com/csdhao/hackathon-ui

## 项目 dubbo-zk-admin 地址
https://hub.docker.com/r/dhso/dubbo-zk-admin/

### dubbo-zk-admin 一键部署
```
docker pull dhso/dubbo-zk-admin

docker run -d -p 52181:2181 -p 58080:8080  -v zk:/root --privileged=true --name dubbo-zk-admin dhso/dubbo-zk-admin

```

### dubbo-zk-admin Dockerfile生成
[https://github.com/jascao/triple-hao/blob/master/readme-src/Dockerfile](https://github.com/jascao/triple-hao/blob/master/readme-src/Dockerfile)

## 项目 mongodb sharding 部署方法
[https://github.com/jascao/triple-hao/blob/master/readme-src/mongodb.md](https://github.com/jascao/triple-hao/blob/master/readme-src/mongodb.md)

## 文件处理性能数据

```
98条： 1,300 ms

1百万： 23,000 ms

1亿： 2,270,336 ms (37min)

```

* 统计时间是从读取文件开始到上传数据到库全部完成为结束，所以统计时间覆盖了整个文件入库周期
* 经过每次5w数据压库测试，单机模式无法抗压，而我们的集群模式完美抗压
* 因为无线网络速率以及副本集集中在3台机上，所以集群效率要小于单机模式
* 以上数据仅供参考，具体性能与索引以及配置模式有关

## 大数据量请求性能数据

```
吞吐量(TPS) 11994

响应时间 0.79ms

```

* 机器为两台mac笔记本，4核8线程, SSD
* 以上数据仅供参考，具体性能与网络以及配置有关

