<template>
  <ContentWrap>
    <!-- 搜索工作栏 -->
    <el-form
      class="-mb-15px"
      :model="queryParams"
      ref="queryFormRef"
      :inline="true"
      label-width="68px"
    >
      <el-form-item label="设备编号" prop="illeageActionId">
        <el-input
          v-model="queryParams.illeageActionId"
          placeholder="请输入设备编号"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="地区编码" prop="deviceRegionCode">
        <el-input
          v-model="queryParams.deviceRegionCode"
          placeholder="请输入地区编码"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="设备ip" prop="deviceRegionCode">
        <el-input
          v-model="queryParams.deviceIp"
          placeholder="请输入设备ip"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="创建时间" prop="createTime">
        <el-date-picker
          v-model="queryParams.createTime"
          value-format="YYYY-MM-DD HH:mm:ss"
          type="daterange"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
          :default-time="[new Date('1 00:00:00'), new Date('1 23:59:59')]"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item>
        <el-button @click="handleQuery"><Icon icon="ep:search" class="mr-5px" /> 搜索</el-button>
        <el-button @click="resetQuery"><Icon icon="ep:refresh" class="mr-5px" /> 重置</el-button>
        <el-button
          type="primary"
          plain
          @click="openForm('create')"
          v-hasPermi="['motocycleBike:bike-entity:create']"
        >
          <Icon icon="ep:plus" class="mr-5px" /> 新增
        </el-button>
        <el-button
          type="success"
          plain
          @click="handleExport"
          :loading="exportLoading"
          v-hasPermi="['motocycleBike:bike-entity:export']"
        >
          <Icon icon="ep:download" class="mr-5px" /> 导出
        </el-button>
      </el-form-item>
    </el-form>
  </ContentWrap>

  <!-- 列表 -->
  <ContentWrap>
    <el-table v-loading="loading" :data="list">
      <el-table-column width="60" label="编号" align="center" prop="id" />
      <el-table-column label="设备编号" align="center" prop="illeageActionId" />
      <el-table-column label="软件版本" align="center" prop="deviceOs" />
      <el-table-column label="设备ip" align="center" prop="deviceIp" />
      <el-table-column label="设备区域" align="center" prop="deviceRegion" />
      <el-table-column label="设备分组" align="center" prop="deviceGroup" />
      <el-table-column label="区域编码" align="center" prop="deviceRegionCode" />
      <el-table-column label="GPS坐标" align="center" prop="devicePosition" />
      <el-table-column label="操作" width="200" align="center">
        <template #default="scope">
          <el-button link type="primary" @click="openForm('update', scope.row.id)">
            编辑
          </el-button>
          <el-button link type="success" @click="handleSSH(scope.row.id)"> SSH </el-button>
          <el-button link type="danger" @click="handleDelete(scope.row.id)"> 删除 </el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页 -->
    <Pagination
      :total="total"
      v-model:page="queryParams.pageNo"
      v-model:limit="queryParams.pageSize"
      @pagination="getList"
    />
  </ContentWrap>

  <!-- 表单弹窗：添加/修改 -->
  <BikeEntityForm ref="formRef" @success="getList" />
</template>

<script setup lang="ts" name="BikeEntity">
import download from '@/utils/download'
import * as BikeEntityApi from '@/api/motocycleBike/bikeEntity'
import BikeEntityForm from './BikeEntityForm.vue'
const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化

const loading = ref(true) // 列表的加载中
const total = ref(0) // 列表的总页数
const list = ref([]) // 列表的数据
const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  illeageActionId: null,
  deviceRegionCode: null,
  deviceGroup: null,
  deviceRegion: null,
  devicePosition: null,
  deviceOs: null,
  deviceIp: null,
  status: null,
  createTime: []
})
const queryFormRef = ref() // 搜索的表单
const exportLoading = ref(false) // 导出的加载中

/** 查询列表 */
const getList = async () => {
  loading.value = true
  try {
    const data = await BikeEntityApi.getBikeEntityPage(queryParams)
    list.value = data.list
    total.value = data.total
  } finally {
    loading.value = false
  }
}

/** 搜索按钮操作 */
const handleQuery = () => {
  queryParams.pageNo = 1
  getList()
}

/** 重置按钮操作 */
const resetQuery = () => {
  queryFormRef.value.resetFields()
  handleQuery()
}

/** 添加/修改操作 */
const formRef = ref()
const openForm = (type: string, id?: number) => {
  formRef.value.open(type, id)
}

/** 删除按钮操作 */
const handleDelete = async (id: number) => {
  try {
    // 删除的二次确认
    await message.delConfirm()
    // 发起删除
    await BikeEntityApi.deleteBikeEntity(id)
    message.success(t('common.delSuccess'))
    // 刷新列表
    await getList()
  } catch {}
}

/** 导出按钮操作 */
const handleExport = async () => { try {
    // 导出的二次确认
    await message.exportConfirm()
    // 发起导出
    exportLoading.value = true
    const data = await BikeEntityApi.exportBikeEntity(queryParams)
    download.excel(data, '电车实体.xls')
  } catch {
  } finally {
    exportLoading.value = false
  }
}

const router = useRouter()
/** 处理 SSH 连接 */
const handleSSH = (id) => {
  router.push('/motocycle-detect/bike-entity/' + id)
}
/** 初始化 **/
onMounted(() => {
  getList()
})
</script>
