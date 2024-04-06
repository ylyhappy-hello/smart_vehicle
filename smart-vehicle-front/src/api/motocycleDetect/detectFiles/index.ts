import request from '@/config/axios'

export interface DetectFilesVO {
  id: number
  configId: number
  name: string
  path: string
  url: string
  type: string
  size: number
}

// 查询文件列表
export const getDetectFilesPage = async (params) => {
  return await request.get({ url: `/motocycleDetect/detect-files/page`, params })
}

// 查询文件分析列表
export const getAllDetectFiles = async () => {
  return await request.get({ url: `/motocycleDetect/detect-files/list-analysis` })
}

// 查询文件详情
export const getDetectFiles = async (id: number) => {
  return await request.get({ url: `/motocycleDetect/detect-files/get?id=` + id })
}

// 新增文件
export const createDetectFiles = async (data: DetectFilesVO) => {
  return await request.post({ url: `/motocycleDetect/detect-files/create`, data })
}

// 修改文件
export const updateDetectFiles = async (data: DetectFilesVO) => {
  return await request.put({ url: `/motocycleDetect/detect-files/update`, data })
}

// 删除文件
export const deleteDetectFiles = async (id: number) => {
  return await request.delete({ url: `/motocycleDetect/detect-files/delete?id=` + id })
}

// 导出文件 Excel
export const exportDetectFiles = async (params) => {
  return await request.download({ url: `/motocycleDetect/detect-files/export-excel`, params })
}
