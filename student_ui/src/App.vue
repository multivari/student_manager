<template>
  <div id="app">
    <router-view />
  </div>
</template>

<script>
export default {
  name: 'App',
  data() {
    return {
      activeIndex: '1' // 用于标识当前菜单项
    };
  }
};
</script>

