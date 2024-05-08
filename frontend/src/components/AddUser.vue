<template>
  <div>
    <div v-if="!submitted">
      <div class="mb-3">
        <label for="userFio" class="form-label">User FIO</label>
        <input type="text" class="form-control" id="fio" required name="fio" v-model="user.fio">
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
        fio: "",
        email: ""
      },
      submitted: false
    }
  },
  methods: {
    saveUser() {
      var data = {
        fio: this.user.fio,
        email: this.user.email
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

<style scoped>

.form-label {
  font-weight: bold;
}

.form-control {
  padding: 8px;
  border: 1px solid #ccc;
  border-radius: 4px;
}

.mb-3 {
  margin-bottom: 16px;
}

.btn {
  padding: 10px 15px;
  background-color: #0d6efd;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

.btn:hover {
  background-color: #0b5ed7;
}

.alert-success {
  background-color: #d4edda;
  border-color: #c3e6cb;
  color: #155724;
  padding: 10px;
  margin-bottom: 16px;
  border-radius: 4px;
}
</style>