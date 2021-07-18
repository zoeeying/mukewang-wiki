<template>
  <a-layout>
    <a-layout-content :style="{ background: '#fff', padding: '24px', margin: 0, minHeight: '280px' }">
      <a-table
        :columns="columns"
        :row-key="record => record.id"
        :data-source="ebooks"
        :pagination="pagination"
        :loading="loading"
        @change="onTableChange"
      >
        <template #cover="{ text: cover }">
          <img :src="cover" alt="avatar" v-if="cover" :style="{ width: 50 }">
        </template>
        <template v-slot:action="{ text, record }">
          <a-space size="small">
            <a-button type="primary">
              编辑
            </a-button>
            <a-button type="danger">
              删除
            </a-button>
          </a-space>
        </template>
      </a-table>
    </a-layout-content>
  </a-layout>
</template>

<script lang="ts">
  import { defineComponent, onMounted, ref } from 'vue'
  import axios from 'axios'

  export default defineComponent({
    name: 'AdminEbook',
    setup(){
      const ebooks = ref()
      const pagination = ref({
        current: 1,
        pageSize: 2,
        total: 0
      })
      const loading = ref(false)

      const columns = [
        {
          title: '封面',
          dataIndex: 'cover',
          slots: { customRender: 'cover'}
        },
        {
          title: '名称',
          dataIndex: 'name'
        },
        {
          title: '分类一',
          key: 'category1Id',
          dataIndex: 'category1Id'
        },
        {
          title: '分类二',
          dataIndex: 'category2Id'
        },
        {
          title: '文档数',
          dataIndex: 'docCount'
        },
        {
          title: '阅读数',
          dataIndex: 'viewCount'
        },
        {
          title: '点赞数',
          dataIndex: 'voteCount'
        },
        {
          title: 'Action',
          key: 'action',
          slots: { customRender: 'action'}
        }
      ]

      // 数据查询
      const onQuery = async (params: any) => {
        loading.value = true
        const res = await axios.get('/ebook/list', params) 
        loading.value = false
        const data = res.data
        ebooks.value = data.content

        // 重置分页
        pagination.value.current = params.page
      }

      // 表格页码变更
      const onTableChange = (pagination: any) => {
        onQuery({
          page: pagination.current,
          size: pagination.pageSize
        })
      }

      onMounted(() => {
        onQuery({})
      })

      return {
        ebooks,
        pagination,
        columns,
        loading,
        onTableChange
      }
    },
  })

</script>
