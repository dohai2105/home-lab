# Playing devops note

## Run ansible for kubernete

```
ansible-playbook playbooks/setup-kubernetes
```


## Copy kubectl config from master

```
scp -r masterIP:~/.kube/config localIP:

Ex: scp -r master:~/.kube/config ~/.kube/config_mycluster
```


## Note: Set eth1 to internal Ip ( default is eth0 - 10.0.2.15) for each node when use Vagrantfile

### Add following setting to Vagrantfile

```
    node.vm.provision "shell",
      run: "always",
      inline: "route add default gw 192.168.0.1"
    # node.vm.provision "shell", path: "bootstrap_kmaster.sh"

    # delete default gw on eth0
    node.vm.provision "shell",
      run: "always",
      inline: "eval `route -n | awk '{ if ($8 ==\"eth0\" && $2 != \"0.0.0.0\") print \"route del default gw \" $2; }'`"
```

### Specific internal Ip in setting file (eg : 20-extra-args.conf.j2)

```
[Service]
Environment="KUBELET_EXTRA_ARGS=--fail-swap-on=false --node-ip={{ nodeIP }}"
```

### Rerun

```
ansible-playbook playbooks/setup-kubernetes  
```


## Note: Setup kubernet dashboard

### Create service account and cluster role binding

```
apiVersion: v1
kind: ServiceAccount
metadata:
  name: admin-user
  namespace: kubernetes-dashboard


---

apiVersion: rbac.authorization.k8s.io/v1
kind: ClusterRoleBinding
metadata:
  name: admin-user
roleRef:
  apiGroup: rbac.authorization.k8s.io
  kind: ClusterRole
  name: cluster-admin
subjects:
- kind: ServiceAccount
  name: admin-user
  namespace: kubernetes-dashboard

```

### Create corresponding secret

```
apiVersion: v1
kind: Secret
metadata:
  name: admin-user
  namespace: kubernetes-dashboard
  annotations:
    kubernetes.io/service-account.name: "admin-user"
type: kubernetes.io/service-account-token
data:
  # You can include additional key value pairs as you do with Opaque Secrets
  extra: abcd
```

### Show token
```
kl describe secret admin-user  -n kubernetes-dashboard
```

