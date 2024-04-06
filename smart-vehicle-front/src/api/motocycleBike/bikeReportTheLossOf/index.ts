import request from '@/config/axios'

export interface BikeReportTheLossOfVO {
  id: number
  reportTheLossOfUserid: number
  reportTheLossOfAuditid: number
  bikeId: number
  status: string
}

// 查询电车挂失信息列表
export const getBikeReportTheLossOfPage = async (params) => {
  return await request.get({ url: `/motocycleBike/bike-report-the-loss-of/page`, params })
}

// 查询电车挂失信息详情
export const getBikeReportTheLossOf = async (id: number) => {
  return await request.get({ url: `/motocycleBike/bike-report-the-loss-of/get?id=` + id })
}

// 新增电车挂失信息
export const createBikeReportTheLossOf = async (data: BikeReportTheLossOfVO) => {
  return await request.post({ url: `/motocycleBike/bike-report-the-loss-of/create`, data })
}

// 修改电车挂失信息
export const updateBikeReportTheLossOf = async (data: BikeReportTheLossOfVO) => {
  return await request.put({ url: `/motocycleBike/bike-report-the-loss-of/update`, data })
}

// 删除电车挂失信息
export const deleteBikeReportTheLossOf = async (id: number) => {
  return await request.delete({ url: `/motocycleBike/bike-report-the-loss-of/delete?id=` + id })
}

// 导出电车挂失信息 Excel
export const exportBikeReportTheLossOf = async (params) => {
  return await request.download({ url: `/motocycleBike/bike-report-the-loss-of/export-excel`, params })
}
