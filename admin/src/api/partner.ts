import request from './request'

export interface PartnerItem {
  id?: number
  name: string
  logoUrl: string
  website: string
  sortOrder: number
  status: number
  createTime?: string
  updateTime?: string
}

export function getPartnerList() {
  return request.get('/api/admin/cms/partner/list')
}

export function getPartnerById(id: number) {
  return request.get(`/api/admin/cms/partner/${id}`)
}

export function addPartner(data: PartnerItem) {
  return request.post('/api/admin/cms/partner', data)
}

export function updatePartner(data: PartnerItem) {
  return request.put('/api/admin/cms/partner', data)
}

export function deletePartner(id: number) {
  return request.delete(`/api/admin/cms/partner/${id}`)
}
