## 拉取mongo
docker pull mongo

====================
## 配置服务器
docker run -d -p 10001:10001 --privileged=true -v cnf10001:/data/db --name config01_srv mongo:latest --configsvr --port 10001 --dbpath /data/db --replSet crs01

====================
## 分片服务器
docker run -d -p 20001:20001 --privileged=true -v db20001:/data/db --name shard01_srv mongo:latest --shardsvr --port 20001 --dbpath /data/db --replSet shard01

docker run -d -p 20002:20002 --privileged=true -v db20002:/data/db --name shard02_srv mongo:latest --shardsvr --port 20002 --dbpath /data/db --replSet shard02

docker run -d -p 20003:20003 --privileged=true -v db20003:/data/db --name shard03_srv mongo:latest --shardsvr --port 20003 --dbpath /data/db --replSet shard03

docker run -d -p 20004:20004 --privileged=true -v db20004:/data/db --name shard04_srv mongo:latest --shardsvr --port 20004 --dbpath /data/db --replSet shard04

====================
## 仲裁节点
docker run -d -p 30001:30001 --privileged=true -v db30001:/data/db --name shard01_arb_srv mongo:latest --shardsvr --port 30001 --dbpath /data/db --replSet shard01

docker run -d -p 30002:30002 --privileged=true -v db30002:/data/db --name shard02_arb_srv mongo:latest --shardsvr --port 30002 --dbpath /data/db --replSet shard02

docker run -d -p 30003:30003 --privileged=true -v db30003:/data/db --name shard03_arb_srv mongo:latest --shardsvr --port 30003 --dbpath /data/db --replSet shard03

docker run -d -p 30004:30004 --privileged=true -v db30004:/data/db --name shard04_arb_srv mongo:latest --shardsvr --port 30004 --dbpath /data/db --replSet shard04

====================
## 路由服务器
docker run -d -p 40001:40001 --privileged=true --name router01_svr mongo:latest mongos --configdb crs01/10.207.2.57:10001,10.207.2.91:10001,10.207.2.144:10001 --port 40001 --bind_ip 0.0.0.0


====================
## [设置配置服务器]
### 任意选择一个副本
mongo --port 10001

### 切换数据库
use admin

### 写配置文件
config = {_id:"crs01", configsvr:true, members:[ {_id:0,host:"10.207.2.57:10001"}, {_id:1,host:"10.207.2.91:10001"}, {_id:2,host:"10.207.2.144:10001"} ] }


### 初始化配置
rs.initiate(config)

### 如果已经初始化过，使用下面的强制配置
rs.reconfig(config,{force:true})

### 查看状态
rs.status()

====================
## [设置分片服务器]
### 任意选择shard01分片的一个副本
mongo --port 20001

### 切换数据库
use admin

### 写配置文件
config = {_id:"shard01",members:[ {_id:0,host:"10.207.2.57:20001"},{_id:1,host:"10.207.2.91:20001"}, {_id:2,host:"10.207.2.144:20001"}, {_id:3,host:"10.207.2.144:30001",arbiterOnly:true}, {_id:4,host:"10.207.2.91:30001",arbiterOnly:true}, {_id:5,host:"10.207.2.57:30001",arbiterOnly:true} ] }

config = {_id:"shard02",members:[ {_id:0,host:"10.207.2.91:20002"},{_id:1,host:"10.207.2.144:20002"}, {_id:2,host:"10.207.2.57:20002"}, {_id:3,host:"10.207.2.57:30002",arbiterOnly:true}, {_id:4,host:"10.207.2.144:30002",arbiterOnly:true}, {_id:5,host:"10.207.2.91:30002",arbiterOnly:true} ] }

config = {_id:"shard03",members:[ {_id:0,host:"10.207.2.144:20003"},{_id:1,host:"10.207.2.57:20003"}, {_id:2,host:"10.207.2.91:20003"}, {_id:3,host:"10.207.2.57:30003",arbiterOnly:true}, {_id:4,host:"10.207.2.91:30003",arbiterOnly:true}, {_id:5,host:"10.207.2.144:30003",arbiterOnly:true} ] }

config = {_id:"shard04",members:[ {_id:0,host:"10.207.2.57:20004"},{_id:1,host:"10.207.2.91:20004"}, {_id:2,host:"10.207.2.144:20004"}, {_id:3,host:"10.207.2.144:30004",arbiterOnly:true}, {_id:4,host:"10.207.2.91:30004",arbiterOnly:true}, {_id:5,host:"10.207.2.57:30004",arbiterOnly:true} ] }

### 增加一个成员，用于存储数据
rs.add("host:port")

### 增加一个arbiter，用于选举
rs.add("host:port",true)

### 删除成员
rs.remove("host")

### 使当前的Secondary 节点能够读取数据
rs.slaveOk()

### 初始化副本集配置
rs.initiate(config)

### 如果已经初始化过，使用下面的强制配置
rs.reconfig(config,{force:true})

### 查看副本集状态
rs.status()


====================
## [设置路由服务器]
### 选择路由服务
mongo --port 40001

### 切换数据库
use admin

### 添加shard
db.runCommand({addshard:"shard01/10.207.2.57:20001,10.207.2.91:20001,10.207.2.144:20001"})
db.runCommand({addshard:"shard02/10.207.2.57:20002,10.207.2.91:20002,10.207.2.144:20002"})
db.runCommand({addshard:"shard03/10.207.2.57:20003,10.207.2.91:20003,10.207.2.144:20003"})
db.runCommand({addshard:"shard04/10.207.2.57:20004,10.207.2.91:20004,10.207.2.144:20004"})

### 强制刷新同步
db.adminCommand({"flushRouterConfig":1})

### 查询结果 [仲裁节点不显示]
db.runCommand({listshards:1})

```
{
    "shards" : [
        {
            "_id" : "rs1",
            "host" : "rs1/192.168.31.82:20001,192.168.31.82:20002"
        },
        {
            "_id" : "rs2",
            "host" : "rs2/192.168.31.82:30001,192.168.31.82:30002"
        }
    ],
    "ok" : 1
}
```

## 测试示例

```
sh.enableSharding("triple_hao") 

sh.shardCollection("triple_hao.customer", { "_id": "hashed"})

db.triple_hao.ensureIndex({'生日年':1, '性别':1, '省份':1})


sh.shardCollection("triple_hao.customer", {'生日年':1, '性别':1, '省份':1})


db.printShardingStatus()

sh.startBalancer()

sh.status()

db.customer.stats()

```


```
use triple_hao  
db.customer.ensureIndex({'生日年':1, '性别':1, '省份':1})

db.customer.getIndexes()

db.runCommand({enablesharding:"triple_hao"})

db.runCommand({shardcollection:"triple_hao.customer", key:{'生日年':1, '性别':1, '省份':1}})

db.customer.stats()

```

## dubbo-zk-admin
====================
```

docker save -o dubbo-zk-admin.tar dhso/dubbo-zk-admin

docker load < dubbo-zk-admin.tar

docker run -d -p 52181:2181 -p 58080:8080  -v zk:/root --privileged=true --name dubbo-zk-admin dhso/dubbo-zk-admin
```

