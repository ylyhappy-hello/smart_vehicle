<script setup lang="ts">
import { getAccessToken } from '@/utils/auth';


</script>
<template>
{{ "Bearer " + getAccessToken() }}
</template>
<style scoped lang="scss">

</style>
