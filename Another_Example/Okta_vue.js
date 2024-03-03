import Auth from '@okta/okta-vue';

const routes = [
{
  path: '/implicit/callback',
  component: Auth.handleCallback()
}];
  
}
