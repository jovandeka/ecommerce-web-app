import { reactive } from 'vue';

export const store = reactive({
  token: localStorage.getItem('token') || null,
  setToken(newToken) {
    this.token = newToken;
    localStorage.setItem('token', newToken);
  },
  clearToken() {
    this.token = null;
    localStorage.removeItem('token');
  }
});