import apiClient from "@/api/apiClient.js";

export function getPoint() {
    return apiClient.get('point/get')
}

export function addPoint(point) {
    return apiClient.post('point/add', point)
}

export function deletePoints() {
    return apiClient.delete('point/delete')
}
