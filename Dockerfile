FROM openjdk:17
EXPOSE 8080:8081
RUN /usr/sbin/groupadd spring && /usr/sbin/adduser -S spring -G spring
USER spring:spring
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} "app.jar"
ENTRYPOINT ["java","-jar","app.jar"]
