/**
 * SEO 工具 - 动态设置页面 title 和 meta
 */
export const useSeo = (options: {
  title?: string
  description?: string
  keywords?: string
}) => {
  const defaultTitle = '虹远水利工程有限公司'
  const defaultDesc = '虹远水利工程有限公司专注水利工程建设，业务涵盖水库大坝、河道治理、农田灌溉、防洪排涝、水土保持及供水工程。'
  const defaultKeywords = '水利工程,水库大坝,河道治理,农田灌溉,防洪排涝,虹远水利'

  useHead({
    title: options.title ? `${options.title} - ${defaultTitle}` : defaultTitle,
    meta: [
      { name: 'description', content: options.description || defaultDesc },
      { name: 'keywords', content: options.keywords || defaultKeywords }
    ]
  })
}
