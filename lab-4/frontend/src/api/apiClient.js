import axios from "axios";
import {jwtDecode} from "jwt-decode";

const API_URL = 'http://localhost:8080/app/app'

const apiClient = axios.create({
    baseURL: API_URL,
    headers: {
        'Content-Type': 'application/json',
    },
})

export function setToken(token) {
    if (token) {
        apiClient.defaults.headers['Authorization'] = `Bearer ${token}`
        localStorage.setItem('token', token)
    } else {
        delete apiClient.defaults.headers['Authorization']
    }
}

export function removeToken() {
    localStorage.clear()
}

export function checkAuth() {
    const token = localStorage.getItem('token');
    console.log(token)

    return !!token;
}

export function getId(token) {
    return jwtDecode(token).id
}
export default apiClient