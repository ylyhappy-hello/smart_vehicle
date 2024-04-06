import request from '@/config/axios'

export interface IllegalActionVO {
  id: number
  position: string
  type: string
  reason: string
  auditResult: string
  punish: string
}

// 查询违规行为列表
export const getIllegalActionPage = async (params) => {
  return await request.get({ url: `/motocycleIllegal/illegal-action/page`, params })
}

// 查询违规行为详情
export const getIllegalAction = async (id: number) => {
  return await request.get({ url: `/motocycleIllegal/illegal-action/get?id=` + id })
}

// 新增违规行为
export const createIllegalAction = async (data: IllegalActionVO) => {
  return await request.post({ url: `/motocycleIllegal/illegal-action/create`, data })
}

// 修改违规行为
export const updateIllegalAction = async (data: IllegalActionVO) => {
  return await request.put({ url: `/motocycleIllegal/illegal-action/update`, data })
}

// 删除违规行为
export const deleteIllegalAction = async (id: number) => {
  return await request.delete({ url: `/motocycleIllegal/illegal-action/delete?id=` + id })
}

// 导出违规行为 Excel
export const exportIllegalAction = async (params) => {
  return await request.download({ url: `/motocycleIllegal/illegal-action/export-excel`, params })
}
