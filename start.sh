# !/bin/bash
echo "shell script to exceute crm jar."

# jar name 
jar_name=crm-0.0.1-SNAPSHOT.jar
echo ${jar_name}


java -Dlogging.level.org.springframework=DEBUG -jar ${jar_name}

