#!/bin/bash

set APP_ENV="frontier"
set APP_ARGS=-Dethereumj.conf.file=%APP_SRC_DIR%/main/resources/conf/%APP_ENV%.conf


echo "JAVA_OPTS: %JAVA_OPTS%"
echo "APP_ARGS: %APP_ARGS%"

gradlew clean bootRun -PjvmArgs="-Xms3g -Xmx4g" %APP_ARGS% > out.log