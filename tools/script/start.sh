function runDailyJob {
    local -a args=$@
 
    env[0]="-Dsystem.ecs.version=$CONFIG"
    env[1]="-Dsystem.ccmr.environment=$ENV"
    env[2]="-Dspring.profiles.active=daily"
 
    env=("${env[@]}" $args)
    nohup $JAVA_HOME/bin/java -Xmx4096m "${env[@]}" -jar $JAR >/dev/null 2>&1 &
}