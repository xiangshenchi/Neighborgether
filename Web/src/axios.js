// src/axios.js

import axios from 'axios';

const axiosInstance = axios.create({
  baseURL: 'http://192.168.217.70:8090', 
  timeout: 3000,
});

export default axiosInstance;
