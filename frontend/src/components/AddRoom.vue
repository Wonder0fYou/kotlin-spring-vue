<template>
  <div>
    <div v-if="!submitted">
      <div class="mb-3">
        <label for="nameRoom" class="form-label">Name Room</label>
        <input type="text" class="form-control" id="nameRoom" required name="nameRoom" v-model="room.nameRoom">
      </div>
      <div class="mb-3">
        <button @click="saveRoom" class="btn btn-primary">Submit</button>
      </div>
    </div>
    <div v-else>
      <div class="alert alert-success" role="alert">
        Save room successfully!
      </div>
      <button @click="newRoom" class="btn btn-primary">Add New Room</button>
    </div>
  </div>
</template>

<script>
import RoomDataService from '../services/RoomDataService'
export default {
  name: 'add-room',
  data() {
    return {
      room: {
        id: null,
        nameRoom: "",
      },
      submitted: false
    }
  },
  methods: {
    saveRoom() {
      var data = {
        nameRoom: this.room.nameRoom,
      };
      RoomDataService.create(data)
          .then(response => {
            this.room.id = response.data.id
            this.submitted = true;
          })
          .catch( e => {
            alert(e)
          })
    },
    newRoom() {
      this.submitted = false
      this.room = {}
    }
  }
}
</script>

<style scoped>
/* Basic form styling */
.form-label {
  font-weight: bold;
}

.form-control {
  padding: 8px;
  border: 1px solid #ccc;
  border-radius: 4px;
}

/* Spacing between form elements */
.mb-3 {
  margin-bottom: 16px;
}

/* Button styling */
.btn {
  padding: 10px 15px;
  background-color: #0d6efd; /* Primary blue color */
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

.btn:hover {
  background-color: #0b5ed7; /* Darker blue on hover */
}

/* Success alert styling */
.alert-success {
  background-color: #d4edda;
  border-color: #c3e6cb;
  color: #155724;
  padding: 10px;
  margin-bottom: 16px;
  border-radius: 4px;
}
</style>