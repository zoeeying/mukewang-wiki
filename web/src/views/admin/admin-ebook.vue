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
          <img :src="cover" alt="avatar" v-if="cover" :style="{ width: '50px' }">
        </template>
        <template v-slot:action="{ record }">
          <a-space size="small">
            <a-button type="primary" @click="edit(record)">
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
  <a-modal title="电子书表单" v-model:visible="modalVisible" :confirmLoading="modalLoading" @ok="onModalOk">
    <a-form :model="ebook" :label-col="{ span: 6 }">
      <a-form-item label="封面">
        <a-input v-model:value="ebook.cover" />
      </a-form-item>
      <a-form-item label="名称">
        <a-input v-model:value="ebook.name" />
      </a-form-item>
      <a-form-item label="分类一">
        <a-input v-model:value="ebook.category1Id" />
      </a-form-item>
      <a-form-item label="分类二">
        <a-input v-model:value="ebook.category2Id" />
      </a-form-item>
      <a-form-item label="描述">
        <a-input v-model:value="ebook.desc" type="text" />
      </a-form-item>
    </a-form>
  </a-modal>
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
        pageSize: 4,
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
        const res = await axios.get('/ebook/list', { params }) 
        loading.value = false
        ebooks.value = res.data.content.list

        // 重置分页
        pagination.value.current = params.page
        pagination.value.total = res.data.content.total
      }

      // 表格页码变更
      const onTableChange = (pagination: any) => {
        onQuery({
          page: pagination.current,
          size: pagination.pageSize
        })
      }
       
      const modalVisible = ref(false)
      const modalLoading = ref(false)
      const ebook = ref({})

      const onModalOk = () => {
        modalLoading.value = true
        axios.post("ebook/save", ebook.value).then(res => {
          const data = res.data
          if(data.success) {
            modalVisible.value = false
            modalLoading.value = false
             onQuery({
              page: pagination.value.current,
              size: pagination.value.pageSize
            })
          }
        })
      }

      const edit = (record: any) => {
        modalVisible.value = true
        ebook.value = record
      }

      onMounted(() => {
        onQuery({
          page: 1,
          size: pagination.value.pageSize
        })
      })

      return {
        ebooks,
        pagination,
        columns,
        loading,
        edit,
        ebook,
        modalVisible,
        modalLoading,
        onModalOk,
        onTableChange
      }
    },
  })

</script>
