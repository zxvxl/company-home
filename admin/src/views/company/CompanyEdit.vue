<template>
  <div class="page-container">
    <el-card shadow="never">
      <template #header>
        <span class="card-title">公司信息编辑</span>
      </template>

      <el-form :model="formData" :rules="formRules" ref="formRef" label-width="120px" size="default">
        <!-- 基本信息 -->
        <div class="form-section">
          <div class="section-title">基本信息</div>
          <el-row :gutter="20">
            <el-col :span="12">
              <el-form-item label="公司名称" prop="name">
                <el-input v-model="formData.name" placeholder="请输入公司全称" />
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="公司简称" prop="shortName">
                <el-input v-model="formData.shortName" placeholder="请输入公司简称" />
              </el-form-item>
            </el-col>
          </el-row>
          <el-row :gutter="20">
            <el-col :span="12">
              <el-form-item label="Logo URL" prop="logoUrl">
                <el-input v-model="formData.logoUrl" placeholder="请输入Logo图片URL" />
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="成立年份" prop="foundedYear">
                <el-input-number v-model="formData.foundedYear" :min="1900" :max="2100" />
              </el-form-item>
            </el-col>
          </el-row>
        </div>

        <!-- 联系方式 -->
        <div class="form-section">
          <div class="section-title">联系方式</div>
          <el-row :gutter="20">
            <el-col :span="12">
              <el-form-item label="地址" prop="address">
                <el-input v-model="formData.address" placeholder="请输入公司地址" />
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="电话" prop="phone">
                <el-input v-model="formData.phone" placeholder="请输入联系电话" />
              </el-form-item>
            </el-col>
          </el-row>
          <el-row :gutter="20">
            <el-col :span="12">
              <el-form-item label="邮箱" prop="email">
                <el-input v-model="formData.email" placeholder="请输入邮箱" />
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="传真" prop="fax">
                <el-input v-model="formData.fax" placeholder="请输入传真号" />
              </el-form-item>
            </el-col>
          </el-row>
          <el-row :gutter="20">
            <el-col :span="12">
              <el-form-item label="邮编" prop="zipCode">
                <el-input v-model="formData.zipCode" placeholder="请输入邮编" />
              </el-form-item>
            </el-col>
          </el-row>
        </div>

        <!-- 公司简介 -->
        <div class="form-section">
          <div class="section-title">公司简介</div>
          <el-form-item label="简介内容" prop="description">
            <div class="editor-container">
              <Toolbar
                class="toolbar"
                :editor="editorRef"
                :defaultConfig="toolbarConfig"
                mode="default"
              />
              <Editor
                class="editor-content"
                v-model="formData.description"
                :defaultConfig="editorConfig"
                mode="default"
                @onCreated="handleEditorCreated"
              />
            </div>
          </el-form-item>
        </div>

        <!-- 资质描述 -->
        <div class="form-section">
          <div class="section-title">资质描述</div>
          <el-form-item label="资质信息" prop="qualifications">
            <el-input
              v-model="formData.qualifications"
              type="textarea"
              :rows="4"
              placeholder="请输入企业资质描述"
            />
          </el-form-item>
        </div>

        <!-- 企业文化 -->
        <div class="form-section">
          <div class="section-title">企业文化</div>
          <el-form-item label="企业使命" prop="mission">
            <el-input v-model="formData.mission" placeholder="请输入企业使命" />
          </el-form-item>
          <el-form-item label="企业愿景" prop="vision">
            <el-input v-model="formData.vision" placeholder="请输入企业愿景" />
          </el-form-item>
          <el-form-item label="核心价值观" prop="values">
            <el-input v-model="formData.values" placeholder="请输入核心价值观" />
          </el-form-item>
        </div>

        <!-- 发展历程 -->
        <div class="form-section">
          <div class="section-title">
            发展历程
            <el-button type="primary" size="small" @click="addHistory" style="margin-left: 12px;">
              <el-icon><Plus /></el-icon> 添加
            </el-button>
          </div>
          <div v-for="(item, index) in formData.history" :key="index" class="history-item">
            <el-row :gutter="12" align="middle">
              <el-col :span="6">
                <el-input v-model="item.year" placeholder="年份（如：2010）" />
              </el-col>
              <el-col :span="16">
                <el-input v-model="item.event" placeholder="事件描述" />
              </el-col>
              <el-col :span="2">
                <el-button type="danger" :icon="Delete" circle @click="removeHistory(index)" />
              </el-col>
            </el-row>
          </div>
          <el-empty v-if="formData.history.length === 0" description="暂无发展历程，请点击添加" :image-size="60" />
        </div>

        <!-- 保存按钮 -->
        <el-form-item>
          <el-button type="primary" size="large" @click="handleSave" :loading="saving">
            保存信息
          </el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<script setup lang="ts">
import { ref, reactive, onMounted, onBeforeUnmount, shallowRef } from 'vue'
import { ElMessage } from 'element-plus'
import { Plus, Delete } from '@element-plus/icons-vue'
import type { FormInstance, FormRules } from 'element-plus'
import { Editor, Toolbar } from '@wangeditor/editor-for-vue'
import { getCompanyInfo, updateCompanyInfo } from '../../api/company'
import type { CompanyInfo, HistoryItem } from '../../api/company'

const formRef = ref<FormInstance>()
const saving = ref(false)
const editorRef = shallowRef()

const toolbarConfig = {}
const editorConfig = {
  placeholder: '请输入公司简介...'
}

const formData = reactive<CompanyInfo>({
  name: '',
  shortName: '',
  logoUrl: '',
  description: '',
  foundedYear: 2000,
  address: '',
  phone: '',
  email: '',
  fax: '',
  zipCode: '',
  qualifications: '',
  mission: '',
  vision: '',
  values: '',
  history: []
})

const formRules: FormRules = {
  name: [{ required: true, message: '请输入公司名称', trigger: 'blur' }],
  phone: [{ required: true, message: '请输入联系电话', trigger: 'blur' }]
}

const handleEditorCreated = (editor: any) => {
  editorRef.value = editor
}

const addHistory = () => {
  formData.history.push({ year: '', event: '' })
}

const removeHistory = (index: number) => {
  formData.history.splice(index, 1)
}

const loadData = async () => {
  try {
    const res = await getCompanyInfo()
    if (res.data) {
      Object.assign(formData, res.data)
      if (!formData.history) {
        formData.history = []
      }
    }
  } catch (e) {
    // handled by interceptor
  }
}

const handleSave = async () => {
  if (!formRef.value) return
  await formRef.value.validate(async (valid) => {
    if (valid) {
      saving.value = true
      try {
        await updateCompanyInfo({ ...formData })
        ElMessage.success('保存成功')
      } finally {
        saving.value = false
      }
    }
  })
}

onMounted(() => {
  loadData()
})

onBeforeUnmount(() => {
  if (editorRef.value) {
    editorRef.value.destroy()
  }
})
</script>

<style src="@wangeditor/editor/dist/css/style.css"></style>

<style scoped>
.page-container {
  max-width: 1000px;
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
  display: flex;
  align-items: center;
}

.history-item {
  margin-bottom: 12px;
  padding: 8px 0;
}

.editor-container {
  border: 1px solid #dcdfe6;
  border-radius: 4px;
  overflow: hidden;
  width: 100%;
}

.editor-container .toolbar {
  border-bottom: 1px solid #dcdfe6;
}

.editor-container .editor-content {
  height: 300px;
  overflow-y: auto;
}
</style>
