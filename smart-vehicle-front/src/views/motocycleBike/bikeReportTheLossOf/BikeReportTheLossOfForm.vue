<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="100px"
      v-loading="formLoading"
    >
      <el-form-item label="电动车照片" prop="datasetUrl">
        <el-row class="w-[100%]">
          <el-col :span="18">
            <el-input v-model="formData.bikeUrl" placeholder="请上传电动车照片" />
          </el-col>
          <el-col :span="5" class="ml-4">
            <el-button type="primary" plain @click="openForm('bikeUrl')">
              <Icon icon="ep:upload" class="mr-5px" /> 上传文件
            </el-button>
          </el-col>
        </el-row>
      </el-form-item>
      <el-form-item label="电动车车牌号" prop="bikeCard">
        <el-input v-model="formData.bikeCard" placeholder="请输入电动车车牌号" />
      </el-form-item>
      <el-form-item label="挂失状态" prop="status">
        <el-select v-model="formData.status" placeholder="是否找回" clearable class="!w-240px">
          <el-option
            v-for="dict in getIntDictOptions(DICT_TYPE.MOTOCYCLE_FIND_STATUS)"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
    </el-form>
    <template #footer>
      <el-button @click="submitForm" type="primary" :disabled="formLoading">确 定</el-button>
      <el-button @click="dialogVisible = false">取 消</el-button>
    </template>
  <FileForm ref="uploadFormRef" @success="uploadSuccess" />
  </Dialog>
</template>
<script setup lang="ts">
import * as BikeReportTheLossOfApi from '@/api/motocycleBike/bikeReportTheLossOf'
import { useUserStore } from '@/store/modules/user';
import { getIntDictOptions, DICT_TYPE } from '@/utils/dict'
import FileForm from './FileForm.vue'


const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const userId = useUserStore().user.id
const formData = ref({
  id: undefined,
  reportTheLossOfUserid: undefined,
  reportTheLossOfAuditid: undefined,
  bikeUrl: undefined,
  bikeCard: undefined,
  status: undefined
})
const formRules = reactive({
  reportTheLossOfUserid: [{ required: true, message: '挂失人id不能为空', trigger: 'blur' }],
  reportTheLossOfAuditid: [{ required: true, message: '挂失审核人id不能为空', trigger: 'blur' }],
  bikeId: [{ required: true, message: '挂失电车id不能为空', trigger: 'blur' }]
})
const formRef = ref() // 表单 Ref
const uploadFormRef = ref()

const uploadSuccess = (r) => {
  formData.value[r.type] = r.data;
}
const openForm = (type: string) => {
  uploadFormRef.value.open(type)
}

/** 打开弹窗 */
const open = async (type: string, id?: number) => {
  dialogVisible.value = true
  dialogTitle.value = t('action.' + type)
  formType.value = type
  resetForm()
  // 修改时，设置数据
  if (id) {
    formLoading.value = true
    try {
      formData.value = await BikeReportTheLossOfApi.getBikeReportTheLossOf(id)
    } finally {
      formLoading.value = false
    }
  }
}
defineExpose({ open }) // 提供 open 方法，用于打开弹窗

/** 提交表单 */
const emit = defineEmits(['success']) // 定义 success 事件，用于操作成功后的回调
const submitForm = async () => {
  // 校验表单
  if (!formRef) return
  const valid = await formRef.value.validate()
  if (!valid) return
  // 提交请求
  formLoading.value = true
  try {
    const data = formData.value as unknown as BikeReportTheLossOfApi.BikeReportTheLossOfVO
    if (formType.value === 'create') {
      data.reportTheLossOfUserid = userId;
      await BikeReportTheLossOfApi.createBikeReportTheLossOf(data)
      message.success(t('common.createSuccess'))
    } else {
      data.reportTheLossOfAuditid = userId;
      await BikeReportTheLossOfApi.updateBikeReportTheLossOf(data)
      message.success(t('common.updateSuccess'))
    }
    dialogVisible.value = false
    // 发送操作成功的事件
    emit('success')
  } finally {
    formLoading.value = false
  }
}

/** 重置表单 */
const resetForm = () => {
  formData.value = {
    id: undefined,
    reportTheLossOfUserid: undefined,
    reportTheLossOfAuditid: undefined,
    bikeUrl: undefined,
    bikeCard: undefined,
    status: undefined
  }
  formRef.value?.resetFields()
}
</script>
