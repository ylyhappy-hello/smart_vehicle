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
      <el-form-item label="挂失人id" prop="reportTheLossOfUserid">
        <el-input
          v-model="queryParams.reportTheLossOfUserid"
          placeholder="请输入挂失人id"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="挂失审核人id" prop="reportTheLossOfAuditid">
        <el-input
          v-model="queryParams.reportTheLossOfAuditid"
          placeholder="请输入挂失审核人id"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="挂失状态" prop="status">
        <el-select
          v-model="queryParams.status"
          placeholder="是否找回"
          clearable
          class="!w-240px"
        >
          <el-option
            v-for="dict in getIntDictOptions(DICT_TYPE.MOTOCYCLE_FIND_STATUS)"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
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
          v-hasPermi="['motocycleBike:bike-report-the-loss-of:create']"
        >
          <Icon icon="ep:plus" class="mr-5px" /> 新增
        </el-button>
        <el-button
          type="success"
          plain
          @click="handleExport"
          :loading="exportLoading"
          v-hasPermi="['motocycleBike:bike-report-the-loss-of:export']"
        >
          <Icon icon="ep:download" class="mr-5px" /> 导出
        </el-button>
      </el-form-item>
    </el-form>
  </ContentWrap>

  <!-- 列表 -->
  <ContentWrap>
    <el-table v-loading="loading" :data="list">
      <el-table-column width="100" label="编号" align="center" prop="id" />
      <el-table-column width="100" label="挂失人id" align="center" prop="reportTheLossOfUserid" />
      <el-table-column label="挂失电车照片" align="center" prop="bikeUrl" >
        <template #default="scope">
        <img class="m-auto w-[12em] h-8em]" :src="scope.row.bikeUrl" />
        </template>
      </el-table-column>
      <div class="border-gray-300">
      </div>
      <el-table-column label="挂失电车车牌号" align="center" prop="bikeCard" />
      <el-table-column label="挂失状态" align="center" prop="status">
        <template #default="scope">
          <dict-tag :type="DICT_TYPE.MOTOCYCLE_FIND_STATUS" :value="scope.row.status" />
        </template>
      </el-table-column>
      <el-table-column
        label="挂失时间"
        align="center"
        prop="createTime"
        :formatter="dateFormatter"
      />
      <el-table-column label="操作" align="center">
        <template #default="scope">
          <el-button
            link
            type="primary"
            @click="handleFinded(scope.row.id)"
            v-hasPermi="['motocycleBike:bike-report-the-loss-of:update']"
          >
            找回了
          </el-button>
          <el-button
            link
            type="danger"
            @click="handleDelete(scope.row.id)"
            v-hasPermi="['motocycleBike:bike-report-the-loss-of:delete']"
          >
            删除
          </el-button>
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
  <BikeReportTheLossOfForm ref="formRef" @success="getList" />
</template>

<script setup lang="ts" name="BikeReportTheLossOf">
import { dateFormatter } from '@/utils/formatTime'
import { getIntDictOptions, DICT_TYPE} from '@/utils/dict'
import download from '@/utils/download'
import * as BikeReportTheLossOfApi from '@/api/motocycleBike/bikeReportTheLossOf'
import BikeReportTheLossOfForm from './BikeReportTheLossOfForm.vue'
const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化

const loading = ref(true) // 列表的加载中
const total = ref(0) // 列表的总页数
const list = ref([]) // 列表的数据
const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  reportTheLossOfUserid: null,
  reportTheLossOfAuditid: null,
  bikeUrl: null,
  bikeCard: null,
  status: null,
  createTime: []
})
const queryFormRef = ref() // 搜索的表单
const exportLoading = ref(false) // 导出的加载中

/** 查询列表 */
const getList = async () => {
  loading.value = true
  try {
    const data = await BikeReportTheLossOfApi.getBikeReportTheLossOfPage(queryParams)
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

/** 找回了按钮擦操作*/
const handleFinded = async (id: number) => { 
  try {
    let data
    if (id) {
      data = await BikeReportTheLossOfApi.getBikeReportTheLossOf(id)
    }
    data.status = 2
    await BikeReportTheLossOfApi.updateBikeReportTheLossOf(data)
    message.success(t('common.success'))
    // 刷新列表
    await getList()
  } catch {}
}

/** 删除按钮操作 */
const handleDelete = async (id: number) => {
  try {
    // 删除的二次确认
    await message.delConfirm()
    // 发起删除
    await BikeReportTheLossOfApi.deleteBikeReportTheLossOf(id)
    message.success(t('common.delSuccess'))
    // 刷新列表
    await getList()
  } catch {}
}

/** 导出按钮操作 */
const handleExport = async () => {
  try {
    // 导出的二次确认
    await message.exportConfirm()
    // 发起导出
    exportLoading.value = true
    const data = await BikeReportTheLossOfApi.exportBikeReportTheLossOf(queryParams)
    download.excel(data, '电车挂失信息.xls')
  } catch {
  } finally {
    exportLoading.value = false
  }
}

/** 初始化 **/
onMounted(() => {
  getList()
})
</script>
