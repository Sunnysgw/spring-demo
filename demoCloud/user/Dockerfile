# 基础镜像，openjkd使用8版本
FROM openjdk:11
# 作者
MAINTAINER sgw
# 系统编码
ENV LANG=C.UTF-8 LC_ALL=C.UTF-8
# 声明一个挂载点，容器内此路径会对应宿主机的某个文件夹
VOLUME /tmp
# 应用构建成功后的jar文件被复制到镜像内，名字也改成了app.jar
ADD target/user-1.0-SNAPSHOT.jar user.jar
# 启动容器时的进程
ENTRYPOINT ["java","-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=9093","-Dfile.encoding=UTF-8","-jar","/user.jar"]
# 暴露8080端口
EXPOSE 8080
EXPOSE 9093