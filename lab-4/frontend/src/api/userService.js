import apiClient, { setToken, removeToken } from "@/api/apiClient.js";

async function handleRequest(endpoint, user) {
    try {
        const response = await apiClient.post(endpoint, user);
        const token = response.data.data;

        console.log(response.data);

        setToken(token);

        return response;
    } catch (error) {
        if (error.response) {
            throw new Error(error.response.data);
        } else {
            throw new Error('Ошибка: ' + error.message);
        }
    }
}

export async function logIn(user) {
    return handleRequest('authorization/login', user);
}

export async function registration(user) {
    return handleRequest('authorization/reg', user);
}

export function logOut() {
    removeToken();
}
