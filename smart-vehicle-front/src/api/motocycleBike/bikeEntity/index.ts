import request from '@/config/axios'

export interface BikeEntityVO {
  id: number
  illeageActionId: number
  status: string
}

// 查询电车实体列表
export const getBikeEntityPage = async (params) => {
  return await request.get({ url: `/motocycleBike/bike-entity/page`, params })
}

// 查询电车实体详情
export const getBikeEntity = async (id: number) => {
  return await request.get({ url: `/motocycleBike/bike-entity/get?id=` + id })
}

// 新增电车实体
export const createBikeEntity = async (data: BikeEntityVO) => {
  return await request.post({ url: `/motocycleBike/bike-entity/create`, data })
}

// 修改电车实体
export const updateBikeEntity = async (data: BikeEntityVO) => {
  return await request.put({ url: `/motocycleBike/bike-entity/update`, data })
}

// 删除电车实体
export const deleteBikeEntity = async (id: number) => {
  return await request.delete({ url: `/motocycleBike/bike-entity/delete?id=` + id })
}

// 导出电车实体 Excel
export const exportBikeEntity = async (params) => {
  return await request.download({ url: `/motocycleBike/bike-entity/export-excel`, params })
}
