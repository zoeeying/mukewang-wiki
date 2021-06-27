<template>
  <a-layout>
    <WikiHeader />
    <router-view />
    <WikiFooter />
  </a-layout>
</template>

<script lang="ts">
  import { defineComponent } from 'vue'
  import WikiHeader from '@/components/WikiHeader.vue'
  import WikiFooter from '@/components/WikiFooter.vue'

  export default defineComponent({
    name: 'App',
    components: {
      WikiHeader,
      WikiFooter
    }
  })
</script>
