--配置hosts
127.0.0.1 slf.eureka.center
127.0.0.1 slf.eureka.center1
127.0.0.1 slf.eureka.center2
127.0.0.1 slf.eureka.center3


--启动单台应用
java -jar eurekaUserService-0.0.1.jar

java -jar eurekaUserService-0.0.1.jar --spring.profiles.active=second
java -jar eurekaUserService-0.0.1.jar --spring.profiles.active=third

--查看集群服务
http://slf.eureka.center1:9001/

E:\workspace\SpringCloud\dest