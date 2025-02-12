import apiClient from "@/api/apiClient.js";
import router from "@/router/index.js";

export async function getPoints() {
    try {
        return await apiClient.get('point/get')
    } catch (error) {
        if(error.response.status === 401) router.push('/')
        if (error.response) {
            throw new Error(error.response.data);
        } else {
            throw new Error('Ошибка: ' + error.message);
        }
    }
}

export function addPoint(point) {
    return apiClient.post('point/add', point)
}

export async function deletePoints() {
    try {
        return await apiClient.delete('point/delete');
    } catch (error) {
        if (error.response) {
            throw new Error(error.response.data);
        } else {
            throw new Error('Ошибка: ' + error.message);
        }
    }
}