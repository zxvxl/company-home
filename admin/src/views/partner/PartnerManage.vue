<template>
  <div class="page-container">
    <div class="table-header">
      <h3>合作伙伴管理</h3>
      <el-button type="primary" @click="handleAdd">
        <el-icon><Plus /></el-icon>
        新增合作伙伴
      </el-button>
    </div>

    <el-table :data="tableData" stripe border style="width: 100%">
      <el-table-column prop="id" label="ID" width="60" />
      <el-table-column label="Logo" width="120">
        <template #default="{ row }">
          <el-image
            :src="row.logoUrl"
            style="width: 80px; height: 40px"
            fit="contain"
          />
        </template>
      </el-table-column>
      <el-table-column prop="name" label="名称" />
      <el-table-column label="官网链接" min-width="180">
        <template #default="{ row }">
          <el-link :href="row.website" type="primary" target="_blank" :underline="false">
            {{ row.website }}
          </el-link>
        </template>
      </el-table-column>
      <el-table-column prop="sortOrder" label="排序" width="80" />
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

    <!-- 新增/编辑弹窗 -->
    <el-dialog
      v-model="dialogVisible"
      :title="isEdit ? '编辑合作伙伴' : '新增合作伙伴'"
      width="500px"
      destroy-on-close
    >
      <el-form :model="formData" :rules="formRules" ref="formRef" label-width="100px">
        <el-form-item label="名称" prop="name">
          <el-input v-model="formData.name" placeholder="请输入合作伙伴名称" />
        </el-form-item>
        <el-form-item label="Logo URL" prop="logoUrl">
          <el-input v-model="formData.logoUrl" placeholder="请输入Logo URL" />
          <el-image
            v-if="formData.logoUrl"
            :src="formData.logoUrl"
            style="width: 120px; height: 60px; margin-top: 8px"
            fit="contain"
          />
        </el-form-item>
        <el-form-item label="官网链接" prop="website">
          <el-input v-model="formData.website" placeholder="请输入官网链接" />
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
import { getPartnerList, addPartner, updatePartner, deletePartner } from '../../api/partner'
import type { PartnerItem } from '../../api/partner'

const tableData = ref<PartnerItem[]>([])
const dialogVisible = ref(false)
const isEdit = ref(false)
const formRef = ref<FormInstance>()

const formData = reactive<PartnerItem>({
  name: '',
  logoUrl: '',
  website: '',
  sortOrder: 0,
  status: 1
})

const formRules: FormRules = {
  name: [{ required: true, message: '请输入名称', trigger: 'blur' }],
  logoUrl: [{ required: true, message: '请输入Logo URL', trigger: 'blur' }]
}

const loadData = async () => {
  try {
    const res = await getPartnerList()
    tableData.value = res.data || []
  } catch (e) {}
}

const resetForm = () => {
  formData.id = undefined
  formData.name = ''
  formData.logoUrl = ''
  formData.website = ''
  formData.sortOrder = 0
  formData.status = 1
}

const handleAdd = () => {
  resetForm()
  isEdit.value = false
  dialogVisible.value = true
}

const handleEdit = (row: PartnerItem) => {
  isEdit.value = true
  Object.assign(formData, row)
  dialogVisible.value = true
}

const handleDelete = (row: PartnerItem) => {
  ElMessageBox.confirm('确定要删除该合作伙伴吗？', '提示', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning'
  }).then(async () => {
    await deletePartner(row.id!)
    ElMessage.success('删除成功')
    loadData()
  }).catch(() => {})
}

const handleSubmit = async () => {
  if (!formRef.value) return
  await formRef.value.validate(async (valid) => {
    if (valid) {
      if (isEdit.value) {
        await updatePartner({ ...formData })
        ElMessage.success('更新成功')
      } else {
        await addPartner({ ...formData })
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
