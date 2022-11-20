FROM openjdk:17
EXPOSE 8080:8081
RUN groupadd -r spring && useradd -r -gapp spring
USER spring:spring
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} "app.jar"
COPY docker-entrypoint.sh /
#RUN chown -R spring:spring ${APP_HOME}
RUN chmod +x /docker-entrypoint.sh
ENTRYPOINT ["java","-jar","app.jar"]
CMD ["/docker-entrypoint.sh"]
