FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY ./crm_home /crm_home

# set working dir, s.t. the script works
WORKDIR /crm_home

ENTRYPOINT ["sh","/crm_home/docker_start.sh"]

