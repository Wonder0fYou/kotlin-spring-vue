<script>
import RoomDataService from '../services/RoomDataService'

export default {
  name: 'edit-room',
  data() {
    return {
      room: null,
      message: ''
    }
  },
  methods: {
    getRoom(id) {
      RoomDataService.get(id)
          .then(response => {
            this.room = response.data
          })
          .catch(e => {
            alert(e)
          })
    },
    updateRoom() {
      RoomDataService.update(this.room.id, this.room)
          .then(() => {
            this.message = 'The room was updated successfully!'
          })
          .catch(e => {
            alert(e)
          })
    },
    deleteRoom() {
      RoomDataService.delete(this.room.id)
          .then(() => {
            this.$router.push({name: 'room'})
          })
          .catch(e => {
            alert(e)
          })
    }
  },
  mounted() {
    this.$nextTick(() => {
      this.getRoom(this.$route.params.id);
    });
  }
}
</script>

<template>
  <div v-if="room">
    <div class="mb-3">
      <label for="nameRoom" class="form-label">Room Name</label>
      <input type="text" class="form-control" id="nameRoom" required name="nameRoom" v-model="room.name">
    </div>
    <div class="mb-3">
      <button @click="updateRoom" class="btn btn-primary me-3">Update</button>
      <button @click="deleteRoom" class="btn btn-danger">Delete</button>
    </div>
    <div class="alert alert-success" role="alert" v-if="message">
      {{message}}
    </div>
  </div>
</template>

<style scoped>
.edit-room {
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

