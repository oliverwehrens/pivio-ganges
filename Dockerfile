FROM java:8-jre
ARG http_proxy
ARG HTTP_PROXY

EXPOSE 8080

ADD build/libs/ganges-0.0.1-SNAPSHOT.jar /ganges-0.0.1-SNAPSHOT.jar

CMD ["java", "-Djava.net.useSystemProxies=true", "-jar", "/ganges-0.0.1-SNAPSHOT.jar"]
