<script>
import UsersDataService from '../services/UsersDataService'

export default {
  name: 'edit-user',
  data() {
    return {
      currentUser: null,
      message: ''
    }
  },
  methods: {
    getUser(id) {
      UsersDataService.get(id)
          .then(response => {
            this.currentUser = response.data
          })
          .catch(e => {
            alert(e)
          })
    },
    updateUser() {
      UsersDataService.update(this.currentUser.id, this.currentUser)
          .then(() => {
            this.message = 'The user was updated successfully!'
          })
          .catch(e => {
            alert(e)
          })
    },
    deleteUser() {
      UsersDataService.delete(this.currentUser.id)
          .then(() => {
            this.$router.push({name: 'users'})
          })
          .catch(e => {
            alert(e)
          })
    }
  },
  mounted() {
    this.getUser(this.$router.params.id)
  }
}
</script>

<template>
  <div v-if="currentUser">
    <div class="mb-3">
      <label for="userName" class="form-label">User Name</label>
      <input type="text" class="form-control" id="firstName" required name="firstName" v-model="currentUser.name">
    </div>
    <div class="mb-3">
      <label for="userSurname" class="form-label">User Surname</label>
      <input type="text" class="form-control" id="lastName" required name="lastName" v-model="currentUser.surname">
    </div>
    <div class="mb-3">
      <label for="email" class="form-label">User Email</label>
      <input type="email" class="form-control" id="email" required name="email" v-model="currentUser.email">
    </div>
    <div class="mb-3">
      <button @click="updateUser" class="btn btn-primary me-3">Update</button>
      <button @click="deleteUser" class="btn btn-danger">Delete</button>
    </div>
    <div class="alert alert-success" role="alert" v-if="message">
      {{message}}
    </div>
  </div>
</template>

