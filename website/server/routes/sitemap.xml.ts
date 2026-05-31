export default defineEventHandler(() => {
  const baseUrl = 'https://www.hongyuan-water.com'
  const pages = [
    { url: '/', changefreq: 'daily', priority: 1.0 },
    { url: '/about', changefreq: 'monthly', priority: 0.8 },
    { url: '/qualifications', changefreq: 'monthly', priority: 0.7 },
    { url: '/safety', changefreq: 'weekly', priority: 0.7 },
    { url: '/business', changefreq: 'monthly', priority: 0.8 },
    { url: '/cases', changefreq: 'weekly', priority: 0.8 },
    { url: '/news', changefreq: 'daily', priority: 0.9 },
    { url: '/careers', changefreq: 'weekly', priority: 0.6 },
    { url: '/contact', changefreq: 'monthly', priority: 0.6 },
  ]

  const xml = `<?xml version="1.0" encoding="UTF-8"?>
<urlset xmlns="http://www.sitemaps.org/schemas/sitemap/0.9">
${pages.map(p => `  <url>
    <loc>${baseUrl}${p.url}</loc>
    <changefreq>${p.changefreq}</changefreq>
    <priority>${p.priority}</priority>
  </url>`).join('\n')}
</urlset>`

  return new Response(xml, {
    headers: { 'Content-Type': 'application/xml' }
  })
})
