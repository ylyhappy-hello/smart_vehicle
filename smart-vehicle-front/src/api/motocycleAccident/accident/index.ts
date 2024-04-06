import request from '@/config/axios'

export interface AccidentVO {
  id: number
  position: string
  reason: string
  detail: string
  resourceUrls: string
}

// 查询意外列表
export const getAccidentPage = async (params) => {
  return await request.get({ url: `/motocycleAccident/accident/page`, params })
}

// 查询意外详情
export const getAccident = async (id: number) => {
  return await request.get({ url: `/motocycleAccident/accident/get?id=` + id })
}

// 新增意外
export const createAccident = async (data: AccidentVO) => {
  return await request.post({ url: `/motocycleAccident/accident/create`, data })
}

// 修改意外
export const updateAccident = async (data: AccidentVO) => {
  return await request.put({ url: `/motocycleAccident/accident/update`, data })
}

// 删除意外
export const deleteAccident = async (id: number) => {
  return await request.delete({ url: `/motocycleAccident/accident/delete?id=` + id })
}

// 导出意外 Excel
export const exportAccident = async (params) => {
  return await request.download({ url: `/motocycleAccident/accident/export-excel`, params })
}
