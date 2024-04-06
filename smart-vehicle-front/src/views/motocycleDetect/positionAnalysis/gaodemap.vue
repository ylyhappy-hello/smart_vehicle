<template>
  <div class="w-[400px] h-[568px]">
    <el-amap @init="init" />
  </div>
</template>

<script lang="ts" setup>
import { ElAmap } from '@vuemap/vue-amap'
const props = defineProps<{cityCode: number}>()
let map;
const init = (e) => {
  map = e;
  map.setCity(110100)
}
watch(() => props.cityCode, () => {
  map.setCity(props.cityCode || 110100)
})
</script>

<style></style>
