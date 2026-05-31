/**
 * API 调用封装
 * 封装 useFetch 统一调用后端 API
 */

// 通用 API 响应类型
interface ApiResult<T> {
  code: number
  message: string
  data: T
}

// 分页结果类型
interface PageResult<T> {
  total: number
  records: T[]
  page: number
  size: number
  pages: number
}

// 轮播图
export interface Banner {
  id: number
  title: string
  subtitle: string
  imageUrl: string
  linkUrl: string
  sortOrder: number
  status: number
  createTime: string
}

// 公司信息
export interface CompanyInfo {
  id: number
  companyName: string
  shortName: string
  logoUrl: string
  description: string
  foundedYear: number
  address: string
  phone: string
  email: string
  fax: string
  postalCode: string
  wechatQrcode: string
  qualification: string
  culture: string
  history: string
  createTime: string
  updateTime: string
}

// 业务领域
export interface Business {
  id: number
  title: string
  icon: string
  coverImage: string
  summary: string
  content: string
  sortOrder: number
  status: number
  createTime: string
  updateTime: string
}

// 工程案例
export interface CaseItem {
  id: number
  categoryId: number
  title: string
  coverImage: string
  images: string
  summary: string
  content: string
  location: string
  projectDate: string
  projectScale: string
  status: number
  isFeatured: number
  sortOrder: number
  createTime: string
}

// 案例分类
export interface CaseCategory {
  id: number
  name: string
  sortOrder: number
}

// 新闻
export interface NewsItem {
  id: number
  categoryId: number
  title: string
  coverImage: string
  summary: string
  content: string
  author: string
  viewCount: number
  isTop: number
  status: number
  publishTime: string
  createTime: string
  updateTime: string
}

// 新闻分类
export interface NewsCategory {
  id: number
  name: string
  sortOrder: number
}

// 合作伙伴
export interface Partner {
  id: number
  name: string
  logoUrl: string
  website: string
  sortOrder: number
}

// 留言
export interface Message {
  name: string
  phone: string
  email: string
  company: string
  subject: string
  content: string
}

export const useApi = () => {
  const config = useRuntimeConfig()
  const baseURL = config.public.apiBase as string

  /**
   * 通用 GET 请求封装
   */
  const get = <T>(url: string, params?: Record<string, any>) => {
    return useFetch<ApiResult<T>>(`${baseURL}${url}`, {
      method: 'GET',
      params
    })
  }

  /**
   * 通用 POST 请求封装
   */
  const post = <T>(url: string, body?: Record<string, any>) => {
    return useFetch<ApiResult<T>>(`${baseURL}${url}`, {
      method: 'POST',
      body
    })
  }

  // ==================== 轮播图 ====================

  /** 获取轮播图列表 */
  const getBanners = () => get<Banner[]>('/api/web/banner/list')

  // ==================== 公司信息 ====================

  /** 获取公司信息 */
  const getCompanyInfo = () => get<CompanyInfo>('/api/web/company/info')

  // ==================== 业务领域 ====================

  /** 获取业务领域列表 */
  const getBusinessList = () => get<Business[]>('/api/web/business/list')

  /** 获取业务详情 */
  const getBusinessDetail = (id: number | string) => get<Business>(`/api/web/business/${id}`)

  // ==================== 工程案例 ====================

  /** 获取案例列表（分页+分类筛选） */
  const getCaseList = (params: { categoryId?: number; page?: number; size?: number }) =>
    get<PageResult<CaseItem>>('/api/web/case/list', params)

  /** 获取案例详情 */
  const getCaseDetail = (id: number | string) => get<CaseItem>(`/api/web/case/${id}`)

  /** 获取首页推荐案例 */
  const getFeaturedCases = () => get<CaseItem[]>('/api/web/case/featured')

  /** 获取案例分类列表 */
  const getCaseCategories = () => get<CaseCategory[]>('/api/web/case/categories')

  // ==================== 新闻动态 ====================

  /** 获取新闻列表（分页+分类） */
  const getNewsList = (params: { categoryId?: number; page?: number; size?: number }) =>
    get<PageResult<NewsItem>>('/api/web/news/list', params)

  /** 获取新闻详情 */
  const getNewsDetail = (id: number | string) => get<NewsItem>(`/api/web/news/${id}`)

  /** 获取最新N条新闻 */
  const getLatestNews = (limit: number = 6) => get<NewsItem[]>('/api/web/news/latest', { limit })

  /** 获取新闻分类列表 */
  const getNewsCategories = () => get<NewsCategory[]>('/api/web/news/categories')

  // ==================== 合作伙伴 ====================

  /** 获取合作伙伴列表 */
  const getPartnerList = () => get<Partner[]>('/api/web/partner/list')

  // ==================== 留言 ====================

  /** 提交留言 */
  const submitMessage = (data: Message) => post<void>('/api/web/message/submit', data)

  // ==================== 站点配置 ====================

  /** 获取数字统计配置 */
  const getStats = () => get<Record<string, string>>('/api/web/config/stats')

  return {
    get,
    post,
    getBanners,
    getCompanyInfo,
    getBusinessList,
    getBusinessDetail,
    getCaseList,
    getCaseDetail,
    getFeaturedCases,
    getCaseCategories,
    getNewsList,
    getNewsDetail,
    getLatestNews,
    getNewsCategories,
    getPartnerList,
    submitMessage,
    getStats
  }
}
