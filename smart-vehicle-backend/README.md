* 演示地址【Vue3 + element-plus】：<http://dashboard-vue3.vehicle.iocoder.cn>
* 启动文档：<https://doc.iocoder.cn/quick-start/>
* 视频教程：<https://doc.iocoder.cn/video/>

mysql  脚本执行顺序。
1. ./



前置准备

启动
1. 启动mysql, redis
docker compose --file '/home/yly_no_nvidia/Desktop/smart-vehicle/smart-vehicle-backend/docker-compose.yml' --env-file '/home/yly_no_nvidia/Desktop/smart-vehicle/smart-vehicle-backend/docker.env' --project-name 'vehicle-system' restart'
这个命令在， runDocker.sh中，修该路径就行
2. 启动java
mvn install -Dmaven.test.skip=true 
mvn package -Dmaven.test.skip=true
java -jar vehicle-server/target/.java
这个命令在， run.sh中

3. 启动前端
cd samrt-vehicle-front
nix develop
npm run dev

4. 导入接口文档，apifox


