<script setup lang="ts">
import { Terminal } from '@xterm/xterm'

const term = new Terminal()
const terminalParentDiv = ref<InstanceType<typeof HTMLDivElement>>()

/** 处理 SSH 连接 */
/** 初始化 **/
onMounted(() => {
  term.open(terminalParentDiv.value!.getElementsByClassName('terminal')[0] as HTMLHtmlElement)
  term.write(`Hello from \x1B[1;3;31mbash\x1B[0m root$${1} `)
})
const router = useRouter()
const back = () => {
  router.back()
}
</script>
<template>
  <ContentDetailWrap @back="back">
    <div class="flex">
      <div class="w-800px h-600px mr-6" ref="terminalParentDiv">
        <div class="terminal"></div>
      </div>
      <div class="flex h-400px flex-col justify-center">
        <div>
          <div>内存 200M / 500M</div>
          <div>CPU 150% / 200%</div>
          <div>网络 10kb/s</div>
          <div>CPU armv7m</div>
          <div>功耗 6.7w</div>
          <div>CPU v2</div>
          <div>数据盘 5G / 10G</div>
          <div>系统盘 1G / 5G</div>
          <div>已检测出 200 人次 </div>
          <div>运行状态 开机 </div>
        </div>
      </div>
    </div>
  </ContentDetailWrap>
</template>
<style scoped lang="scss"></style>
