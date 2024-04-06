ARGS="--spring.datasource.dynamic.datasource.master.url=${MASTER_DATASOURCE_URL:-jdbc:mysql://localhost:13306/ncst-smart-vehicle?useSSL=false&serverTimezone=Asia/Shanghai&allowPublicKeyRetrieval=true&nullCatalogMeansCurrent=true}
        --spring.datasource.dynamic.datasource.master.username=${MASTER_DATASOURCE_USERNAME:-root}
        --spring.datasource.dynamic.datasource.master.password=${MASTER_DATASOURCE_PASSWORD:-123456}
        --spring.datasource.dynamic.datasource.slave.url=${SLAVE_DATASOURCE_URL:-jdbc:mysql://localhost:13306/ncst-smart-vehicle?useSSL=false&serverTimezone=Asia/Shanghai&allowPublicKeyRetrieval=true&nullCatalogMeansCurrent=true}
        --spring.datasource.dynamic.datasource.slave.username=${SLAVE_DATASOURCE_USERNAME:-root}
        --spring.datasource.dynamic.datasource.slave.password=${SLAVE_DATASOURCE_PASSWORD:-123456}
        --spring.redis.host=${REDIS_HOST:-vehicle-redis}"
JAVA_OPTS="-Xms512m
      -Xmx512m
      -Djava.security.egd=file:/dev/./urandom "

java $JAVA_OPTS -jar xxx.jar $ARGS
