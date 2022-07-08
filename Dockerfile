FROM openjdk:8
EXPOSE 8080
ADD target/employeecruddocker-0.0.1-snapshot.jar employeecruddocker-0.0.1-snapshot.jar
ENTRYPOINT ["java", "-jar", "/employeecruddocker-0.0.1-snapshot.jar"]
