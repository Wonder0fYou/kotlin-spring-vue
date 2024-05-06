<script>
import UsersDataService from '../services/UsersDataService'

export default {
  name: 'users-list',
  data() {
    return {
      users: []
    }
  },
  methods: {
    retrieveUsers() {
      UsersDataService.getAll()
          .then(response => {
            this.users = response.data
          })
          .catch(e => {
            alert(e)
          })
    }
  },
  mounted() {
    this.retrieveUsers()
  }
}
</script>

<template>
  <div>
    <table class="table">
      <thead>
      <tr>
        <th scope="col">User Name</th>
        <th scope="col">User Surname</th>
        <th scope="col">User Email</th>
        <th scope="col">Actions</th>
      </tr>
      </thead>
      <tbody v-for="(user, index) in users" :key="index">
      <tr>
        <td>{{ user.name }}</td>
        <td>{{ user.surname }}</td>
        <td>{{ user.email }}</td>
        <td><a :href="'/users/' + user.id" class="btn btn-primary">Edit</a></td>
      </tr>
      </tbody>
    </table>
  </div>
</template>

<style scoped>
.table {
  width: 100%;
  border-collapse: collapse;
}

th, td {
  border: 1px solid #ccc;
  padding: 8px;
  text-align: left;
}

th {
  background-color: #f2f2f2;
}

tbody tr {
  border-bottom: 2px solid #eee;
}
</style>
