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
      <el-form-item label="行政区划" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入行政区划"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="文件路径" prop="path">
        <el-input
          v-model="queryParams.path"
          placeholder="请输入文件路径"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="文件 URL" prop="url">
        <el-input
          v-model="queryParams.url"
          placeholder="请输入文件 URL"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="检测地点" prop="url">
        <el-input
          v-model="queryParams.location"
          placeholder="请输入检测地点"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="是否误判" prop="status">
        <el-select
          v-model="queryParams.status"
          placeholder="是否误判"
          clearable
          class="!w-240px"
        >
          <el-option
            v-for="dict in getIntDictOptions(DICT_TYPE.MOTOCYCLE_DETECT_MISTACK)"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="检测类型" prop="type">
        <el-select
          v-model="queryParams.type"
          placeholder="请选择检测类型"
          clearable
          class="!w-240px"
        >
          <el-option
            v-for="dict in getIntDictOptions(DICT_TYPE.MOTOCYCLE_DETECT_TYPE)"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="地区编码" prop="size">
        <el-input
          v-model="queryParams.size"
          placeholder="请输入地区编码"
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
          v-hasPermi="['motocycleDetect:detect-files:create']"
        >
          <Icon icon="ep:plus" class="mr-5px" /> 新增
        </el-button>
        <el-button
          type="success"
          plain
          @click="handleExport"
          :loading="exportLoading"
          v-hasPermi="['motocycleDetect:detect-files:export']"
        >
          <Icon icon="ep:download" class="mr-5px" /> 导出
        </el-button>
      </el-form-item>
    </el-form>
  </ContentWrap>

  <!-- 列表 -->
  <ContentWrap>
    <el-table v-loading="loading" :data="list">
      <el-table-column label="文件编号" align="center" prop="id" />
      <el-table-column label="行政区划" align="center" prop="name" />
      <el-table-column label="文件路径" align="center" prop="path" />
      <el-table-column label="文件 URL" align="center" prop="url" />
      <el-table-column label="检测地点" align="center" prop="location" />
      <el-table-column label="检测类型" align="center" prop="type">
        <template #default="scope">
          <dict-tag :type="DICT_TYPE.MOTOCYCLE_DETECT_TYPE" :value="scope.row.type" />
        </template>
      </el-table-column>
      <el-table-column label="误判" align="center" prop="type">
        <template #default="scope">
          <dict-tag :type="DICT_TYPE.MOTOCYCLE_DETECT_MISTACK" :value="scope.row.status" />
        </template>
      </el-table-column>
      <el-table-column label="地区编码" align="center" prop="size" />
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
            @click="goToReview(scope.row.id)"
          >
            复核
          </el-button>
          <el-button
            link
            type="danger"
            @click="handleDelete(scope.row.id)"
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

</template>

<script setup lang="ts" name="DetectFiles">
import { dateFormatter } from '@/utils/formatTime'
import download from '@/utils/download'
import * as DetectFilesApi from '@/api/motocycleDetect/detectFiles'
import { DICT_TYPE, getIntDictOptions } from '@/utils/dict'
const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化

const loading = ref(true) // 列表的加载中
const total = ref(0) // 列表的总页数
const list = ref([]) // 列表的数据
const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  configId: null,
  name: null,
  path: null,
  location: null,
  status: null,
  url: null,
  type: null,
  size: null,
  createTime: []
})
const queryFormRef = ref() // 搜索的表单
const exportLoading = ref(false) // 导出的加载中

/** 查询列表 */
const getList = async () => {
  loading.value = true
  try {
    const data = await DetectFilesApi.getDetectFilesPage(queryParams)
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
    await DetectFilesApi.deleteDetectFiles(id)
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
    const data = await DetectFilesApi.exportDetectFiles(queryParams)
    download.excel(data, '文件.xls')
  } catch {
  } finally {
    exportLoading.value = false
  }
}
/** 复核功能 */
const goToReview = (id) => {
  console.log(id , router)
  router.push("/motocycle-detect/detect-files-analysis/" + id)
}
const router = useRouter()
/** 初始化 **/
onMounted(() => {
  getList()
  console.log("获取到路由参数",useRoute().params.id)
})
</script>
