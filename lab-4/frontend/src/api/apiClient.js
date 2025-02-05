import axios from "axios";

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
    } else {
        delete apiClient.defaults.headers['Authorization']
    }
}

export function removeToken() {
    setToken(null)
}

export function checkAuth() {
    const token = localStorage.getItem('token')

    if (!token) {
        return false
    }
}
export default apiClient