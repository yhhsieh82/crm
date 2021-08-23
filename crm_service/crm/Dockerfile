FROM openjdk:8-jdk-alpine
# VOLUME /tmp
COPY ./crm_home /crm_home

# set working dir in the container, s.t. the script works
WORKDIR /crm_home

# this is the step which will be executed at docker run (container initialization)
ENTRYPOINT ["sh","/crm_home/docker_start.sh"]

