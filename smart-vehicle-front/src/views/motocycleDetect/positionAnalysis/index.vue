<script setup lang="ts">
import * as echarts from 'echarts'
import * as DetectFilesApi from '@/api/motocycleDetect/detectFiles'
import { mapOption } from './mapOption'
import { getChinaJson, getProvinceJSON, getCityJSON } from '@/api/maps/index'
import { cityProvincesMap } from './cityProvincesMap'
import GaoDeMap from './gaodemap.vue'

const emit = defineEmits()

const data = reactive({
  chart: null as any as echarts.ECharts, // 实例化 echarts
  pieChart: null as any as echarts.ECharts, // 实例化 echarts
  provincesMap: cityProvincesMap.provincesMap, // 省拼音，用于查找对应 json
  provincesCode: cityProvincesMap.provincesCode, // 市行政区划，用于查找对应 json
  areaMap: cityProvincesMap.areaMap, // 省行政区划，用于数据的查找，按行政区划查数据
  special: ['北京市', '天津市', '上海市', '重庆市', '香港', '澳门'], //直辖市和特别行政区 - 只有二级地图，没有三级地图
  mapData: [] as any, // 当前地图上的地区
  option: { ...mapOption.basicOption }, // map 的相关配置
  deepTree: [] as Array<any>, // 点击地图时 push，点返回时 pop
  areaName: '中国', // 当前地名
  areaCode: '000000', // 当前行政区划
  areaLevel: 'country', // 当前级别
  detectList: [] as Array<{ id: number; size: number; name: string }>
})
import { pieOption } from './mapOption'
const chartOptions = reactive({
  pieChartOption: { ...pieOption } as any as echarts.EChartsOption
})

const pieChart = ref()
const tangshanMapChart = ref()
function renderMap(_map, _data) {
  data.option.geo = { map: _map }
  data.option.series = [{ name: _map, mapType: _map, ...mapOption.seriesOption, data: _data }]
  console.log('---410-239', data.option)
  data.chart.setOption(data.option)
}
function renderPieChar() {
  ;(chartOptions.pieChartOption as any).series[0].data = (curMap.value || []).map((e) => {
    return { name: unref(e).name, value: unref(e).id }
  })
  data.pieChart.setOption(chartOptions.pieChartOption as any)
}
// 返回
function back() {
  if (data.deepTree.length > 1) {
    data.deepTree.pop()
    data.areaName = data.deepTree[data.deepTree.length - 1].params.areaName
    data.mapData = data.deepTree[data.deepTree.length - 1].mapData
    emit('back-change', data.deepTree[data.deepTree.length - 1].params)
    renderMap(data.areaName, data.mapData)
  }
}
//绘制全国地图
function requestGetChinaJson() {
  getChinaJson('').then((res: any) => {
    //注册地图
    echarts.registerMap('china', res, {})
    let arr: any[] = []
    for (let i = 0; i < res.features.length; i++) {
      let obj = {
        name: res.features[i].properties.name,
        areaName: res.features[i].properties.name,
        areaCode: res.features[i].id,
        areaLevel: 'province',
        value: Math.round(Math.random())
      }
      arr.push(obj)
    }
    data.mapData = arr
    data.deepTree.push({
      mapData: arr,
      params: { name: 'china', areaName: 'china', areaLevel: 'country', areaCode: '000000' }
    })
    //绘制地图
    renderMap('china', arr)
  })
}
// 加载省级地图
function requestGetProvinceJSON(params) {
  getProvinceJSON(params.areaCode).then((res: any) => {
    echarts.registerMap(params.areaName, res)
    let arr = [] as any
    for (let i = 0; i < res.features.length; i++) {
      let obj = {
        name: res.features[i].properties.name,
        areaName: res.features[i].properties.name,
        areaCode: res.features[i].id,
        areaLevel: 'city',
        value: Math.round(Math.random())
      }
      arr.push(obj)
    }
    data.mapData = arr
    data.deepTree.push({
      mapData: arr,
      params: params
    })
    renderMap(params.areaName, arr)
  })
}
// 加载市级地图
function requestGetCityJSON(params) {
  data.areaLevel = params.areaLevel
  getCityJSON(params.areaCode).then((res: any) => {
    echarts.registerMap(params.areaName, res)
    let arr = [] as any
    for (let i = 0; i < res.features.length; i++) {
      let obj = {
        name: res.features[i].properties.name,
        areaName: res.features[i].properties.areaName,
        areaCode: res.features[i].id,
        areaLevel: 'districts',
        value: Math.round(Math.random())
      }
      arr.push(obj)
    }
    data.mapData = arr
    data.deepTree.push({ mapData: arr, params: params })
    renderMap(params.areaName, arr)
  })
}

// 初次加载绘制地图
function initEcharts() {
  //地图容器
  data.chart = echarts.init(tangshanMapChart.value)
  data.pieChart = echarts.init(pieChart.value)
  if (data.areaCode === '000000') {
    requestGetChinaJson()
  } else {
    requestGetProvinceJSON({ areaName: data.areaName, areaCode: data.areaCode })
  }
}
function echartsMapClick(params) {
  data.areaName = params.areaName
  if (params.name in data.provincesMap) {
    data.areaCode = params.data.areaCode
    data.areaLevel = params.data.areaLevel
    //如果点击的是34个省、市、自治区，绘制选中地区的二级地图
    requestGetProvinceJSON(params.data)
  } else if (params.seriesName in data.provincesMap) {
    //如果是【直辖市/特别行政区】只有二级下钻
    if (data.special.indexOf(params.seriesName) >= 0) {
      return
    } else {
      data.areaCode = data.areaMap[params.name]
      data.areaLevel = params.data.areaLevel
      //显示县级地图
      requestGetCityJSON(params.data)
    }
  } else {
    return
  }
  emit('map-change', params.data)
}

const onMountedDo = () => {
  initEcharts()
  //绘制地图
  data.chart.on('click', echartsMapClick)
  console.log(tangshanMapChart.value)
}
onMounted(() => {
  nextTick(onMountedDo)
  DetectFilesApi.getAllDetectFiles().then((res) => {
    data.detectList = res
    buildRank()
    console.log('触发 onmouted')
  })
})
watch(
  () => data.deepTree.length,
  () => {
    if (data.detectList.length > 0) {
      buildRank()
    }
  }
)

const curMap = ref<Array<any>>([])
watch(
  () => curMap.value,
  () => {
    renderPieChar()
  }
)
const buildRank = () => {
  const mMap = new Map()
  data.mapData.forEach((e) => {
    mMap.set(unref(e).areaCode, 1)
  })
  curMap.value = unref(data.detectList)
    .sort((a, b) => b.id - a.id)
    .filter((e) => {
      return mMap.get('' + e.size)
    })
}
</script>
<template>
  <content-wrap>
    <div class="flex">
      <div>
        <el-card class="h-50 mb-4">
          <div class="w-80 h-24">
            <div class="font-bold my-2">{{ data.areaName }} 未佩戴头盔人数 TOP 5</div>
            <div class="w-auto leading-6" v-for="v in curMap.filter((_, idx) => idx < 5)">
              <div :key="v.name"
                >{{ v.name }} <span>未佩戴头盔人数 {{ v.id }}</span></div
              >
            </div>
          </div>
        </el-card>
        <el-card class="w-[400px] h-[400px] flex justify-center">
          <div class="w-[400px] h-[400px]" ref="pieChart"></div>
        </el-card>
      </div>
      <el-card class="ml-4 w-[650px] h-[617px] overflow-hidden relative">
        <el-button class="absolute top-0 right-0 z-10" type="primary" @click="back" v-if="data.deepTree.length > 1"
          >返回</el-button
        >
        <div class="w-[800px] h-[800px] relative bottom-20 right-25" ref="tangshanMapChart"/>
      </el-card>
      <el-card class="">
        <gao-de-map :city-code="data.areaCode" class="h-[400px]"/>
      </el-card>
    </div>
  </content-wrap>
</template>
<style scoped lang="scss">
</style>
