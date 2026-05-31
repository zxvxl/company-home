/**
 * CMS API 调用封装
 * 适配 RuoYi-Vue-CMS 的 /api/web/ 公开接口
 */

interface ApiResponse<T = any> {
  code: number
  msg: string
  data: T
}

interface ContentItem {
  contentId: number
  title: string
  subTitle?: string
  summary?: string
  logo?: string
  author?: string
  publishDate?: string
  status?: string
  catalogId?: number
  sortFlag?: number
  keywords?: string[]
  tags?: string[]
}

interface CatalogItem {
  catalogId: number
  name: string
  alias?: string
  parentId?: number
  sort?: number
}

interface SiteInfo {
  siteId: number
  name: string
  description?: string
  logo?: string
}

interface PageResult<T> {
  records: T[]
  total: number
  size: number
  current: number
  pages: number
}

export const useApi = () => {
  const config = useRuntimeConfig()
  const baseURL = config.public.apiBase as string

  const request = async <T = any>(url: string, options?: any): Promise<T | null> => {
    try {
      const { data } = await useFetch<ApiResponse<T>>(`${baseURL}/api/web${url}`, {
        ...options,
        key: url + JSON.stringify(options?.params || {})
      })
      if (data.value && data.value.code === 200) {
        return data.value.data
      }
      return null
    } catch (error) {
      console.warn(`[API] Request failed: ${url}`, error)
      return null
    }
  }

  /**
   * 获取站点信息
   */
  const getSiteInfo = () => request<SiteInfo>('/site/info')

  /**
   * 获取栏目列表
   */
  const getCatalogs = (parentId?: number) =>
    request<CatalogItem[]>('/catalogs', {
      params: parentId ? { parentId } : {}
    })

  /**
   * 获取栏目下的内容列表
   */
  const getContentsByCatalog = (catalogId: number, pageNum = 1, pageSize = 10) =>
    request<PageResult<ContentItem>>(`/catalog/${catalogId}/contents`, {
      params: { pageNum, pageSize }
    })

  /**
   * 获取内容详情
   */
  const getContentDetail = (contentId: number) =>
    request<ContentItem>(`/content/${contentId}`)

  /**
   * 获取推荐内容
   */
  const getFeaturedContents = (limit = 6) =>
    request<ContentItem[]>('/contents/featured', {
      params: { limit }
    })

  return {
    getSiteInfo,
    getCatalogs,
    getContentsByCatalog,
    getContentDetail,
    getFeaturedContents
  }
}
