<script>
import RoomDataService from '../services/RoomDataService'

export default {
  name: 'room-list',
  data() {
    return {
      room: []
    }
  },
  methods: {
    retrieveRoom() {
      RoomDataService.getAll()
          .then(response => {
            this.room = response.data
          })
          .catch(e => {
            alert(e)
          })
    }
  },
  mounted() {
    this.retrieveRoom()
  }
}
</script>

<template>
  <div>
    <table class="table">
      <thead>
      <tr>
        <th scope="col">Room Name</th>
        <th scope="col">Actions</th>
      </tr>
      </thead>
      <tbody v-for="(room, index) in room" :key="index">
      <tr>
        <td>{{ room.nameRoom }}</td>
        <td><a :href="'/room/' + room.id" class="btn btn-primary">Edit</a></td>
      </tr>
      </tbody>
    </table>
  </div>
</template>
