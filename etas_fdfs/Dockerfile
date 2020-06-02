# 该镜像需要依赖的基础镜像
FROM java:8
# 设置字符集
ENV LANG en_US.UTF-8
ENV LANGUAGE en_US:en
ENV LC_ALL en_US.UTF-8
# 将当前目录下的jar包复制到docker容器的/目录下
ADD target/etas-fdfs-0.0.1.jar /etas-fdfs-0.0.1.jar
# 设置时区
#RUN /bin/cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime && echo 'Asia/Shanghai' >/etc/timezone
# 运行过程中创建一个mall-tiny-docker-file.jar文件
RUN bash -c 'touch /etas-fdfs-0.0.1.jar'
# 声明服务运行在8080端口
EXPOSE 8095
# 指定docker容器启动时运行jar包
ENTRYPOINT ["java", "-jar", "/etas-fdfs-0.0.1.jar"]
# 指定维护者的名字
MAINTAINER cai