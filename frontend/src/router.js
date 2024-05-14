
import { createRouter, createWebHistory } from 'vue-router'

const Router = createRouter({
    history: createWebHistory(),
    routes: [
        {
            path: '/',
            alias: '/users',
            name: 'users',
            component: () => import('@/components/UserList.vue')
        },
        {
            path: '/users/:id',
            name: 'edit-user',
            component: () => import('@/components/EditUser.vue')
        },
        {
            path: '/add-user',
            name: 'add-user',
            component: () => import('@/components/AddUser.vue')
        },
        {
            path: "/add-device",
            name: "add-device",
            component: () => import('@/components/AddDevice.vue')
        },
        {
            path: "/device-list",
            name: "device-list",
            component: () => import('@/components/DeviceList.vue')
        },
        {
            path: "/add-room",
            name: "add-room",
            component: () => import('@/components/AddRoom.vue')
        },
        {
            path: "/room-list",
            name: "room-list",
            component: () => import('@/components/RoomList.vue')
        },
        {
            path: '/room/:id',
            name: 'edit-room',
            component: () => import('@/components/EditRoom.vue')
        },
        {
            path: '/device/:id',
            name: 'edit-device',
            component: () => import('@/components/EditDevice.vue')
        }
    ]
})

export default Router

