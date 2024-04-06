<script setup lang="ts">
import * as echarts from 'echarts'
const data = reactive({
  region: '',
  char1: null as any as echarts.EChartsType,
  char2: null as any as echarts.EChartsType,
})
const char1 = ref()
const char2 = ref()

onMounted(() => { 
  nextTick(() => {
    data.char1 = echarts.init(char1.value)
    data.char2 = echarts.init(char2.value)
  })

})

</script>
<template>
  <div>
    <div class="font-bold text-xl"> 当前地区： {{ data.region }} </div>
    <div class="w-[400px] h-[400px]">
      <div ref="char1"></div>
    </div>
    <div class="w-[400px] h-[400px]">
      <div ref="char2"></div>
    </div>
  </div>
</template>
<style scoped lang="scss"></style>
