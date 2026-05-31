import request from './request'

export interface NewsItem {
  id?: number
  title: string
  categoryId: number
  categoryName?: string
  coverUrl: string
  summary: string
  content: string
  author: string
  viewCount?: number
  status: number
  isTop: number
  publishTime?: string
  createTime?: string
  updateTime?: string
}

export interface NewsCategory {
  id: number
  name: string
}

export function getNewsList(params?: { categoryId?: number; status?: number; page?: number; size?: number }) {
  return request.get('/api/admin/cms/news/list', { params })
}

export function getNewsById(id: number) {
  return request.get(`/api/admin/cms/news/${id}`)
}

export function addNews(data: NewsItem) {
  return request.post('/api/admin/cms/news', data)
}

export function updateNews(data: NewsItem) {
  return request.put('/api/admin/cms/news', data)
}

export function deleteNews(id: number) {
  return request.delete(`/api/admin/cms/news/${id}`)
}

export function publishNews(id: number) {
  return request.put(`/api/admin/cms/news/${id}/publish`)
}

export function withdrawNews(id: number) {
  return request.put(`/api/admin/cms/news/${id}/withdraw`)
}

export function getNewsCategoryList() {
  return request.get('/api/admin/cms/news/categories')
}
