// https://nuxt.com/docs/api/configuration/nuxt-config
export default defineNuxtConfig({
  devtools: { enabled: false },

  app: {
    head: {
      title: '虹远水利工程有限公司',
      meta: [
        { charset: 'utf-8' },
        { name: 'viewport', content: 'width=device-width, initial-scale=1' },
        { name: 'description', content: '虹远水利工程有限公司 - 专注水库大坝、河道治理、农田灌溉、防洪排涝、水土保持、供水工程' },
        { name: 'keywords', content: '水利工程,水库大坝,河道治理,农田灌溉,防洪排涝,水土保持,供水工程' }
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

  compatibilityDate: '2024-04-01'
})
