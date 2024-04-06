<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="100px"
      v-loading="formLoading"
    >
      <el-form-item label="模型文件名" prop="name">
        <el-input v-model="formData.name" placeholder="请输入模型文件名" />
      </el-form-item>
      <el-form-item label="模型类别">
        <el-select v-model="formData.modelType" placeholder="请选择模型类别">
          <el-option
            v-for="dict in getIntDictOptions(DICT_TYPE.MOTOCYCLE_DETECT_MODEL_TYPE)"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="模型路径" prop="modelUrl">
        <el-row class="w-[100%]">
          <el-col :span="18">
            <el-input v-model="formData.modelUrl" placeholder="请输入模型路径" />
          </el-col>
          <el-col :span="5" class="ml-4">
            <el-button type="primary" plain @click="openForm('modelUrl')">
              <Icon icon="ep:upload" class="mr-5px" /> 上传文件
            </el-button>
          </el-col>
        </el-row>
      </el-form-item>
      <el-form-item label="模型参数地址" prop="hyprUrl">
        <el-row class="w-[100%]">
          <el-col :span="18">
            <el-input v-model="formData.hyprUrl" placeholder="请输入模型参数地址" />
          </el-col>
          <el-col :span="5" class="ml-4">
            <el-button type="primary" plain @click="openForm('hyprUrl')">
              <Icon icon="ep:upload" class="mr-5px" /> 上传文件
            </el-button>
          </el-col>
        </el-row>
      </el-form-item>
      <el-form-item label="数据集类型">
        <el-select
          v-model="formData.datasetType"
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
        <el-row class="w-[100%]">
          <el-col :span="18">
            <el-input v-model="formData.datasetUrl" placeholder="请输入数据集地址" />
          </el-col>
          <el-col :span="5" class="ml-4">
            <el-button type="primary" plain @click="openForm('datasetUrl')">
              <Icon icon="ep:upload" class="mr-5px" /> 上传文件
            </el-button>
          </el-col>
        </el-row>
      </el-form-item>
      <el-form-item label="mAP@0.5" prop="point5Map">
        <el-input v-model="formData.point5Map" placeholder="请输入mAP@0.5" />
      </el-form-item>
      <el-form-item label="迭代次数" prop="epoch">
        <el-input v-model="formData.epoch" placeholder="请输入迭代次数" />
      </el-form-item>
    </el-form>
    <template #footer>
      <el-button @click="submitForm" type="primary" :disabled="formLoading">确 定</el-button>
      <el-button @click="dialogVisible = false">取 消</el-button>
    </template>
  </Dialog>
  <FileForm ref="uploadFormRef" @success="uploadSuccess" />
</template>
<script setup lang="ts">
import * as IllegalActionApi from '@/api/motocycleIllegal/illegalAction'
import { getIntDictOptions, DICT_TYPE } from '@/utils/dict'
import FileForm from './FileForm.vue'

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const uploadFormRef = ref()
const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  name: undefined,
  modelType: undefined,
  modelUrl: undefined,
  hyprUrl: undefined,
  point5Map: undefined,
  datasetType: undefined,
  datasetUrl: undefined,
  epoch: undefined
})
const formRules = reactive({
  position: [{ required: true, message: '违规发生地点不能为空', trigger: 'blur' }]
})
const formRef = ref() // 表单 Ref

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
      formData.value = await IllegalActionApi.getIllegalAction(id)
      formData.value['datasetType'] = Number.parseInt(formData.value['datasetType'] as any) as any
      formData.value['modelType'] = Number.parseInt(formData.value['modelType'] as any) as any
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
    const data = formData.value as unknown as IllegalActionApi.IllegalActionVO
    if (formType.value === 'create') {
      await IllegalActionApi.createIllegalAction(data)
      message.success(t('common.createSuccess'))
    } else {
      await IllegalActionApi.updateIllegalAction(data)
      message.success(t('common.updateSuccess'))
    }
    dialogVisible.value = false
    // 发送操作成功的事件
    emit('success')
  } finally {
    formLoading.value = false
  }
}

const openForm = (type:string) => {
  uploadFormRef.value.open(type)
}
const uploadSuccess = (r) => {
  formData.value[r.type] = r.data;
}

/** 重置表单 */
const resetForm = () => {
  formData.value = {
    id: undefined,
    name: undefined,
    modelType: undefined,
    modelUrl: undefined,
    hyprUrl: undefined,
    point5Map: undefined,
    datasetType: undefined,
    datasetUrl: undefined,
    epoch: undefined
  }
  formRef.value?.resetFields()
}
</script>

