import http from '../http-common'

class CustomerDataService {
    getAll() {
        return http.get('/device')
    }

    get(id) {
        return http.get(`/device/${id}`)
    }

    create(data) {
        return http.post('/device', data)
    }

    update(id, data) {
        return http.put(`/device/${id}`, data)
    }

    delete(id) {
        return http.delete(`/device/${id}`)
    }

}

export default new CustomerDataService()