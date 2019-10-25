From openjdk:8
copy ./target/rbms-1.0.jar rbms-1.0.jar
CMD ["java","-jar","rbms-1.0.jar"]