FROM openjdk:22-jdk
COPY target/*.jar opt/regiondict.jar
ENTRYPOINT ["java", "-jar", "opt/regiondict.jar"]