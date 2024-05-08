<template>
  <div>
    <div v-if="!submitted">
      <div class="mb-3">
        <label for="model" class="form-label">Model</label>
        <input type="text" class="form-control" id="model" required name="model" v-model="device.model">
      </div>
      <div class="mb-3">
        <label for="manufacturer" class="form-label">Manufacturer</label>
        <input type="text" class="form-control" id="manufacturer" required name="manufacturer" v-model="device.manufacturer">
      </div>
      <div class="mb-3">
        <label for="serialNumber" class="form-label">Serial Number</label>
        <input type="number" class="form-control" id="serialNumber" required name="serialNumber" v-model="device.serialNumber">
      </div>
      <div class="mb-3">
        <label for="pagePerMinute" class="form-label">Page Per Minute</label>
        <input type="number" class="form-control" id="pagePerMinute" required name="pagePerMinute" v-model="device.pagePerMinute">
      </div>
      <div class="mb-3">
        <label for="dateOfReceipt" class="form-label">Date Of Receipt</label>
        <input type="date" class="form-control" id="dateOfReceipt" required name="dateOfReceipt" v-model="device.dateOfReceipt">
      </div>
      <div class="mb-3">
        <label for="dateOfCommissioning" class="form-label">Date Of Commissioning</label>
        <input type="date" class="form-control" id="dateOfCommissioning" required name="dateOfCommissioning" v-model="device.dateOfCommissioning">
      </div>
      <div class="mb-3">
        <label for="paperFormat" class="form-label">Paper Format</label>
        <select class="form-control" id="paperFormat" required name="paperFormat" v-model="device.paperFormat">
          <option value="A3">A3</option>
          <option value="A4">A4</option>
        </select>
      </div>
      <div class="mb-3">
        <label for="printColor" class="form-label">Print Color</label>
        <select class="form-control" id="printColor" required name="printColor" v-model="device.printColor">
          <option value="Цветной">Цветной</option>
          <option value="Черно-белый">Черно-белый</option>
        </select>
      </div>
      <div class="mb-3">
        <label for="midDpi" class="form-label">Min Dpi</label>
        <select class="form-control" id="midDpi" required name="midDpi" v-model="device.midDpi">
          <option value="300x300">300x300</option>
          <option value="600x480">600x480</option>
          <option value="600x600">600x600</option>
          <option value="1200x600">1200x600</option>
          <option value="1200x1200">1200x1200</option>
        </select>
      </div>
      <div class="mb-3">
        <label for="userFio" class="form-label">User Fio</label>
        <select  class="form-control" id="userFio" required name="userFio" v-model="selectedUser">
          <option v-for="(user, index) in users" :key="index" :value="user">{{user.fio}}</option>
        </select>
      </div>
      <div class="mb-3">
        <label for="nameRoom" class="form-label">Room Name</label>
        <select  class="form-control" id="nameRoom" required name="nameRoom" v-model="selectedRoom">
          <option v-for="(room, index) in room" :key="index" :value="room">{{room.nameRoom}}</option>
        </select>
      </div>
      <div class="mb-3">
        <button @click="saveDevice" class="btn btn-primary">Submit</button>
      </div>
    </div>
    <div v-else>
      <div class="alert alert-success" role="alert">
        Save device successfully!
      </div>
      <button @click="newDevice" class="btn btn-primary">Add New Device</button>
    </div>
  </div>
</template>

<script>
import DeviceDataService from '../services/DeviceDataService'
import UsersDataService from '../services/UsersDataService'
import RoomDataService from '../services/RoomDataService'
export default {
  name: 'add-device',
  data() {
    return {
      users: [],
      room: [],
      device: {
        id: null,
        model: "",
        manufacturer: "",
        serialNumber: null,
        pagePerMinute: null,
        dateOfReceipt: Date(),
        dateOfCommissioning: Date(),
        paperFormat: "",
        printColor: "",
        midDpi: "",
        userFio: "",
        roomName: ""
      },
      selectedRoom: null,
      submitted: false
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
    },
    retrieveUsers() {
      UsersDataService.getAll()
          .then(response => {
            this.users = response.data;
          })
          .catch(e => {
            alert(e);
          });
    },
    saveDevice() {
      var data = {
        model: this.device.model,
        manufacturer: this.device.manufacturer,
        serialNumber: this.device.serialNumber,
        pagePerMinute: this.device.pagePerMinute,
        dateOfReceipt: this.device.dateOfReceipt,
        dateOfCommissioning: this.device.dateOfCommissioning,
        paperFormat: this.device.paperFormat,
        printColor: this.device.printColor,
        midDpi: this.device.midDpi,
        userFio: this.selectedUser.fio,
        roomName: this.selectedRoom.nameRoom
      };
      DeviceDataService.create(data)
          .then(response => {
            this.device.id = response.data.id
            this.submitted = true;
          })
          .catch( e => {
            alert(e)
          })
    },
    newDevice() {
      this.submitted = false
      this.device = {}
      this.selectedUser = null
      this.selectedRoom = null
    }
  },
  mounted() {
    this.retrieveUsers()
    this.retrieveRoom()
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