# Hadoop's playbook notes

## Hadoop's architect

<br>
<p>
Before configuring the master and worker nodes, it’s important to understand the different components of a Hadoop cluster.

A master node maintains knowledge about the distributed file system, like the inode table on an ext3 filesystem, and schedules resources allocation. node-master will handle this role in this guide, and host two daemons:

The NameNode manages the distributed file system and knows where stored data blocks inside the cluster are.
The ResourceManager manages the YARN jobs and takes care of scheduling and executing processes on worker nodes.
Worker nodes store the actual data and provide processing power to run the jobs. They’ll be node1 and node2, and will host two daemons:

The DataNode manages the physical data stored on the node; it’s named, NameNode.
The NodeManager manages execution of tasks on the node.
</p>
<br>


## Useful command

```
# Start hdfs
start-dfs.sh

# Check hdfs status
jps

# Stop hdfs
stop-dfs.sh

# Start yarn
start-yarn.sh

# Stop yarn
stop-yarn.sh

```


## Monitor
- Yarn: http://node-master-IP:8088
- HDFS: http://node-master-IP:9870


## Note issue when install 

### Issue

```
2022-08-11 14:14:41,030 INFO org.apache.hadoop.ipc.Client: Retrying connect to server: master/192.168.0.100:51119. Already tried 5 time(s); retry policy is RetryUpToMaximumCountWithFixedSleep(maxRetries=10, sleepTime=1000 MILLISECONDS)
```

### Possible solution
1. Kill all process
2. Run `hdfs datanode -format` on wokers node
3. Comment out following line of /etc/hosts on master node
```
#127.0.1.1 master.local.com master
```
