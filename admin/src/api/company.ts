import request from './request'

export interface CompanyInfo {
  id?: number
  name: string
  shortName: string
  logoUrl: string
  description: string
  foundedYear: number
  address: string
  phone: string
  email: string
  fax: string
  zipCode: string
  qualifications: string
  mission: string
  vision: string
  values: string
  history: HistoryItem[]
}

export interface HistoryItem {
  year: string
  event: string
}

export function getCompanyInfo() {
  return request.get('/api/admin/cms/company')
}

export function updateCompanyInfo(data: CompanyInfo) {
  return request.put('/api/admin/cms/company', data)
}
