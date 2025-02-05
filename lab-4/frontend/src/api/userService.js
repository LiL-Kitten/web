import apiClient, {setToken, removeToken} from "@/api/apiClient.js";

export async function logIn(user) {
    const response = await apiClient.post('authorization/login', user)
    const token = response.data.token

    setToken(token)

    return response
}

export async function registration(user) {
    const response = await apiClient.post('authorization/login', user)
    const token = response.data.token

    setToken(token)

    return response
}

export function logOut() {
    removeToken()
}