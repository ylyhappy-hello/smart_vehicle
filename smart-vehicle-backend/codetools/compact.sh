# read -p '请输入目标文目录，codegen-*所在目录' targetDir
targetDir=$1
resultDir=$1/result

# 每次重新生成
if [ -d $resultDir ]; then
  rm -r $resultDir
fi

mkdir -p $resultDir
mkdir -p $resultDir/errCode
mkdir -p $resultDir/totalCode
mkdir -p $resultDir/front


if [ ! $targetDir ]; then
  echo 请输入目标目录
  exit 1
fi

if [ ! -d $targetDir ]; then
  echo 目标目录不存在
  exit 1
fi

idx=0
for f in $targetDir/code*; do
  if [ -d $f ]; then
    # 合并sql
    for sql_sf in $f/sql/*; do
      if [ -f $sql_sf ] && [ ${sql_sf##*/} == 'sql.sql' ]; then
        cat $sql_sf >> $resultDir/total.sql
      fi
    done
    # 合并java 代码
    for java_sf in $f/vehicle-module-*/*; do
      # 手动操作
      errCodeDir=$java_sf/src/main/java/com/ncst/vehicle/module/*/enums;
      if [ -d $errCodeDir ]; then
        idx=`expr $idx + 1`
        for err_f in $errCodeDir/*; do
          cp $err_f $resultDir/errCode/$idx-errCode.java
        done

      # contrller, service
      else
        cp $java_sf -r $resultDir/totalCode
      fi
    done

    # 合并 前端代码
    [ ! -d $f/vehicle-ui-admin-vue3 ] && continue;
    for ui_sf in $f/vehicle-ui-admin-vue3/*; do
      cp $ui_sf -r $resultDir/front
    done
  fi
done

