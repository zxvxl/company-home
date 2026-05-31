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
      ],
      script: [
        // 百度统计（替换为实际统计代码）
        {
          children: `
            var _hmt = _hmt || [];
            (function() {
              var hm = document.createElement("script");
              hm.src = "https://hm.baidu.com/hm.js?YOUR_BAIDU_TONGJI_ID";
              var s = document.getElementsByTagName("script")[0];
              s.parentNode.insertBefore(hm, s);
            })();
          `,
          type: 'text/javascript'
        }
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

  routeRules: {
    // 不常变的页面启用 ISR（1小时缓存）
    '/about': { isr: 3600 },
    '/qualifications': { isr: 3600 },
    '/safety': { isr: 3600 },
    '/business': { isr: 3600 },
    '/contact': { isr: 3600 },
    // 新闻和案例列表（10分钟缓存）
    '/news': { isr: 600 },
    '/cases': { isr: 600 },
    // 首页（5分钟缓存）
    '/': { isr: 300 },
  },

  compatibilityDate: '2024-04-01'
})
