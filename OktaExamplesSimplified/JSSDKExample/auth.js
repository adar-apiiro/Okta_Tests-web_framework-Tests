import { OktaAuth } from '@okta/okta-auth-js';

const oktaAuth = new OktaAuth({
  issuer: 'https://{yourOktaDomain}/oauth2/default',
  clientId: '{yourClientId}',
  redirectUri: window.location.origin + '/callback'
});

document.getElementById('login').addEventListener('click', () => {
  signInWithOkta('user@example.com', 'password');
});

async function signInWithOkta(username, password) {
  try {
    const transaction = await oktaAuth.signIn({ username, password });
    if (transaction.status === 'SUCCESS') {
      oktaAuth.token.getWithRedirect({
        sessionToken: transaction.sessionToken,
        scopes: ['openid', 'email', 'profile']
      });
    } else {
      // Handle different transaction statuses
    }
  } catch (error) {
    console.error('Failed to login', error);
  }
}