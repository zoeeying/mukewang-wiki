<template>
  <a-layout style="padding: 24px 0; background: #fff">
    <a-layout-sider width="200" style="background: #fff">
      <a-menu
          mode="inline"
          v-model:selectedKeys="selectedKeys2"
          v-model:openKeys="openKeys"
          style="height: 100%"
      >
        <a-sub-menu key="sub1">
          <template #title>
              <span>
                <user-outlined />
                subnav 1
              </span>
          </template>
          <a-menu-item key="1">option1</a-menu-item>
          <a-menu-item key="2">option2</a-menu-item>
          <a-menu-item key="3">option3</a-menu-item>
          <a-menu-item key="4">option4</a-menu-item>
        </a-sub-menu>
        <a-sub-menu key="sub2">
          <template #title>
              <span>
                <laptop-outlined />
                subnav 2
              </span>
          </template>
          <a-menu-item key="5">option5</a-menu-item>
          <a-menu-item key="6">option6</a-menu-item>
          <a-menu-item key="7">option7</a-menu-item>
          <a-menu-item key="8">option8</a-menu-item>
        </a-sub-menu>
        <a-sub-menu key="sub3">
          <template #title>
              <span>
                <notification-outlined />
                subnav 3
              </span>
          </template>
          <a-menu-item key="9">option9</a-menu-item>
          <a-menu-item key="10">option10</a-menu-item>
          <a-menu-item key="11">option11</a-menu-item>
          <a-menu-item key="12">option12</a-menu-item>
        </a-sub-menu>
      </a-menu>
    </a-layout-sider>
    <a-layout-content :style="{ padding: '0 24px', minHeight: '280px' }">
      Content
    </a-layout-content>
  </a-layout>
</template>

<script lang="ts">
  import { defineComponent, onMounted, ref, reactive, toRef } from 'vue'
  import axios from 'axios'

  export default defineComponent({
    name: 'Home',
    setup() {
      // 使用ref和reactive实现数据绑定
      const ebooks = ref() // 响应式变量，最后需要return出去
      const ebooks2 = reactive({ books: [] })

      onMounted(() => {
        axios.get('http://localhost:8080/ebook/list?name=入门').then(res => {
          ebooks.value = res.data.content
          ebooks2.books = res.data.content
        })
      })

      return {
        ebooks,
        ebooks2: toRef(ebooks2, 'books') // 转换成响应式变量
      }
    }
  })
</script>
