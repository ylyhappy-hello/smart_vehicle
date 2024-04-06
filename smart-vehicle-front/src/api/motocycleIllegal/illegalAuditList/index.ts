import request from '@/config/axios'

export interface IllegalAuditListVO {
  id: number
  illeageActionId: number
  status: string
  applicantMobile: string
  inspectorMobile: string
}

// 查询违规审核列表
export const getIllegalAuditListPage = async (params) => {
  return await request.get({ url: `/motocycleIllegal/illegal-audit-list/page`, params })
}

// 查询违规审核详情
export const getIllegalAuditList = async (id: number) => {
  return await request.get({ url: `/motocycleIllegal/illegal-audit-list/get?id=` + id })
}

// 新增违规审核
export const createIllegalAuditList = async (data: IllegalAuditListVO) => {
  return await request.post({ url: `/motocycleIllegal/illegal-audit-list/create`, data })
}

// 修改违规审核
export const updateIllegalAuditList = async (data: IllegalAuditListVO) => {
  return await request.put({ url: `/motocycleIllegal/illegal-audit-list/update`, data })
}

// 删除违规审核
export const deleteIllegalAuditList = async (id: number) => {
  return await request.delete({ url: `/motocycleIllegal/illegal-audit-list/delete?id=` + id })
}

// 导出违规审核 Excel
export const exportIllegalAuditList = async (params) => {
  return await request.download({ url: `/motocycleIllegal/illegal-audit-list/export-excel`, params })
}
