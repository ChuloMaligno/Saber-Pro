FROM openjdk:17
COPY ".target/SaberPro-1-0.0.1-SNAPSHOT.jar" "app.jar"
EXPOSE 6565
ENTRYPOINT [ "java", "-jar", "app.jar" ]