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
  border-bottom: 2px solid #eee; /* Separator for data rows */
}
</style>
