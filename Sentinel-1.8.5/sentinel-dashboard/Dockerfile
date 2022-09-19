FROM openjdk:8
MAINTAINER SGW
ENV LANG=C.UTF-8, LC_ALL=C.UTF-8
ADD target/sentinel-dashboard.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
EXPOSE 8081