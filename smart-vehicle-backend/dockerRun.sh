SRC_DIR=$(cd $(dirname $0) && pwd)
echo $SRC_DIR 
docker-compose --file "$SRC_DIR/docker-compose.yml" --env-file "$SRC_DIR/docker.env" --project-name "vehicle-system" up mysql redis -d
docker-compose --file "$SRC_DIR/docker-compose.yml" --env-file "$SRC_DIR/docker.env" --project-name "vehicle-system" restart mysql redis 
