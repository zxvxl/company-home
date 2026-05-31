<template>
  <form class="contact-form" @submit.prevent="handleSubmit">
    <h3 class="contact-form__title">在线留言</h3>
    <p class="contact-form__subtitle">请填写以下信息，我们将尽快与您联系</p>

    <div class="contact-form__row">
      <div class="contact-form__field">
        <label class="contact-form__label">姓名 <span class="required">*</span></label>
        <input
          v-model="form.name"
          type="text"
          class="contact-form__input"
          placeholder="请输入您的姓名"
          :class="{ 'contact-form__input--error': errors.name }"
        />
        <span class="contact-form__error" v-if="errors.name">{{ errors.name }}</span>
      </div>
      <div class="contact-form__field">
        <label class="contact-form__label">电话 <span class="required">*</span></label>
        <input
          v-model="form.phone"
          type="tel"
          class="contact-form__input"
          placeholder="请输入联系电话"
          :class="{ 'contact-form__input--error': errors.phone }"
        />
        <span class="contact-form__error" v-if="errors.phone">{{ errors.phone }}</span>
      </div>
    </div>

    <div class="contact-form__row">
      <div class="contact-form__field">
        <label class="contact-form__label">邮箱</label>
        <input
          v-model="form.email"
          type="email"
          class="contact-form__input"
          placeholder="请输入邮箱地址"
          :class="{ 'contact-form__input--error': errors.email }"
        />
        <span class="contact-form__error" v-if="errors.email">{{ errors.email }}</span>
      </div>
      <div class="contact-form__field">
        <label class="contact-form__label">公司名称</label>
        <input
          v-model="form.company"
          type="text"
          class="contact-form__input"
          placeholder="请输入公司名称"
        />
      </div>
    </div>

    <div class="contact-form__field">
      <label class="contact-form__label">主题 <span class="required">*</span></label>
      <input
        v-model="form.subject"
        type="text"
        class="contact-form__input"
        placeholder="请输入留言主题"
        :class="{ 'contact-form__input--error': errors.subject }"
      />
      <span class="contact-form__error" v-if="errors.subject">{{ errors.subject }}</span>
    </div>

    <div class="contact-form__field">
      <label class="contact-form__label">留言内容 <span class="required">*</span></label>
      <textarea
        v-model="form.content"
        class="contact-form__textarea"
        rows="5"
        placeholder="请详细描述您的需求或问题"
        :class="{ 'contact-form__input--error': errors.content }"
      ></textarea>
      <span class="contact-form__error" v-if="errors.content">{{ errors.content }}</span>
    </div>

    <button type="submit" class="btn btn--primary btn--lg contact-form__submit" :disabled="submitting">
      {{ submitting ? '提交中...' : '提交留言' }}
    </button>

    <!-- 成功提示 -->
    <Transition name="fade">
      <div v-if="submitSuccess" class="contact-form__success">
        <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" class="contact-form__success-icon">
          <circle cx="12" cy="12" r="10"/>
          <path d="M8 12l2.5 2.5L16 9"/>
        </svg>
        <span>留言提交成功！我们将尽快与您联系。</span>
      </div>
    </Transition>
  </form>
</template>

<script setup lang="ts">
const { submitMessage } = useApi()

const form = reactive({
  name: '',
  phone: '',
  email: '',
  company: '',
  subject: '',
  content: ''
})

const errors = reactive<Record<string, string>>({})
const submitting = ref(false)
const submitSuccess = ref(false)

const validate = () => {
  const newErrors: Record<string, string> = {}

  if (!form.name.trim()) {
    newErrors.name = '请输入姓名'
  }

  if (!form.phone.trim()) {
    newErrors.phone = '请输入联系电话'
  } else if (!/^1[3-9]\d{9}$/.test(form.phone) && !/^0\d{2,3}-?\d{7,8}$/.test(form.phone)) {
    newErrors.phone = '请输入正确的电话号码'
  }

  if (form.email && !/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(form.email)) {
    newErrors.email = '请输入正确的邮箱地址'
  }

  if (!form.subject.trim()) {
    newErrors.subject = '请输入留言主题'
  }

  if (!form.content.trim()) {
    newErrors.content = '请输入留言内容'
  }

  Object.keys(errors).forEach(key => delete errors[key])
  Object.assign(errors, newErrors)

  return Object.keys(newErrors).length === 0
}

const handleSubmit = async () => {
  if (!validate()) return

  submitting.value = true
  submitSuccess.value = false

  try {
    await submitMessage({
      name: form.name,
      phone: form.phone,
      email: form.email,
      company: form.company,
      subject: form.subject,
      content: form.content
    })

    submitSuccess.value = true
    // 重置表单
    form.name = ''
    form.phone = ''
    form.email = ''
    form.company = ''
    form.subject = ''
    form.content = ''

    setTimeout(() => {
      submitSuccess.value = false
    }, 5000)
  } catch (error) {
    alert('提交失败，请稍后重试')
  } finally {
    submitting.value = false
  }
}
</script>

<style scoped>
.contact-form {
  background: var(--color-white);
  border-radius: var(--radius-lg);
  padding: var(--spacing-2xl);
  box-shadow: var(--shadow-md);
}

.contact-form__title {
  font-size: var(--font-size-2xl);
  font-weight: 600;
  color: var(--color-text);
  margin-bottom: var(--spacing-sm);
}

.contact-form__subtitle {
  font-size: var(--font-size-sm);
  color: var(--color-text-light);
  margin-bottom: var(--spacing-xl);
}

.contact-form__row {
  display: grid;
  grid-template-columns: 1fr 1fr;
  gap: var(--spacing-lg);
}

.contact-form__field {
  margin-bottom: var(--spacing-lg);
}

.contact-form__label {
  display: block;
  font-size: var(--font-size-sm);
  font-weight: 500;
  color: var(--color-text);
  margin-bottom: var(--spacing-sm);
}

.required {
  color: #e74c3c;
}

.contact-form__input,
.contact-form__textarea {
  width: 100%;
  padding: 0.75rem 1rem;
  border: 1px solid var(--color-border);
  border-radius: var(--radius-md);
  font-size: var(--font-size-base);
  color: var(--color-text);
  transition: all var(--transition-fast);
  background: var(--color-bg);
}

.contact-form__input:focus,
.contact-form__textarea:focus {
  border-color: var(--color-primary-light);
  box-shadow: 0 0 0 3px rgba(41, 128, 185, 0.1);
  background: var(--color-white);
}

.contact-form__input--error {
  border-color: #e74c3c;
}

.contact-form__textarea {
  resize: vertical;
  min-height: 120px;
}

.contact-form__error {
  display: block;
  font-size: var(--font-size-xs);
  color: #e74c3c;
  margin-top: var(--spacing-xs);
}

.contact-form__submit {
  width: 100%;
  margin-top: var(--spacing-md);
}

.contact-form__submit:disabled {
  opacity: 0.7;
  cursor: not-allowed;
}

.contact-form__success {
  display: flex;
  align-items: center;
  gap: var(--spacing-sm);
  margin-top: var(--spacing-lg);
  padding: var(--spacing-md) var(--spacing-lg);
  background: #d4edda;
  color: #155724;
  border-radius: var(--radius-md);
  font-size: var(--font-size-sm);
}

.contact-form__success-icon {
  width: 20px;
  height: 20px;
  flex-shrink: 0;
  color: #28a745;
}

.fade-enter-active,
.fade-leave-active {
  transition: opacity var(--transition-normal);
}

.fade-enter-from,
.fade-leave-to {
  opacity: 0;
}

@media (max-width: 768px) {
  .contact-form__row {
    grid-template-columns: 1fr;
  }

  .contact-form {
    padding: var(--spacing-xl);
  }
}
</style>
