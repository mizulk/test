# 测试git团队协作的一个远程仓库

## 可能遇到的问题
`$ git push test master`
`fatal: unable to access 'https://github.com/mizudp/test.git/': SSL certificate problem: unable to get local issuer certificate`
**如果遇到该问题的话请用下面的命令来解决**
`git config --global http.sslVerify false`

