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
        <label for="userId" class="form-label">User Id</label>
        <input type="number" class="form-control" id="userId" required name="userId" v-model="device.userId">
      </div>
      <div class="mb-3">
        <label for="roomId" class="form-label">Room Id</label>
        <input type="number" class="form-control" id="roomId" required name="roomId" v-model="device.roomId">
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
export default {
  name: 'add-device',
  data() {
    return {
      device: {
        id: null,
        model: "",
        manufacturer: "",
        serialNumber: null,
        pagePerMinute: null,
        dateOfReceipt: Date(),
        dateOfCommissioning: Date(),
        userId: null,
        roomId: null
      },
      submitted: false
    }
  },
  methods: {
    saveDevice() {
      var data = {
        model: this.device.model,
        manufacturer: this.device.manufacturer,
        serialNumber: this.device.serialNumber,
        pagePerMinute: this.device.pagePerMinute,
        dateOfReceipt: this.device.dateOfReceipt,
        dateOfCommissioning: this.device.dateOfCommissioning,
        userId: this.device.userId,
        roomId: this.device.roomId
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