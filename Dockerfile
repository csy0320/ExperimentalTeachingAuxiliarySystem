# 该镜像需要依赖的基础镜像
FROM acbyac/java-env
# 指定维护者的名字
MAINTAINER cai
# 设置字符集
ENV LANG en_US.UTF-8
ENV LANGUAGE en_US:en
ENV LC_ALL en_US.UTF-8
# 将当前目录下的jar包复制到docker容器的/目录下
#ADD target/etas-user-0.0.1.jar /etas-user-0.0.1.jar
# 设置时区
#RUN /bin/cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime && echo 'Asia/Shanghai' >/etc/timezone
# 运行过程中创建一个mall-tiny-docker-file.jar文件
#RUN bash -c 'touch /etas-user-0.0.1.jar'
# 声明服务运行在8080端口
#EXPOSE 8060
# 指定docker容器启动时运行jar包
#ENTRYPOINT ["java", "-jar", "/etas-user-0.0.1.jar"]

COPY etas_auth/target/etas-auth-0.0.1.jar /etas-auth-0.0.1.jar
COPY etas_fdfs/target/etas-fdfs-0.0.1.jar /etas-fdfs-0.0.1.jar
COPY etas_gateway/target/etas-gateway-0.0.1.jar /etas-gateway-0.0.1.jar
COPY etas_manage/target/etas-manage-0.0.1.jar /etas-manage-0.0.1.jar
COPY etas_search/target/etas-search-0.0.1.jar /etas-search-0.0.1.jar
COPY etas_user/target/etas-user-0.0.1.jar /etas-user-0.0.1.jar


COPY start_etas.sh /usr/bin/start_etas.sh

RUN chmod +x /usr/bin/start_etas.sh
CMD "start_etas.sh"

