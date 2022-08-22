# Issue when run ansible (on MAC)

- Exception : "in progress in another thread when fork() was called..."

Solution:
```
export OBJC_DISABLE_INITIALIZE_FORK_SAFETY=YES
```

- Unachive can't unzip file

Solution:
```
 brew install gnu-tar
 ```