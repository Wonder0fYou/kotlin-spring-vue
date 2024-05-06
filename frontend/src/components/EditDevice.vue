<script>
import DeviceDataService from '../services/DeviceDataService'

export default {
  name: 'edit-device',
  data() {
    return {
      device: null,
      message: ''
    }
  },
  methods: {
    getDevice(id) {
      DeviceDataService.get(id)
          .then(response => {
            this.device = response.data
          })
          .catch(e => {
            alert(e)
          })
    },
    updateDevice() {
      DeviceDataService.update(this.device.id, this.device)
          .then(() => {
            this.message = 'The device was updated successfully!'
          })
          .catch(e => {
            alert(e)
          })
    },
    deleteDevice() {
      DeviceDataService.delete(this.device.id)
          .then(() => {
            this.$router.push({name: 'device'})
          })
          .catch(e => {
            alert(e)
          })
    }
  },
  mounted() {
    this.$nextTick(() => {
      this.getDevice(this.$route.params.id);
    });
  }
}
</script>

<template>
  <div v-if="device">
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
      <button @click="updateDevice" class="btn btn-primary me-3">Update</button>
      <button @click="deleteDevice" class="btn btn-danger">Delete</button>
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

