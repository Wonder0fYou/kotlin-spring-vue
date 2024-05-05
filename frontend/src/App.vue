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
    <footer>
      ©Wonder0fYou, 2024
    </footer>
  </div>
</template>

<style scoped>
#app {
  font-family: 'Roboto', sans-serif; /* Modern and clean font */
  display: flex;
  flex-direction: column;
  min-height: 100vh;
  background-color: #f8f9fa; /* Light background for the app */
}

/* Navbar styles */
.navbar {
  background-color: #212529 !important; /* Darker background for navbar */
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.2); /* More pronounced shadow */
}

.nav-link {
  color: #e2e6ea !important; /* Lighter text color for nav links */
  font-weight: 500;
  padding: 1rem 1.5rem; /* Increased padding for better spacing */
  border-radius: 5px; /* Slight rounded corners for links */
  transition: all 0.3s ease; /* Smoother transition with all properties */
}

.nav-link:hover {
  background-color: #343a40; /* Darker background on hover */
  color: #fff;
  transform: translateY(-2px); /* Subtle hover effect with slight upward movement */
}

/* Container styles */
.container {
  flex: 1;
  padding: 2rem;
}

/* Content styles */
h1, h2, h3 {
  font-weight: 500; /* Slightly lighter font weight for headings */
  color: #343a40;
}

p {
  line-height: 1.8; /* Increased line height for better readability */
  color: #495057;
}

/* Footer with copyright */
footer {
  background-color: #343a40;
  color: #f8f9fa;
  text-align: center;
  padding: 1rem;
  font-size: 0.8rem; /* Smaller font size for footer text */
}
</style>
