<template>
    <div class="container pb-5">
      <div class="row">
        <div class="col-12 text-center pt-2">
          <router-link :to="{ name: 'HomeView' }">
            <img id="logo" src="../assets/icon_black.png" />
          </router-link>
        </div>
      </div>
  
      <div class="row">
        <div class="col-12 justify-content-center d-flex flex-row pt-2">
          <div id="signin-div" class="flex-item border">
            <h2 class="pt-4">Sign In</h2>
            <form @submit="signin" class="pt-2 pl-4 pr-4">
              <div class="form-group">
                <label>Email</label>
                <input
                  type="email"
                  class="form-control"
                  v-model="email"
                  required
                />
              </div>
              <div class="form-group">
                <label>Password</label>
                <input
                  type="password"
                  class="form-control"
                  v-model="password"
                  required
                />
              </div>
              <small class="form-text text-muted" >By continuing, you agree to SDžP's Conditions of Use and Privacy Notice.</small>
              <button type="submit" class="btn btn-primary mt-2 px-4 py-1">
                Continue
                <div
                  v-if="loading"
                  class="spinner-border spinner-border-sm"
                  role="status">
                  <span class="sr-only">Loading...</span>
                </div>
              </button>
            </form>
            <hr />
            <small class="form-text text-muted p-1 text-center">New to SDžP?</small>
            <p class="text-center">
              <router-link :to="{ name: 'Signup' }" class="btn btn-dark text-center mx-auto px-5 py-1 mb-2">Create Your SDžP Account</router-link>
            </p>
          </div>
        </div>
      </div>
    </div>
  </template>
  
  <script>
  import axios from 'axios';
  import swal from 'sweetalert';
  import { store } from '../StoreToken.js';
  export default {
    // eslint-disable-next-line vue/multi-word-component-names
    name: "Signin",
    props: ["baseURL"],
    data() {
      return {
        email: null,
        password: null,
        loading: null,
      };
    },
    methods: {
      async signin(e) {
        e.preventDefault();
        this.loading = true;
  
        const user = {
          email: this.email,
          password: this.password,
        };
  
        await axios
          .post(`${this.baseURL}/user/signin`, user)
          .then((res) => {
            store.setToken(res.data.token);
            this.$router.push({ name: "HomeView" });
          })
          .catch((err) => {
            swal({
              text: "Unable to Log you in!",
              icon: "error",
              closeOnClickOutside: false,
            });
            console.log(err);
          })
          .finally(() => {
            this.loading = false;
          });
      },
    },
    mounted() {
      this.loading = false;
    },
  };
  </script>
  
  <style scoped>
  .btn-dark {
    background-color: #e7e9ec;
    color: #000;
    font-size: smaller;
    border-radius: 0;
    border-color: #adb1b8 #a2a6ac #a2a6ac;
  }
  
  #logo {
    width: 150px;
  }
  
  @media only screen and (min-width: 992px) {
    #signin-div {
      width: 40%;
    }
  }
  </style>