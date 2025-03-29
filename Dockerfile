FROM openjdk:17
COPY target/JoseBD-1-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8108
ENTRYPOINT [ "java","-jar", "app.jar" ]