<template>
  <div class="page-container">
    <div class="table-header">
      <h3>留言管理</h3>
      <el-select v-model="filterStatus" placeholder="全部状态" clearable style="width: 120px;" @change="loadData">
        <el-option label="未读" :value="0" />
        <el-option label="已读" :value="1" />
      </el-select>
    </div>

    <el-table :data="tableData" stripe border style="width: 100%">
      <el-table-column prop="id" label="ID" width="60" />
      <el-table-column prop="name" label="姓名" width="100" />
      <el-table-column prop="phone" label="电话" width="130" />
      <el-table-column prop="company" label="公司" width="150" show-overflow-tooltip />
      <el-table-column prop="subject" label="主题" min-width="150" show-overflow-tooltip />
      <el-table-column label="状态" width="80">
        <template #default="{ row }">
          <el-tag :type="row.status === 1 ? 'success' : 'danger'" size="small">
            {{ row.status === 1 ? '已读' : '未读' }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="createTime" label="提交时间" width="160" />
      <el-table-column label="操作" width="240" fixed="right">
        <template #default="{ row }">
          <el-button type="primary" link @click="handleView(row)">查看</el-button>
          <el-button v-if="row.status === 0" type="success" link @click="handleMarkRead(row)">标记已读</el-button>
          <el-button type="warning" link @click="handleReply(row)">回复</el-button>
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

    <!-- 详情弹窗 -->
    <el-dialog
      v-model="detailVisible"
      title="留言详情"
      width="600px"
    >
      <el-descriptions :column="2" border>
        <el-descriptions-item label="姓名">{{ currentMessage.name }}</el-descriptions-item>
        <el-descriptions-item label="电话">{{ currentMessage.phone }}</el-descriptions-item>
        <el-descriptions-item label="公司">{{ currentMessage.company }}</el-descriptions-item>
        <el-descriptions-item label="提交时间">{{ currentMessage.createTime }}</el-descriptions-item>
        <el-descriptions-item label="主题" :span="2">{{ currentMessage.subject }}</el-descriptions-item>
        <el-descriptions-item label="留言内容" :span="2">
          <div class="message-content">{{ currentMessage.content }}</div>
        </el-descriptions-item>
        <el-descriptions-item label="回复内容" :span="2" v-if="currentMessage.reply">
          <div class="reply-content">{{ currentMessage.reply }}</div>
        </el-descriptions-item>
      </el-descriptions>
    </el-dialog>

    <!-- 回复弹窗 -->
    <el-dialog
      v-model="replyVisible"
      title="回复留言"
      width="500px"
    >
      <div class="reply-info">
        <p><strong>来自：</strong>{{ currentMessage.name }}（{{ currentMessage.company }}）</p>
        <p><strong>主题：</strong>{{ currentMessage.subject }}</p>
        <p><strong>内容：</strong>{{ currentMessage.content }}</p>
      </div>
      <el-divider />
      <el-form>
        <el-form-item label="回复内容">
          <el-input
            v-model="replyContent"
            type="textarea"
            :rows="5"
            placeholder="请输入回复内容"
          />
        </el-form-item>
      </el-form>
      <template #footer>
        <el-button @click="replyVisible = false">取消</el-button>
        <el-button type="primary" @click="submitReply">发送回复</el-button>
      </template>
    </el-dialog>
  </div>
</template>

<script setup lang="ts">
import { ref, reactive, onMounted } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import { getMessageList, markMessageRead, replyMessage, deleteMessage } from '../../api/message'
import type { MessageItem } from '../../api/message'

const tableData = ref<MessageItem[]>([])
const filterStatus = ref<number | undefined>(undefined)
const currentPage = ref(1)
const pageSize = ref(10)
const total = ref(0)
const detailVisible = ref(false)
const replyVisible = ref(false)
const replyContent = ref('')

const currentMessage = reactive<MessageItem>({
  name: '',
  phone: '',
  company: '',
  subject: '',
  content: '',
  status: 0,
  reply: ''
})

const loadData = async () => {
  try {
    const params: any = { page: currentPage.value, size: pageSize.value }
    if (filterStatus.value !== undefined) {
      params.status = filterStatus.value
    }
    const res = await getMessageList(params)
    tableData.value = res.data?.records || res.data?.list || []
    total.value = res.data?.total || 0
  } catch (e) {}
}

const handleView = (row: MessageItem) => {
  Object.assign(currentMessage, row)
  detailVisible.value = true
  // 查看时自动标记已读
  if (row.status === 0) {
    markMessageRead(row.id!).then(() => {
      row.status = 1
    })
  }
}

const handleMarkRead = async (row: MessageItem) => {
  try {
    await markMessageRead(row.id!)
    ElMessage.success('已标记为已读')
    row.status = 1
  } catch (e) {}
}

const handleReply = (row: MessageItem) => {
  Object.assign(currentMessage, row)
  replyContent.value = row.reply || ''
  replyVisible.value = true
}

const submitReply = async () => {
  if (!replyContent.value.trim()) {
    ElMessage.warning('请输入回复内容')
    return
  }
  try {
    await replyMessage(currentMessage.id!, replyContent.value)
    ElMessage.success('回复成功')
    replyVisible.value = false
    loadData()
  } catch (e) {}
}

const handleDelete = (row: MessageItem) => {
  ElMessageBox.confirm('确定要删除该留言吗？', '提示', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning'
  }).then(async () => {
    await deleteMessage(row.id!)
    ElMessage.success('删除成功')
    loadData()
  }).catch(() => {})
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

.pagination-wrap {
  margin-top: 16px;
  display: flex;
  justify-content: flex-end;
}

.message-content {
  white-space: pre-wrap;
  line-height: 1.6;
}

.reply-content {
  white-space: pre-wrap;
  line-height: 1.6;
  color: #409eff;
}

.reply-info p {
  margin: 4px 0;
  color: #606266;
  font-size: 14px;
}
</style>
