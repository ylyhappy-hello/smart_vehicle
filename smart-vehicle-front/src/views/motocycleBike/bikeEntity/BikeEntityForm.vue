<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="100px"
      v-loading="formLoading"
    >
      <el-form-item label="设备编号" prop="illeageActionId">
        <el-input v-model="formData.illeageActionId" placeholder="请输入设备编号" />
      </el-form-item>

      <el-form-item label="软件版本" prop="deviceOs">
        <el-input v-model="formData.deviceOs" placeholder="请输入软件版本" />
      </el-form-item>
      <el-form-item label="设备ip" prop="deviceIp">
        <el-input v-model="formData.deviceIp" placeholder="请输入设备ip" />
      </el-form-item>
      <el-form-item label="设备区域" prop="deviceRegion">
        <el-input v-model="formData.deviceRegion" placeholder="请输入设备区域" />
      </el-form-item>
      <el-form-item label="区域编码" prop="deviceRegionCode">
        <el-input v-model="formData.deviceRegionCode" placeholder="请输入区域编码" />
      </el-form-item>
      <el-form-item label="设备分组" prop="deviceGroup">
        <el-input v-model="formData.deviceGroup" placeholder="请输入设备分组" />
      </el-form-item>
      <el-form-item label="GPS坐标" prop="devicePosition">
        <el-input v-model="formData.devicePosition" placeholder="请输入GPS坐标" />
      </el-form-item>
    </el-form>
    <template #footer>
      <el-button @click="submitForm" type="primary" :disabled="formLoading">确 定</el-button>
      <el-button @click="dialogVisible = false">取 消</el-button>
    </template>
  </Dialog>
</template>
<script setup lang="ts">
import * as BikeEntityApi from '@/api/motocycleBike/bikeEntity'

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  illeageActionId: undefined,
  deviceRegionCode: null,
  deviceGroup: null,
  deviceRegion: null,
  devicePosition: null,
  deviceOs: null,
  deviceIp: null,
  status: null,
})
const formRules = reactive({
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
      formData.value = await BikeEntityApi.getBikeEntity(id)
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
    const data = formData.value as unknown as BikeEntityApi.BikeEntityVO
    if (formType.value === 'create') {
      await BikeEntityApi.createBikeEntity(data)
      message.success(t('common.createSuccess'))
    } else {
      await BikeEntityApi.updateBikeEntity(data)
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
    illeageActionId: undefined,
    status: undefined
  }
  formRef.value?.resetFields()
}
</script>
