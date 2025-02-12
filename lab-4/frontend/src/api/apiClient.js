import axios from "axios";
import {jwtDecode} from "jwt-decode";

const API_URL = 'http://localhost:8080/app/app'

const apiClient = axios.create({
    baseURL: API_URL,
    headers: {
        'Content-Type': 'application/json',
    },
})

export function setToken() {
    console.log('start set token')
        let token = localStorage.getItem('token')
    if (token) {
        apiClient.defaults.headers['Authorization'] = `Bearer ${token}`
        console.log('token set')
    } else {
        delete apiClient.defaults.headers['Authorization']
        console.log('token don"t set')
    }
}

export function removeToken() {
    localStorage.clear()
    setToken()
}

export function checkAuth() {
    const token = localStorage.getItem('token');
    setToken()
    return !!token;
}

export function getId(token) {
    return jwtDecode(token).id
}
export default apiClient