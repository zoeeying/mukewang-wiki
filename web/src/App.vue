<template>
  <a-layout>
    <wiki-header />
    <router-view />
    <wiki-footer />
  </a-layout>
</template>

<script lang="ts">
  import { defineComponent } from 'vue'
  import WikiHeader from '@/components/wiki-header.vue'
  import WikiFooter from '@/components/wiki-footer.vue'

  export default defineComponent({
    name: 'App',
    components: {
      WikiHeader,
      WikiFooter
    }
  })
</script>
