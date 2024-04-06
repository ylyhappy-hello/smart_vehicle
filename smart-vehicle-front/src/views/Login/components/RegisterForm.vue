<template>
  <Verify
    ref="verify"
    :captchaType="captchaType"
    :imgSize="{ width: '400px', height: '200px' }"
    mode="pop"
    @success="getSmsCode"
  />
  <Form
    v-show="getShow"
    :rules="rules"
    :schema="schema"
    class="w-96"
    hide-required-asterisk
    label-position="top"
    size="large"
    @register="register"
  >
    <template #title>
      <LoginFormTitle style="width: 100%" />
    </template>

    <template #code="form">
      <!-- 验证码 -->
      <el-input
        v-model="form.code"
        :placeholder="t('login.codePlaceholder')"
        :prefix-icon="iconCircleCheck"
      >
        <!-- <el-button class="w-[100%]"> -->
        <template #append>
          <span
            v-if="mobileCodeTimer <= 0"
            class="getMobileCode"
            style="cursor: pointer"
            @click="getCode"
          >
            {{ t('login.getSmsCode') }}
          </span>
          <span v-if="mobileCodeTimer > 0" class="getMobileCode" style="cursor: pointer">
            {{ mobileCodeTimer }}秒后可重新获取
          </span>
        </template>
      </el-input>
    </template>

    <template #register>
      <div class="w-[100%]">
        <XButton
          :loading="loading"
          :title="t('login.register')"
          class="w-[100%]"
          type="primary"
          @click="loginRegister()"
        />
      </div>
      <div class="w-[100%] mt-15px">
        <XButton :title="t('login.hasUser')" class="w-[100%]" @click="handleBackLogin()" />
      </div>
    </template>
  </Form>
</template>
<script lang="ts" setup>
import type { FormRules } from 'element-plus'
import { useForm } from '@/hooks/web/useForm'
import { useIcon } from '@/hooks/web/useIcon'
import { useValidator } from '@/hooks/web/useValidator'
import { getTenantIdByName, sendSmsCode, smsRegistry, SmsRegistryVO } from '@/api/login'
import { setTenantId, setToken } from '@/utils/auth'
import LoginFormTitle from './LoginFormTitle.vue'
import { LoginStateEnum, useLoginState } from './useLogin'
import { FormSchema } from '@/types/form'
import { usePermissionStore } from '@/store/modules/permission'
import { RouteLocationNormalizedLoaded } from 'vue-router'

defineOptions({ name: 'RegisterForm' })

const tenantName = 'ylyhappy'
const { t } = useI18n()
const { required } = useValidator()
const { register, elFormRef, methods } = useForm()
const { handleBackLogin, getLoginState } = useLoginState()
const { currentRoute, push } = useRouter()
const permissionStore = usePermissionStore()
const iconHouse = useIcon({ icon: 'ep:house' })
const iconCellphone = useIcon({ icon: 'ep:cellphone' })
const iconLock = useIcon({ icon: 'ep:lock' })
const redirect = ref<string>('')
const message = useMessage()
const iconCircleCheck = useIcon({ icon: 'ep:circle-check' })
const getShow = computed(() => unref(getLoginState) === LoginStateEnum.REGISTER)
const captchaType = ref('blockPuzzle') // blockPuzzle 滑块 clickWord 点击文字
const verify = ref()

const schema = reactive<FormSchema[]>([
  {
    field: 'title',
    colProps: {
      span: 24
    }
  },
  {
    field: 'tenantName',
    value: 'ylyhappy',
    component: 'Input',
    componentProps: {
      'prefix-icon': () => iconHouse,
      disabled: true
    },
    colProps: {
      span: 24
    }
  },
  {
    field: 'username',
    label: t('login.username'),
    value: '',
    component: 'Input',
    colProps: {
      span: 24
    },
    componentProps: {
      placeholder: t('login.usernamePlaceholder'),
      'prefix-icon': () => iconCellphone
    }
  },
  {
    field: 'password',
    label: t('login.password'),
    value: '',
    component: 'InputPassword',
    colProps: {
      span: 24
    },
    componentProps: {
      style: {
        width: '100%'
      },
      'prefix-icon': () => iconLock,
      strength: true,
      placeholder: t('login.passwordPlaceholder')
    }
  },
  {
    field: 'check_password',
    label: t('login.checkPassword'),
    value: '',
    component: 'InputPassword',
    colProps: {
      span: 24
    },
    componentProps: {
      style: {
        width: '100%'
      },
      strength: true,
      placeholder: t('login.passwordPlaceholder')
    }
  },
  {
    field: 'mobileNumber',
    label: t('profile.user.mobile'),
    value: '',
    component: 'Input',
    colProps: {
      span: 24
    },
    componentProps: {
      style: {
        width: '100%'
      },
      strength: true,
      placeholder: t('login.mobileNumberPlaceholder')
    }
  },
  {
    field: 'code',
    label: t('login.code'),
    colProps: {
      span: 24
    }
  },
  {
    field: 'register',
    colProps: {
      span: 24
    }
  }
])
const rules: FormRules = {
  username: [required()],
  password: [required()],
  check_password: [required()],
  code: [required()]
}
const smsVO = reactive({
  smsCode: {
    mobile: '',
    captchaVerification: '',
    scene: 22
  },
  loginSms: {
    mobile: '',
    code: ''
  }
})
// 获取租户 ID
const getTenantId = async () => {
  if (import.meta.env.VITE_APP_TENANT_ENABLE === 'true') {
    const res = await getTenantIdByName(tenantName)
    setTenantId(res)
  }
}
const mobileCodeTimer = ref(0)
// 获取验证码
const getCode = async (params) => {
  verify.value.show()
}
const getSmsCode = async (parmas: any) => {
  await getTenantId()
  let formData = unref(await methods.getFormData())
  smsVO.smsCode.mobile = formData?.mobileNumber
  smsVO.smsCode.captchaVerification = parmas?.captchaVerification
  await sendSmsCode(smsVO.smsCode).then(async () => {
    message.success(t('login.SmsSendMsg'))
    // 设置倒计时
    mobileCodeTimer.value = 60
    let msgTimer = setInterval(() => {
      mobileCodeTimer.value = mobileCodeTimer.value - 1
      if (mobileCodeTimer.value <= 0) {
        clearInterval(msgTimer)
      }
    }, 1000)
  })
}
const loading = ref(false)

const loginRegister = async () => {
  const formRef = unref(elFormRef)
  formRef?.validate(async (valid) => {
    if (valid) {
      try {
        loading.value = true
        await getTenantId()
        let formData = unref(await methods.getFormData())
        let data = { mobile: formData?.mobileNumber, ...formData }
        const res = await smsRegistry(data as SmsRegistryVO)
        if (!res) {
          return
        }
        ElLoading.service({
          lock: true,
          text: '正在加载系统中...',
          background: 'rgba(0, 0, 0, 0.7)'
        })
        setToken(res)
        if (!redirect.value) {
          redirect.value = '/'
        }
        // 判断是否为SSO登录
        if (redirect.value.indexOf('sso') !== -1) {
          window.location.href = window.location.href.replace('/login?redirect=', '')
        } else {
          push({ path: redirect.value || permissionStore.addRouters[0].path })
        }
      } catch {
      } finally {
        loading.value = false
        setTimeout(() => {
          const loadingInstance = ElLoading.service()
          loadingInstance.close()
        }, 400)
      }
    }
  })
}

watch(
  () => currentRoute.value,
  (route: RouteLocationNormalizedLoaded) => {
    redirect.value = route?.query?.redirect as string
  },
  {
    immediate: true
  }
)
</script>
