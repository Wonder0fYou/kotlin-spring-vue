<script>
export default {
  name: 'App'
}
</script>

<template>
  <div id="app">
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
      <div class="container-fluid">
        <div class="collapse navbar-collapse">
          <div class="navbar-nav">
            <router-link to="/users" class="nav-link">User List</router-link>
            <router-link to="/add-user" class="nav-link">Add New User</router-link>
            <router-link to="/device-list" class="nav-link">Device List</router-link>
            <router-link to="/add-device" class="nav-link">Add New Device</router-link>
            <router-link to="/room-list" class="nav-link">Room List</router-link>
            <router-link to="/add-room" class="nav-link">Add New Room</router-link>
          </div>
        </div>
      </div>
    </nav>
    <div class="container">
      <router-view />
    </div>
  </div>
</template>
