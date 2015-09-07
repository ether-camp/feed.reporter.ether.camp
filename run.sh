export APP_ENV="frontier"
export APP_ARGS="-Dethereumj.conf.file=$APP_SRC_DIR/main/resources/conf/$APP_ENV.conf"

export JAVA_OPTS="-Xms3g -Xmx4g"

echo "JAVA_OPTS: $JAVA_OPTS"
echo "APP_ARGS: $APP_ARGS"

./gradlew clean bootRun -PjvmArgs="$JAVA_OPTS" $APP_ARGS > out.log