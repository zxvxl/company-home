<template>
  <div class="page-container">
    <div class="table-header">
      <h3>工程案例管理</h3>
      <div class="header-actions">
        <el-select v-model="filterCategoryId" placeholder="全部分类" clearable style="width: 160px; margin-right: 12px;" @change="loadData">
          <el-option v-for="cat in categoryList" :key="cat.id" :label="cat.name" :value="cat.id" />
        </el-select>
        <el-button type="primary" @click="handleAdd">
          <el-icon><Plus /></el-icon>
          新增案例
        </el-button>
      </div>
    </div>

    <el-table :data="tableData" stripe border style="width: 100%">
      <el-table-column prop="id" label="ID" width="60" />
      <el-table-column prop="title" label="标题" min-width="160" />
      <el-table-column prop="categoryName" label="分类" width="120" />
      <el-table-column prop="location" label="地点" width="120" />
      <el-table-column label="推荐" width="80">
        <template #default="{ row }">
          <el-tag :type="row.isRecommend === 1 ? 'success' : 'info'" size="small">
            {{ row.isRecommend === 1 ? '是' : '否' }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column label="状态" width="80">
        <template #default="{ row }">
          <el-tag :type="row.status === 1 ? 'success' : 'info'" size="small">
            {{ row.status === 1 ? '启用' : '停用' }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column label="操作" width="180" fixed="right">
        <template #default="{ row }">
          <el-button type="primary" link @click="handleEdit(row)">编辑</el-button>
          <el-button type="danger" link @click="handleDelete(row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 分页 -->
    <div class="pagination-wrap">
      <el-pagination
        v-model:current-page="currentPage"
        v-model:page-size="pageSize"
        :total="total"
        :page-sizes="[10, 20, 50]"
        layout="total, sizes, prev, pager, next"
        @size-change="loadData"
        @current-change="loadData"
      />
    </div>

    <!-- 新增/编辑弹窗 -->
    <el-dialog
      v-model="dialogVisible"
      :title="isEdit ? '编辑工程案例' : '新增工程案例'"
      width="800px"
      destroy-on-close
    >
      <el-form :model="formData" :rules="formRules" ref="formRef" label-width="100px">
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="标题" prop="title">
              <el-input v-model="formData.title" placeholder="请输入标题" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="分类" prop="categoryId">
              <el-select v-model="formData.categoryId" placeholder="请选择分类" style="width: 100%;">
                <el-option v-for="cat in categoryList" :key="cat.id" :label="cat.name" :value="cat.id" />
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-form-item label="封面图URL" prop="coverUrl">
          <el-input v-model="formData.coverUrl" placeholder="请输入封面图URL" />
          <el-image
            v-if="formData.coverUrl"
            :src="formData.coverUrl"
            style="width: 200px; height: 100px; margin-top: 8px"
            fit="cover"
          />
        </el-form-item>
        <el-form-item label="项目摘要" prop="summary">
          <el-input
            v-model="formData.summary"
            type="textarea"
            :rows="3"
            placeholder="请输入项目摘要"
          />
        </el-form-item>
        <el-form-item label="项目详情" prop="content">
          <div class="editor-container">
            <Toolbar
              class="toolbar"
              :editor="editorRef"
              :defaultConfig="toolbarConfig"
              mode="default"
            />
            <Editor
              class="editor-content"
              v-model="formData.content"
              :defaultConfig="editorConfig"
              mode="default"
              @onCreated="handleEditorCreated"
            />
          </div>
        </el-form-item>
        <el-row :gutter="20">
          <el-col :span="8">
            <el-form-item label="项目地点" prop="location">
              <el-input v-model="formData.location" placeholder="项目地点" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="工期" prop="duration">
              <el-input v-model="formData.duration" placeholder="如：12个月" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="工程规模" prop="scale">
              <el-input v-model="formData.scale" placeholder="工程规模" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="8">
            <el-form-item label="首页推荐">
              <el-switch v-model="formData.isRecommend" :active-value="1" :inactive-value="0" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="排序">
              <el-input-number v-model="formData.sortOrder" :min="0" :max="999" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="状态">
              <el-switch v-model="formData.status" :active-value="1" :inactive-value="0" active-text="启用" inactive-text="停用" />
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <template #footer>
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="handleSubmit">确定</el-button>
      </template>
    </el-dialog>
  </div>
</template>

<script setup lang="ts">
import { ref, reactive, onMounted, onBeforeUnmount, shallowRef } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import { Plus } from '@element-plus/icons-vue'
import type { FormInstance, FormRules } from 'element-plus'
import { Editor, Toolbar } from '@wangeditor/editor-for-vue'
import { getCaseList, addCase, updateCase, deleteCase, getCaseCategoryList } from '../../api/case'
import type { CaseItem, CaseCategory } from '../../api/case'

const tableData = ref<CaseItem[]>([])
const categoryList = ref<CaseCategory[]>([])
const dialogVisible = ref(false)
const isEdit = ref(false)
const formRef = ref<FormInstance>()
const editorRef = shallowRef()
const filterCategoryId = ref<number | undefined>(undefined)
const currentPage = ref(1)
const pageSize = ref(10)
const total = ref(0)

const toolbarConfig = {}
const editorConfig = {
  placeholder: '请输入项目详情...'
}

const formData = reactive<CaseItem>({
  title: '',
  categoryId: 0,
  coverUrl: '',
  summary: '',
  content: '',
  location: '',
  duration: '',
  scale: '',
  isRecommend: 0,
  sortOrder: 0,
  status: 1
})

const formRules: FormRules = {
  title: [{ required: true, message: '请输入标题', trigger: 'blur' }],
  categoryId: [{ required: true, message: '请选择分类', trigger: 'change' }]
}

const handleEditorCreated = (editor: any) => {
  editorRef.value = editor
}

const loadCategories = async () => {
  try {
    const res = await getCaseCategoryList()
    categoryList.value = res.data || []
  } catch (e) {}
}

const loadData = async () => {
  try {
    const params: any = { page: currentPage.value, size: pageSize.value }
    if (filterCategoryId.value) {
      params.categoryId = filterCategoryId.value
    }
    const res = await getCaseList(params)
    tableData.value = res.data?.records || res.data?.list || []
    total.value = res.data?.total || 0
  } catch (e) {}
}

const resetForm = () => {
  formData.id = undefined
  formData.title = ''
  formData.categoryId = 0
  formData.coverUrl = ''
  formData.summary = ''
  formData.content = ''
  formData.location = ''
  formData.duration = ''
  formData.scale = ''
  formData.isRecommend = 0
  formData.sortOrder = 0
  formData.status = 1
}

const handleAdd = () => {
  resetForm()
  isEdit.value = false
  dialogVisible.value = true
}

const handleEdit = (row: CaseItem) => {
  isEdit.value = true
  Object.assign(formData, row)
  dialogVisible.value = true
}

const handleDelete = (row: CaseItem) => {
  ElMessageBox.confirm('确定要删除该工程案例吗？', '提示', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning'
  }).then(async () => {
    await deleteCase(row.id!)
    ElMessage.success('删除成功')
    loadData()
  }).catch(() => {})
}

const handleSubmit = async () => {
  if (!formRef.value) return
  await formRef.value.validate(async (valid) => {
    if (valid) {
      if (isEdit.value) {
        await updateCase({ ...formData })
        ElMessage.success('更新成功')
      } else {
        await addCase({ ...formData })
        ElMessage.success('新增成功')
      }
      dialogVisible.value = false
      loadData()
    }
  })
}

onMounted(() => {
  loadCategories()
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
  background: #fff;
  padding: 20px;
  border-radius: 4px;
}

.table-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 16px;
}

.table-header h3 {
  margin: 0;
  font-size: 18px;
  color: #303133;
}

.header-actions {
  display: flex;
  align-items: center;
}

.pagination-wrap {
  margin-top: 16px;
  display: flex;
  justify-content: flex-end;
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
