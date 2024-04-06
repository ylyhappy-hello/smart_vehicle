import request from '@/config/axios'

export interface DetectFilesAnalysisVO {
  id: number
  fileId: number
  type: string
  result: string
  illegal: string
}

// 查询检测文件结果分析列表
export const getDetectFilesAnalysisPage = async (params) => {
  return await request.get({ url: `/motocycleDetect/detect-files-analysis/page`, params })
}

// 查询检测文件结果分析详情
export const getDetectFilesAnalysis = async (id: number) => {
  return await request.get({ url: `/motocycleDetect/detect-files-analysis/get?id=` + id })
}

// 新增检测文件结果分析
export const createDetectFilesAnalysis = async (data: DetectFilesAnalysisVO) => {
  return await request.post({ url: `/motocycleDetect/detect-files-analysis/create`, data })
}

// 修改检测文件结果分析
export const updateDetectFilesAnalysis = async (data: DetectFilesAnalysisVO) => {
  return await request.put({ url: `/motocycleDetect/detect-files-analysis/update`, data })
}

// 删除检测文件结果分析
export const deleteDetectFilesAnalysis = async (id: number) => {
  return await request.delete({ url: `/motocycleDetect/detect-files-analysis/delete?id=` + id })
}

// 导出检测文件结果分析 Excel
export const exportDetectFilesAnalysis = async (params) => {
  return await request.download({ url: `/motocycleDetect/detect-files-analysis/export-excel`, params })
}
