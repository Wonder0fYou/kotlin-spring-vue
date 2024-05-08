<script>
import UsersDataService from '../services/UsersDataService'

export default {
  name: 'edit-user',
  data() {
    return {
      user: null,
      message: ''
    }
  },
  methods: {
    getUser(id) {
      UsersDataService.get(id)
          .then(response => {
            this.user = response.data
          })
          .catch(e => {
            alert(e)
          })
    },
    updateUser() {
      UsersDataService.update(this.user.id, this.user)
          .then(() => {
            this.message = 'The user was updated successfully!'
          })
          .catch(e => {
            alert(e)
          })
    },
    deleteUser() {
      UsersDataService.delete(this.user.id)
          .then(() => {
            this.$router.push({name: 'users'})
          })
          .catch(e => {
            alert(e)
          })
    }
  },
  mounted() {
    this.$nextTick(() => {
      this.getUser(this.$route.params.id);
    });
  }
}
</script>

<template>
  <div v-if="user">
    <div class="mb-3">
      <label for="userFio" class="form-label">User FIO</label>
      <input type="text" class="form-control" id="fio" required name="fio" v-model="user.fio">
    </div>
    <div class="mb-3">
      <label for="email" class="form-label">User Email</label>
      <input type="text" class="form-control" id="email" required name="email" v-model="user.email">
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

<style scoped>
.edit-user {
  width: 400px;
  margin: 20px auto;
  padding: 20px;
  border: 1px solid #ccc;
  border-radius: 5px;
}

.edit-user h2 {
  text-align: center;
  margin-bottom: 20px;
}


.form-group {
  margin-bottom: 15px;
}

.form-label {
  display: block;
  margin-bottom: 5px;
  font-weight: bold;
}

.form-control {
  width: 100%;
  padding: 8px;
  border: 1px solid #ccc;
  border-radius: 3px;
}

.btn {
  padding: 8px 15px;
  border: none;
  border-radius: 3px;
  cursor: pointer;
  transition: background-color 0.3s;
}

.btn-primary {
  background-color: #007bff;
  color: #fff;
}

.btn-primary:hover {
  background-color: #0062cc;
}

.btn-danger {
  background-color: #dc3545;
  color: #fff;
}

.btn-danger:hover {
  background-color: #c82333;
}

.alert-success {
  background-color: #d4edda;
  color: #155724;
  border-color: #c3e6cb;
  padding: 10px;
  margin-top: 10px;
  border-radius: 3px;
}
</style>

