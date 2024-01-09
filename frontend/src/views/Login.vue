<template>
    <div class="login-container">
      <img class="logo" src="../assets/img/Logo.png" alt="Logo">
      <form @submit.prevent="login">
        <div class="form-group">
          <label for="username">Username:</label>
          <input type="text" id="username" v-model="credentials.username" required>
        </div>
        <div class="form-group">
          <label for="password">Password:</label>
          <input type="password" id="password" v-model="credentials.password" required>
        </div>
        <button type="submit">Login</button>
        <p v-if="loginError" style="color: red;">Usuario o contraseña incorrectos.</p>
      </form>
    </div>
  </template>
  
  <script>
  import axios from 'axios';
  
  export default {
    data() {
      return {
        credentials: {
          username: '',
          password: ''
        },
        loginError: false
      }
    },
    methods: {
      login() {
        axios.post('http://localhost:8080/api/usuarios/login', this.credentials)
          .then(response => {
            this.$router.push('/solicitudes');
          })
          .catch(error => {
            if (error.response && error.response.status === 401) {
              this.loginError = true;
            } else {
              console.error('Error durante el login:', error);
            }
          });
      }
    }
  }
  </script>
  
  <style>
  .login-container {
    max-width: 400px;
    margin: 2rem auto;
    padding: 2rem;
    border-radius: 8px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    background-color: white;
  }
  
 .logo {
  width: 28vw;
  align-items: center;
 }
  
  .form-group {
    margin-bottom: 1rem;
  }
  
  .form-group label {
    display: block;
    margin-bottom: 0.5rem;
    font-weight: bold;
    color: #555;
  }
  
  .form-group input {
    width: 100%;
    padding: 10px;
    border: 1px solid #ddd;
    border-radius: 4px;
    box-sizing: border-box;
  }
  
  button {
    width: 100%;
    padding: 10px;
    border: none;
    background-color: #EF117A;
    color: white;
    border-radius: 4px;
    cursor: pointer;
    font-weight: bold;
    font-size: 1rem;
  }
  
  button:hover {
    background-color: #EF117A;
  }
  
  p {
    margin-top: 1rem;
    text-align: center;
  }
  
  .login-error {
    color: red;
    font-weight: bold;
  }
  </style>
  
  