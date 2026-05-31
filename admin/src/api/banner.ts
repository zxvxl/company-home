import request from './request'

export interface BannerItem {
  id?: number
  title: string
  subtitle: string
  imageUrl: string
  linkUrl: string
  sortOrder: number
  status: number
  createTime?: string
  updateTime?: string
}

export function getBannerList() {
  return request.get('/api/admin/cms/banner/list')
}

export function getBannerById(id: number) {
  return request.get(`/api/admin/cms/banner/${id}`)
}

export function addBanner(data: BannerItem) {
  return request.post('/api/admin/cms/banner', data)
}

export function updateBanner(data: BannerItem) {
  return request.put('/api/admin/cms/banner', data)
}

export function deleteBanner(id: number) {
  return request.delete(`/api/admin/cms/banner/${id}`)
}

export function updateBannerStatus(id: number, status: number) {
  return request.put(`/api/admin/cms/banner/${id}/status`, { status })
}
