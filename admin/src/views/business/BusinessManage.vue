<template>
  <div class="page-container">
    <div class="table-header">
      <h3>业务领域管理</h3>
      <el-button type="primary" @click="handleAdd">
        <el-icon><Plus /></el-icon>
        新增业务
      </el-button>
    </div>

    <el-table :data="tableData" stripe border style="width: 100%">
      <el-table-column prop="id" label="ID" width="60" />
      <el-table-column prop="title" label="标题" />
      <el-table-column prop="icon" label="图标" width="100" />
      <el-table-column prop="sortOrder" label="排序" width="80" />
      <el-table-column label="状态" width="80">
        <template #default="{ row }">
          <el-tag :type="row.status === 1 ? 'success' : 'info'">
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

    <!-- 新增/编辑弹窗 -->
    <el-dialog
      v-model="dialogVisible"
      :title="isEdit ? '编辑业务领域' : '新增业务领域'"
      width="700px"
      destroy-on-close
    >
      <el-form :model="formData" :rules="formRules" ref="formRef" label-width="100px">
        <el-form-item label="标题" prop="title">
          <el-input v-model="formData.title" placeholder="请输入业务标题" />
        </el-form-item>
        <el-form-item label="图标" prop="icon">
          <el-input v-model="formData.icon" placeholder="请输入图标名称（如：Water, Dam）" />
        </el-form-item>
        <el-form-item label="封面图URL" prop="coverUrl">
          <el-input v-model="formData.coverUrl" placeholder="请输入封面图URL" />
          <el-image
            v-if="formData.coverUrl"
            :src="formData.coverUrl"
            style="width: 200px; height: 100px; margin-top: 8px"
            fit="cover"
          />
        </el-form-item>
        <el-form-item label="简要描述" prop="summary">
          <el-input
            v-model="formData.summary"
            type="textarea"
            :rows="3"
            placeholder="请输入简要描述"
          />
        </el-form-item>
        <el-form-item label="详细内容" prop="content">
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
        <el-form-item label="排序" prop="sortOrder">
          <el-input-number v-model="formData.sortOrder" :min="0" :max="999" />
        </el-form-item>
        <el-form-item label="状态" prop="status">
          <el-switch v-model="formData.status" :active-value="1" :inactive-value="0" active-text="启用" inactive-text="停用" />
        </el-form-item>
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
import { getBusinessList, addBusiness, updateBusiness, deleteBusiness } from '../../api/business'
import type { BusinessItem } from '../../api/business'

const tableData = ref<BusinessItem[]>([])
const dialogVisible = ref(false)
const isEdit = ref(false)
const formRef = ref<FormInstance>()
const editorRef = shallowRef()

const toolbarConfig = {}
const editorConfig = {
  placeholder: '请输入详细内容...'
}

const formData = reactive<BusinessItem>({
  title: '',
  icon: '',
  coverUrl: '',
  summary: '',
  content: '',
  sortOrder: 0,
  status: 1
})

const formRules: FormRules = {
  title: [{ required: true, message: '请输入标题', trigger: 'blur' }],
  summary: [{ required: true, message: '请输入简要描述', trigger: 'blur' }]
}

const handleEditorCreated = (editor: any) => {
  editorRef.value = editor
}

const loadData = async () => {
  try {
    const res = await getBusinessList()
    tableData.value = res.data || []
  } catch (e) {
    // handled by interceptor
  }
}

const resetForm = () => {
  formData.id = undefined
  formData.title = ''
  formData.icon = ''
  formData.coverUrl = ''
  formData.summary = ''
  formData.content = ''
  formData.sortOrder = 0
  formData.status = 1
}

const handleAdd = () => {
  resetForm()
  isEdit.value = false
  dialogVisible.value = true
}

const handleEdit = (row: BusinessItem) => {
  isEdit.value = true
  Object.assign(formData, row)
  dialogVisible.value = true
}

const handleDelete = (row: BusinessItem) => {
  ElMessageBox.confirm('确定要删除该业务领域吗？', '提示', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning'
  }).then(async () => {
    await deleteBusiness(row.id!)
    ElMessage.success('删除成功')
    loadData()
  }).catch(() => {})
}

const handleSubmit = async () => {
  if (!formRef.value) return
  await formRef.value.validate(async (valid) => {
    if (valid) {
      if (isEdit.value) {
        await updateBusiness({ ...formData })
        ElMessage.success('更新成功')
      } else {
        await addBusiness({ ...formData })
        ElMessage.success('新增成功')
      }
      dialogVisible.value = false
      loadData()
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
