import { createApp } from 'vue'
import { OktaAuth } from '@okta/okta-auth-js'
import OktaVue from '@okta/okta-vue'

const oktaAuth = new OktaAuth({
  issuer: 'https://{yourOktaDomain}/oauth2/default',
  clientId: '{clientId}',
  redirectUri: window.location.origin + '/login/callback',
  scopes: ['openid', 'profile', 'email']
})

const app = createApp(App)
app.use(OktaVue, { oktaAuth })
app.mount('#app')
