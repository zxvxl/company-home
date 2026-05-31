import request from './request'

export interface BusinessItem {
  id?: number
  title: string
  icon: string
  coverUrl: string
  summary: string
  content: string
  sortOrder: number
  status: number
  createTime?: string
  updateTime?: string
}

export function getBusinessList() {
  return request.get('/api/admin/cms/business/list')
}

export function getBusinessById(id: number) {
  return request.get(`/api/admin/cms/business/${id}`)
}

export function addBusiness(data: BusinessItem) {
  return request.post('/api/admin/cms/business', data)
}

export function updateBusiness(data: BusinessItem) {
  return request.put('/api/admin/cms/business', data)
}

export function deleteBusiness(id: number) {
  return request.delete(`/api/admin/cms/business/${id}`)
}
