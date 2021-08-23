JAVA_HOME=/home/cbx/tools/jdk1.8.0_201
JRE_HOME=$JAVA_HOME/jre

API_OPTS="-Xmx2g -Xms2g"

# Start tomcat
echo -e "Starting CRM"

# Find the jar file
CRM_JAR=$(ls crm*.jar | head -1)
echo "Jar file: $CRM_JAR"

# Find the logback configuration file
LOGBACK_FILE=$(find ./config -name "logback.xml" | head -1)
if [ "$LOGBACK_FILE" != "" ]; then
  echo "Logback file: $LOGBACK_FILE"
  LOG_CONFIG="-Dlogging.config="$LOGBACK_FILE
fi

nohup $JRE_HOME/bin/java $API_OPTS $LOG_CONFIG -jar $CRM_JAR &
