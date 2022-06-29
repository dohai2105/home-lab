## Run ansible for kubernete
```
ansible-playbook playbooks/setup-kubernetes
```

## Copy kubectl config from master
```
scp -r masterIP:~/.kube/config localIP:
```