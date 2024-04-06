import request from '@/config/axios'

export interface IllegalWaringTypeVO {
  id: number
  waringId: number
  type: number 
  penalty: number
  daysInDetention: number
}

// 查询违规处罚力度列表
export const getIllegalWaringTypePage = async (params) => {
  return await request.get({ url: `/motocycleIllegal/illegal-waring-type/page`, params })
}

// 查询违规处罚力度详情
export const getIllegalWaringType = async (id: number) => {
  return await request.get({ url: `/motocycleIllegal/illegal-waring-type/get?id=` + id })
}

// 新增违规处罚力度
export const createIllegalWaringType = async (data: IllegalWaringTypeVO) => {
  return await request.post({ url: `/motocycleIllegal/illegal-waring-type/create`, data })
}

// 修改违规处罚力度
export const updateIllegalWaringType = async (data: IllegalWaringTypeVO) => {
  return await request.put({ url: `/motocycleIllegal/illegal-waring-type/update`, data })
}

// 删除违规处罚力度
export const deleteIllegalWaringType = async (id: number) => {
  return await request.delete({ url: `/motocycleIllegal/illegal-waring-type/delete?id=` + id })
}

// 导出违规处罚力度 Excel
export const exportIllegalWaringType = async (params) => {
  return await request.download({ url: `/motocycleIllegal/illegal-waring-type/export-excel`, params })
}
