import request from './request'

export interface CaseItem {
  id?: number
  title: string
  categoryId: number
  categoryName?: string
  coverUrl: string
  summary: string
  content: string
  location: string
  duration: string
  scale: string
  isRecommend: number
  sortOrder: number
  status: number
  createTime?: string
  updateTime?: string
}

export interface CaseCategory {
  id: number
  name: string
}

export function getCaseList(params?: { categoryId?: number; page?: number; size?: number }) {
  return request.get('/api/admin/cms/case/list', { params })
}

export function getCaseById(id: number) {
  return request.get(`/api/admin/cms/case/${id}`)
}

export function addCase(data: CaseItem) {
  return request.post('/api/admin/cms/case', data)
}

export function updateCase(data: CaseItem) {
  return request.put('/api/admin/cms/case', data)
}

export function deleteCase(id: number) {
  return request.delete(`/api/admin/cms/case/${id}`)
}

export function getCaseCategoryList() {
  return request.get('/api/admin/cms/case/categories')
}
