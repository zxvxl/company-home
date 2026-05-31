/**
 * HTML 内容净化插件
 * 防止存储型 XSS 攻击
 */
export default defineNuxtPlugin(() => {
  return {
    provide: {
      sanitize: (html: string): string => {
        if (!html) return ''
        // 基本 XSS 防护：移除 script 标签和事件处理器
        return html
          .replace(/<script\b[^<]*(?:(?!<\/script>)<[^<]*)*<\/script>/gi, '')
          .replace(/on\w+\s*=\s*"[^"]*"/gi, '')
          .replace(/on\w+\s*=\s*'[^']*'/gi, '')
          .replace(/javascript:/gi, '')
      }
    }
  }
})
