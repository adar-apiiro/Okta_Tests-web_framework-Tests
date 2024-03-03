import Vue from 'vue'
import App from './App.vue'
import router from './router'
import Auth from '@okta/okta-vue'

Vue.use(Auth, {
  issuer: 'https://{yourOktaDomain}/oauth2/default',
  clientId: '{yourClientId}',
  redirectUri: window.location.origin + '/callback',
  scopes: ['openid', 'profile', 'email']
})

router.beforeEach(Vue.prototype.$auth.authRedirectGuard())

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')