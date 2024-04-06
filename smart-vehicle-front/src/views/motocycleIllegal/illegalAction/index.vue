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
      <el-form-item label="模型文件名" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入模型文件名"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="模型类型" prop="modelType">
        <el-select
          v-model="queryParams.modelType"
          placeholder="请选择模型类型"
          clearable
          class="!w-240px"
        >
          <el-option
            v-for="dict in getIntDictOptions(DICT_TYPE.MOTOCYCLE_DETECT_MODEL_TYPE)"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="模型参数地址" prop="hyprUrl">
        <el-input
          v-model="queryParams.hyprUrl"
          placeholder="请输入模型参数地址"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="数据集类型" prop="datasetType">
        <el-select
          v-model="queryParams.datasetType"
          placeholder="请选择模型类型"
          clearable
          class="!w-240px"
        >
          <el-option
            v-for="dict in getIntDictOptions(DICT_TYPE.MOTOCYCLE_DATASET_TYPE)"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="数据集地址" prop="datasetUrl">
        <el-input
          v-model="queryParams.datasetUrl"
          placeholder="请输入数据集地址"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="mAP@0.5" prop="point5Map">
        <el-input
          v-model="queryParams.point5Map"
          placeholder="请输入数据集地址"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="迭代轮次" prop="epoch">
        <el-input
          v-model="queryParams.epoch"
          placeholder="请输入数据集地址"
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
        <el-button type="primary" plain @click="openForm('create')">
          <Icon icon="ep:plus" class="mr-5px" /> 新增
        </el-button>
        <el-button
          type="success"
          plain
          @click="handleExport"
          :loading="exportLoading"
          v-hasPermi="['motocycleIllegal:illegal-action:export']"
        >
          <Icon icon="ep:download" class="mr-5px" /> 导出
        </el-button>
      </el-form-item>
    </el-form>
  </ContentWrap>

  <!-- 列表 -->
  <ContentWrap>
    <el-table v-loading="loading" :data="list">
      <el-table-column label="模型文件名" align="center" prop="name" />
      <el-table-column label="模型类型" align="center" prop="modelType">
        <template #default="scope">
          <dict-tag :type="DICT_TYPE.MOTOCYCLE_DETECT_MODEL_TYPE" :value="scope.row.modelType" />
        </template>
      </el-table-column>
      <el-table-column label="模型文件地址" align="center" prop="modelUrl" />
      <el-table-column label="模型参数地址" align="center" prop="hyprUrl" />
      <el-table-column sortable label="mAP@0.5" align="center" prop="point5Map" />
      <el-table-column label="数据集类型" align="center" prop="datasetType">
        <template #default="scope">
          <dict-tag :type="DICT_TYPE.MOTOCYCLE_DATASET_TYPE" :value="scope.row.datasetType" />
        </template>
      </el-table-column>
      <el-table-column label="数据集地址" align="center" prop="datasetUrl" />
      <el-table-column label="迭代轮次" align="center" prop="epoch" />
      <el-table-column
        label="创建时间"
        align="center"
        prop="createTime"
        :formatter="dateFormatter"
      />
      <el-table-column label="操作" align="center">
        <template #default="scope">
          <el-button
            link
            type="primary"
            @click="openForm('update', scope.row.id)"
            v-hasPermi="['motocycleIllegal:illegal-action:update']"
          >
            编辑
          </el-button>
          <el-button
            link
            type="primary"
            @click="handleDetail(scope.row.id)"
            v-hasPermi="['motocycleIllegal:illegal-action:update']"
          >
            查看详情
          </el-button>
          <el-button
            link
            type="danger"
            @click="handleDelete(scope.row.id)"
            v-hasPermi="['motocycleIllegal:illegal-action:delete']"
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
  <IllegalActionForm ref="formRef" @success="getList" />
</template>

<script setup lang="ts" name="IllegalAction">
import { dateFormatter } from '@/utils/formatTime'
import download from '@/utils/download'
import * as IllegalActionApi from '@/api/motocycleIllegal/illegalAction'
import IllegalActionForm from './IllegalActionForm.vue'
import { getIntDictOptions, DICT_TYPE } from '@/utils/dict'
const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化

const loading = ref(true) // 列表的加载中
const total = ref(0) // 列表的总页数
const list = ref([]) // 列表的数据
const router = useRouter()
const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  name: null,
  modelType: null,
  modelUrl: null,
  hyprUrl: null,
  datasetType: null,
  datasetUrl: null,
  point5Map: null,
  epoch: null,
  createTime: []
})
const queryFormRef = ref() // 搜索的表单
const exportLoading = ref(false) // 导出的加载中

/** 查询列表 */
const getList = async () => {
  loading.value = true
  try {
    const data = await IllegalActionApi.getIllegalActionPage(queryParams)
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

const handleDetail = (id:number) => {
  router.push(`/motocycle-detect/illegal-action/${id}`)
}

/** 删除按钮操作 */
const handleDelete = async (id: number) => {
  try {
    // 删除的二次确认
    await message.delConfirm()
    // 发起删除
    await IllegalActionApi.deleteIllegalAction(id)
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
    const data = await IllegalActionApi.exportIllegalAction(queryParams)
    download.excel(data, '模型训练记录.xls')
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
