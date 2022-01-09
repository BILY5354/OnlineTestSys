# OnlineTestSys
heima javaWeb 在线面试系统学习  
## 还可以提高的地方
- 现在的生成试卷只能生成Java基础这一小类，因该是在被选择大类中显示出所有的小类进行组卷
- 位于个人历史测试功能，增加做过试题的选项功能
- 位于开始测试功能，增加按学校选择试题和只做经典题的功能
- 位于后台的审核功能，增加审核试题的选项功能
- 位于后台管理功能，增加显示所有学生的做题信息功能  
## [创建数据库语句](./db.md)  
使用版本为5.7，需要连接新的本地数据库需要修改**jdbc.properties**,分别是数据库地址、用户名和密码
```properties
jdbc.url=jdbc:mysql://192.168.23.129:3306/heima_mm1
jdbc.username=root
jdbc.password=root
```
