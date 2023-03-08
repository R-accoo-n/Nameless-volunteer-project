FROM openjdk:11
COPY . /project
RUN cd /project
RUN cp /project/target/*.jar /app.jar
CMD ["java", "-jar", "/app.jar"]