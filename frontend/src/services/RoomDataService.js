import http from '../http-common'

class CustomerDataService {
    getAll() {
        return http.get('/room')
    }

    get(id) {
        return http.get(`/room/${id}`)
    }

    create(data) {
        return http.post('/room', data)
    }

    update(id, data) {
        return http.put(`/room/${id}`, data)
    }

    delete(id) {
        return http.delete(`/room/${id}`)
    }
}

export default new CustomerDataService()