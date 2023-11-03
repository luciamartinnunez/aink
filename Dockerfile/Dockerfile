FROM eclipse-temurin:17

COPY ./doctor-fis-service-*jar /usr/src/doctor-fis/doctor-fis-service.jar
WORKDIR /usr/src/doctor-fis

COPY . ./

EXPOSE 9000

CMD ["java", "-jar", "./doctor-fis-service.jar"]
