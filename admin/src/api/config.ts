import request from './request'

export interface SiteConfig {
  seoTitle: string
  seoKeywords: string
  seoDescription: string
  projectCount: number
  serviceYears: number
  teamSize: number
  clientCount: number
  icpNumber: string
  copyright: string
}

export function getSiteConfig() {
  return request.get('/api/admin/cms/config')
}

export function updateSiteConfig(data: SiteConfig) {
  return request.put('/api/admin/cms/config', data)
}
