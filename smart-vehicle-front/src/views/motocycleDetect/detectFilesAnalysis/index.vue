<template>
  <!-- 列表 -->
  <ContentWrap>
    <div class="flex">
      <div class="border-gray-300">
        <img class="w-[40em] h-[30em]" :src="formData.url" />
      </div>
      <div class="w-[500px] mr-10">
        <el-form
          ref="formRef"
          :model="formData"
          :rules="formRules"
          label-width="100px"
          v-loading="formLoading"
        >
          <el-form-item label="行政区划" prop="name">
            <el-input v-model="formData.name" placeholder="请输入文件名" />
          </el-form-item>
          <el-form-item label="文件路径" prop="path">
            <el-input v-model="formData.path" placeholder="请输入文件路径" />
          </el-form-item>
          <el-form-item label="文件 URL" prop="url">
            <el-input disabled v-model="formData.url" placeholder="请输入文件 URL" />
          </el-form-item>
          <el-form-item label="误判" prop="status">
            <el-select v-model="formData.status" placeholder="是否误判" clearable class="!w-240px">
              <el-option
                v-for="dict in getIntDictOptions(DICT_TYPE.MOTOCYCLE_DETECT_MISTACK)"
                :key="dict.value"
                :label="dict.label"
                :value="dict.value"
              />
            </el-select>
          </el-form-item>
          <el-form-item label="检测类型" prop="type">
            <el-tag type="success">手动</el-tag>
          </el-form-item>
          <el-form-item label="检测地址" prop="location">
            <el-input v-model="formData.location" placeholder="检测地址" />
          </el-form-item>
          <el-form-item label="地区编号" prop="size">
            <el-input disabled v-model="formData.size" placeholder="请输入地区编号" />
          </el-form-item>
          <el-form-item>
            <el-button @click="submitForm" type="primary" :disabled="formLoading">确 定</el-button>
            <el-button
              @click="handleDelete(formData.id as any)"
              type="danger"
              :disabled="formLoading"
              >删 除</el-button
            >
          </el-form-item>
        </el-form>
      </div>
    </div>
  </ContentWrap>

</template>

<script setup lang="ts" name="DetectFilesAnalysis">
import * as DetectFilesApi from '@/api/motocycleDetect/detectFiles'

import { DICT_TYPE, getIntDictOptions } from '@/utils/dict'

const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formRef = ref()
const formData = ref({
  id: undefined,
  name: undefined,
  path: undefined,
  url: undefined,
  location: undefined,
  type: 1,
  size: undefined,
  status: undefined
})
const formRules = reactive({
  path: [{ required: true, message: '文件路径不能为空', trigger: 'blur' }],
  url: [{ required: true, message: '文件 URL 不能为空', trigger: 'blur' }],
  size: [{ required: true, message: '文件大小不能为空', trigger: 'blur' }]
})

const loading = ref(true) // 列表的加载中

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
    const data = formData.value as unknown as DetectFilesApi.DetectFilesVO
    await DetectFilesApi.updateDetectFiles(data)
    message.success(t('common.updateSuccess'))
    // 发送操作成功的事件
    emit('success')
  } finally {
    formLoading.value = false
  }
}

/** 查询列表 */
const getList = async () => {
  loading.value = true
  try {
    formData.value = await DetectFilesApi.getDetectFiles(
      Number.parseInt(useRoute().params.id as string) || 0
    )
    formData.value.type = 1
    console.log('数据是', formData.value)
  } finally {
    loading.value = false
  }
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

/** 初始化 **/
onMounted(() => {
  getList().then(() => {
    nextTick(() => {
      (formData.value.status as any) = formData.value.status as any - 1
      formData.value.status = formData.value.status as any + 1
    })
  })
})
</script>
