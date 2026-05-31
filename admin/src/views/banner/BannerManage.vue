<template>
  <div class="page-container">
    <div class="table-header">
      <h3>轮播图管理</h3>
      <el-button type="primary" @click="handleAdd">
        <el-icon><Plus /></el-icon>
        新增轮播图
      </el-button>
    </div>

    <el-table :data="tableData" stripe border style="width: 100%">
      <el-table-column prop="id" label="ID" width="60" />
      <el-table-column label="图片预览" width="150">
        <template #default="{ row }">
          <el-image
            :src="row.imageUrl"
            style="width: 120px; height: 60px"
            fit="cover"
            :preview-src-list="[row.imageUrl]"
          />
        </template>
      </el-table-column>
      <el-table-column prop="title" label="标题" />
      <el-table-column prop="subtitle" label="副标题" />
      <el-table-column prop="sortOrder" label="排序" width="80" />
      <el-table-column label="状态" width="80">
        <template #default="{ row }">
          <el-switch
            v-model="row.status"
            :active-value="1"
            :inactive-value="0"
            @change="handleStatusChange(row)"
          />
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
      :title="isEdit ? '编辑轮播图' : '新增轮播图'"
      width="600px"
      destroy-on-close
    >
      <el-form :model="formData" :rules="formRules" ref="formRef" label-width="100px">
        <el-form-item label="标题" prop="title">
          <el-input v-model="formData.title" placeholder="请输入标题" />
        </el-form-item>
        <el-form-item label="副标题" prop="subtitle">
          <el-input v-model="formData.subtitle" placeholder="请输入副标题" />
        </el-form-item>
        <el-form-item label="图片URL" prop="imageUrl">
          <el-input v-model="formData.imageUrl" placeholder="请输入图片URL" />
          <el-image
            v-if="formData.imageUrl"
            :src="formData.imageUrl"
            style="width: 200px; height: 100px; margin-top: 8px"
            fit="cover"
          />
        </el-form-item>
        <el-form-item label="跳转链接" prop="linkUrl">
          <el-input v-model="formData.linkUrl" placeholder="请输入跳转链接（可选）" />
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
import { ref, reactive, onMounted } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import { Plus } from '@element-plus/icons-vue'
import type { FormInstance, FormRules } from 'element-plus'
import { getBannerList, addBanner, updateBanner, deleteBanner, updateBannerStatus } from '../../api/banner'
import type { BannerItem } from '../../api/banner'

const tableData = ref<BannerItem[]>([])
const dialogVisible = ref(false)
const isEdit = ref(false)
const formRef = ref<FormInstance>()

const formData = reactive<BannerItem>({
  title: '',
  subtitle: '',
  imageUrl: '',
  linkUrl: '',
  sortOrder: 0,
  status: 1
})

const formRules: FormRules = {
  title: [{ required: true, message: '请输入标题', trigger: 'blur' }],
  imageUrl: [{ required: true, message: '请输入图片URL', trigger: 'blur' }]
}

const loadData = async () => {
  try {
    const res = await getBannerList()
    tableData.value = res.data || []
  } catch (e) {
    // handled by interceptor
  }
}

const resetForm = () => {
  formData.id = undefined
  formData.title = ''
  formData.subtitle = ''
  formData.imageUrl = ''
  formData.linkUrl = ''
  formData.sortOrder = 0
  formData.status = 1
}

const handleAdd = () => {
  resetForm()
  isEdit.value = false
  dialogVisible.value = true
}

const handleEdit = (row: BannerItem) => {
  isEdit.value = true
  Object.assign(formData, row)
  dialogVisible.value = true
}

const handleDelete = (row: BannerItem) => {
  ElMessageBox.confirm('确定要删除该轮播图吗？', '提示', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning'
  }).then(async () => {
    await deleteBanner(row.id!)
    ElMessage.success('删除成功')
    loadData()
  }).catch(() => {})
}

const handleStatusChange = async (row: BannerItem) => {
  try {
    await updateBannerStatus(row.id!, row.status)
    ElMessage.success('状态更新成功')
  } catch (e) {
    row.status = row.status === 1 ? 0 : 1
  }
}

const handleSubmit = async () => {
  if (!formRef.value) return
  await formRef.value.validate(async (valid) => {
    if (valid) {
      if (isEdit.value) {
        await updateBanner({ ...formData })
        ElMessage.success('更新成功')
      } else {
        await addBanner({ ...formData })
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
</script>

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
</style>
