FROM jeanblanchard/java:8
MAINTAINER Tommy Brettschneider
COPY files/pinterest-0.0.1-SNAPSHOT.jar /opt/pinterest-clone/
ENTRYPOINT []
CMD ["/opt/jdk/bin/java","-jar", "/opt/pinterest-clone/pinterest-0.0.1-SNAPSHOT.jar"]
EXPOSE 8080
