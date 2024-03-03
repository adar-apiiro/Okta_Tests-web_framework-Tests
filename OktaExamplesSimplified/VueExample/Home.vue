<template>
  <div class="home">
    <h1>Welcome to the Vue Okta Example</h1>
    <button @click="login">Login</button>
  </div>
</template>

<script>
export default {
  methods: {
    login() {
      this.$auth.loginRedirect('/profile');
    }
  }
}
</script>