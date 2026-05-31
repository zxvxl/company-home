// https://nuxt.com/docs/api/configuration/nuxt-config
export default defineNuxtConfig({
  devtools: { enabled: true },

  app: {
    head: {
      title: '虹远水利工程有限公司',
      meta: [
        { charset: 'utf-8' },
        { name: 'viewport', content: 'width=device-width, initial-scale=1' },
        { name: 'description', content: '虹远水利工程有限公司 - 专业从事水利工程设计、施工与管理，提供水库大坝、河道治理、农田灌溉、防洪排涝等全方位水利工程服务' },
        { name: 'keywords', content: '水利工程,水库大坝,河道治理,农田灌溉,防洪排涝,虹远水利' }
      ],
      link: [
        { rel: 'icon', type: 'image/x-icon', href: '/favicon.ico' }
      ]
    }
  },

  css: [
    '~/assets/css/variables.css',
    '~/assets/css/main.css'
  ],

  runtimeConfig: {
    public: {
      apiBase: process.env.NUXT_PUBLIC_API_BASE || 'http://localhost:8080'
    }
  },

  compatibilityDate: '2024-04-03'
})
