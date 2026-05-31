<template>
  <div class="page-container">
    <el-card shadow="never">
      <template #header>
        <span class="card-title">站点配置</span>
      </template>

      <el-form :model="formData" ref="formRef" label-width="120px" size="default">
        <!-- SEO 设置 -->
        <div class="form-section">
          <div class="section-title">SEO 设置</div>
          <el-form-item label="网站标题" prop="seoTitle">
            <el-input v-model="formData.seoTitle" placeholder="请输入网站标题" />
          </el-form-item>
          <el-form-item label="关键词" prop="seoKeywords">
            <el-input v-model="formData.seoKeywords" placeholder="请输入SEO关键词，多个用逗号分隔" />
          </el-form-item>
          <el-form-item label="描述" prop="seoDescription">
            <el-input
              v-model="formData.seoDescription"
              type="textarea"
              :rows="3"
              placeholder="请输入网站描述"
            />
          </el-form-item>
        </div>

        <!-- 首页数字 -->
        <div class="form-section">
          <div class="section-title">首页数字展示</div>
          <el-row :gutter="20">
            <el-col :span="12">
              <el-form-item label="完工项目数" prop="projectCount">
                <el-input-number v-model="formData.projectCount" :min="0" :max="99999" />
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="服务年限" prop="serviceYears">
                <el-input-number v-model="formData.serviceYears" :min="0" :max="999" />
              </el-form-item>
            </el-col>
          </el-row>
          <el-row :gutter="20">
            <el-col :span="12">
              <el-form-item label="团队规模" prop="teamSize">
                <el-input-number v-model="formData.teamSize" :min="0" :max="99999" />
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="合作客户数" prop="clientCount">
                <el-input-number v-model="formData.clientCount" :min="0" :max="99999" />
              </el-form-item>
            </el-col>
          </el-row>
        </div>

        <!-- 其他设置 -->
        <div class="form-section">
          <div class="section-title">其他设置</div>
          <el-form-item label="ICP备案号" prop="icpNumber">
            <el-input v-model="formData.icpNumber" placeholder="如：京ICP备XXXXXXXX号" />
          </el-form-item>
          <el-form-item label="底部版权信息" prop="copyright">
            <el-input v-model="formData.copyright" placeholder="如：Copyright 2024 虹远水利工程有限公司" />
          </el-form-item>
        </div>

        <!-- 保存按钮 -->
        <el-form-item>
          <el-button type="primary" size="large" @click="handleSave" :loading="saving">
            保存配置
          </el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<script setup lang="ts">
import { ref, reactive, onMounted } from 'vue'
import { ElMessage } from 'element-plus'
import type { FormInstance } from 'element-plus'
import { getSiteConfig, updateSiteConfig } from '../../api/config'
import type { SiteConfig } from '../../api/config'

const formRef = ref<FormInstance>()
const saving = ref(false)

const formData = reactive<SiteConfig>({
  seoTitle: '',
  seoKeywords: '',
  seoDescription: '',
  projectCount: 0,
  serviceYears: 0,
  teamSize: 0,
  clientCount: 0,
  icpNumber: '',
  copyright: ''
})

const loadData = async () => {
  try {
    const res = await getSiteConfig()
    if (res.data) {
      Object.assign(formData, res.data)
    }
  } catch (e) {
    // handled by interceptor
  }
}

const handleSave = async () => {
  saving.value = true
  try {
    await updateSiteConfig({ ...formData })
    ElMessage.success('保存成功')
  } finally {
    saving.value = false
  }
}

onMounted(() => {
  loadData()
})
</script>

<style scoped>
.page-container {
  max-width: 800px;
}

.card-title {
  font-size: 18px;
  font-weight: bold;
  color: #303133;
}

.form-section {
  margin-bottom: 32px;
}

.section-title {
  font-size: 16px;
  font-weight: bold;
  color: #303133;
  margin-bottom: 16px;
  padding-bottom: 8px;
  border-bottom: 1px solid #ebeef5;
}
</style>
