import request from './request'

export interface MessageItem {
  id?: number
  name: string
  phone: string
  company: string
  subject: string
  content: string
  status: number
  reply: string
  createTime?: string
  updateTime?: string
}

export function getMessageList(params?: { status?: number; page?: number; size?: number }) {
  return request.get('/api/admin/cms/message/list', { params })
}

export function getMessageById(id: number) {
  return request.get(`/api/admin/cms/message/${id}`)
}

export function markMessageRead(id: number) {
  return request.put(`/api/admin/cms/message/${id}/read`)
}

export function replyMessage(id: number, reply: string) {
  return request.put(`/api/admin/cms/message/${id}/reply`, { reply })
}

export function deleteMessage(id: number) {
  return request.delete(`/api/admin/cms/message/${id}`)
}
