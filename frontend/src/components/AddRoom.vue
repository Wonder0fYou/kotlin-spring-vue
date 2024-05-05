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