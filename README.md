# triple-hao 说明

## 项目 架构图
![项目 架构图](https://github.com/jascao/triple-hao/blob/master/readme-src/1301514861787_.pic.jpg?raw=true)

## dubbo 架构图
![dubbo 架构图](https://github.com/jascao/triple-hao/blob/master/readme-src/1331514861855_.pic.jpg?raw=true)

## mongodb sharding 架构图
![mongodb sharding 架构图](https://github.com/jascao/triple-hao/blob/master/readme-src/1351514861872_.pic.jpg?raw=true)

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