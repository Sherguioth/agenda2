FROM openjdk:17-oracle as build

COPY . /app
WORKDIR /app

RUN chmod +x ./gradlew
RUN ./gradlew build

FROM openjdk:17-oracle

LABEL version="1.0" description="Agenda2 project" maintainer="Ezequiel Melo"
ENV JAVA_OPTS="-XX:+UseContainerSupport -Djava.security.egd=file:/dev/./urandom"

COPY --from=build /app/build/libs/*.jar app.jar
EXPOSE 8080

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]