# Hadoop's playbook notes

## Hadoop's architect

<p>
Before configuring the master and worker nodes, it’s important to understand the different components of a Hadoop cluster.

A master node maintains knowledge about the distributed file system, like the inode table on an ext3 filesystem, and schedules resources allocation. node-master will handle this role in this guide, and host two daemons:

The NameNode manages the distributed file system and knows where stored data blocks inside the cluster are.
The ResourceManager manages the YARN jobs and takes care of scheduling and executing processes on worker nodes.
Worker nodes store the actual data and provide processing power to run the jobs. They’ll be node1 and node2, and will host two daemons:

The DataNode manages the physical data stored on the node; it’s named, NameNode.
The NodeManager manages execution of tasks on the node.
</p>