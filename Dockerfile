FROM eclipse-temurin:21-jdk AS build
WORKDIR /application
ARG JAR_FILE=target/Movies-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} application.jar
RUN java -Djarmode=layertools -jar application.jar extract

FROM eclipse-temurin:21-jre
WORKDIR /application
COPY --from=build /application/dependencies/ ./
COPY --from=build /application/spring-boot-loader/ ./
COPY --from=build /application/snapshot-dependencies/ ./
COPY --from=build /application/application/ ./

ENV TZ=UTC
EXPOSE 8080
ENTRYPOINT ["java", "org.springframework.boot.loader.launch.JarLauncher"]
