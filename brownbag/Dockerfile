FROM ubuntu:latest
MAINTAINER engineering "engineering@werai.net"
ENV DEBIAN_FRONTEND noninteractive

# resolve https error when using 16.04
RUN apt-get update && apt-get -y install apt-transport-https

# install Java
RUN apt-get update && apt-get install -yqq --no-install-recommends \
      openjdk-8-jre \
      gnupg

# install sbt
RUN echo "deb https://dl.bintray.com/sbt/debian /" | tee -a /etc/apt/sources.list.d/sbt.list
ENV APT_KEY_DONT_WARN_ON_DANGEROUS_USAGE=DontWarn
RUN apt-key adv --keyserver hkp://keyserver.ubuntu.com:80 --recv 2EE0EA64E40A89B84B2DF73499E82A75642AC823 || \
 apt-key adv --keyserver hkp://p80.pool.sks-keyservers.net:80 --recv 2EE0EA64E40A89B84B2DF73499E82A75642AC823 || \
 apt-key adv --keyserver hkp://ha.pool.sks-keyservers.net:80 --recv 2EE0EA64E40A89B84B2DF73499E82A75642AC823 || \
 apt-key adv --batch --keyserver hkp://pgp.mit.edu:80 --recv 2EE0EA64E40A89B84B2DF73499E82A75642AC823 || \
 apt-key adv --batch --keyserver hkp://ipv4.pool.sks-keyservers.net --recv 2EE0EA64E40A89B84B2DF73499E82A75642AC823
RUN apt-get update && apt-get install -yqq --no-install-recommends sbt


# install curl
RUN apt-get update -y && apt-get -y install curl

WORKDIR /brownbag

ADD . .

# to create brownbag jar file
RUN sbt clean compile assembly

EXPOSE 9001

RUN echo `find . -name brownbag-assembly-0.1.jar`

CMD java -jar ./target/scala-2.11/brownbag-assembly-0.1.jar