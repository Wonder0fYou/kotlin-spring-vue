<template>
  <div>
    <div v-if="!submitted">
      <div class="mb-3">
        <label for="userName" class="form-label">User Name</label>
        <input type="text" class="form-control" id="firstName" required name="firstName" v-model="user.name">
      </div>
      <div class="mb-3">
        <label for="userSurname" class="form-label">User Surname</label>
        <input type="text" class="form-control" id="lastName" required name="lastName" v-model="user.surname">
      </div>
      <div class="mb-3">
        <label for="email" class="form-label">User Email</label>
        <input type="email" class="form-control" id="email" required name="email" v-model="user.email">
      </div>
      <div class="mb-3">
        <button @click="saveUser" class="btn btn-primary">Submit</button>
      </div>
    </div>
    <div v-else>
      <div class="alert alert-success" role="alert">
        Save user successfully!
      </div>
      <button @click="newUser" class="btn btn-primary">Add New User</button>
    </div>
  </div>
</template>

<script>
import UsersDataService from '../services/UsersDataService'
export default {
  name: 'add-user',
  data() {
    return {
      user: {
        id: null,
        Name: "",
        Surname: "",
        email: "",
      },
      submitted: false
    }
  },
  methods: {
    saveUser() {
      var data = {
        name: this.user.name,
        surname: this.user.surname,
        email: this.user.email,
      };
      UsersDataService.create(data)
          .then(response => {
            this.user.id = response.data.id
            this.submitted = true;
          })
          .catch( e => {
            alert(e)
          })
    },
    newUser() {
      this.submitted = false
      this.user = {}
    }
  }
}
</script>