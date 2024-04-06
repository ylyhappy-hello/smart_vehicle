<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="100px"
      v-loading="formLoading"
    >
      <el-form-item label="警告编号" prop="waringId">
        <el-input v-model="formData.waringId" placeholder="请输入警告编号" />
      </el-form-item>
      <el-form-item label="处罚力度分类" prop="type">
        <el-select v-model="formData.type" placeholder="请选择处罚力度分类">
          <el-option label="请选择字典生成" value="" />
        </el-select>
      </el-form-item>
      <el-form-item label="处罚金额" prop="penalty">
        <el-input v-model="formData.penalty" placeholder="请输入处罚金额" />
      </el-form-item>
      <el-form-item label="拘留天数" prop="daysInDetention">
        <el-input v-model="formData.daysInDetention" placeholder="请输入拘留天数" />
      </el-form-item>
    </el-form>
    <template #footer>
      <el-button @click="submitForm" type="primary" :disabled="formLoading">确 定</el-button>
      <el-button @click="dialogVisible = false">取 消</el-button>
    </template>
  </Dialog>
</template>
<script setup lang="ts">
import * as IllegalWaringTypeApi from '@/api/motocycleIllegal/illegalWaringType'

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  waringId: undefined,
  type: undefined,
  penalty: undefined,
  daysInDetention: undefined
})
const formRules = reactive({
  type: [{ required: true, message: '处罚力度分类不能为空', trigger: 'change' }]
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
      formData.value = await IllegalWaringTypeApi.getIllegalWaringType(id)
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
    const data = formData.value as unknown as IllegalWaringTypeApi.IllegalWaringTypeVO
    if (formType.value === 'create') {
      await IllegalWaringTypeApi.createIllegalWaringType(data)
      message.success(t('common.createSuccess'))
    } else {
      await IllegalWaringTypeApi.updateIllegalWaringType(data)
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
    waringId: undefined,
    type: undefined,
    penalty: undefined,
    daysInDetention: undefined
  }
  formRef.value?.resetFields()
}
</script>
