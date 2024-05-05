<script>
import DeviceDataService from '../services/DeviceDataService'

export default {
  name: 'device-list',
  data() {
    return {
      device: []
    }
  },
  methods: {
    retrieveDevice() {
      DeviceDataService.getAll()
          .then(response => {
            this.device = response.data
          })
          .catch(e => {
            alert(e)
          })
    }
  },
  mounted() {
    this.retrieveDevice()
  }
}
</script>

<template>
  <div>
    <table class="table">
      <thead>
      <tr>
        <th scope="col">Model</th>
        <th scope="col">Manufacturer</th>
        <th scope="col">Serial Number</th>
        <th scope="col">Page Per Minute</th>
        <th scope="col">Date Of Receipt</th>
        <th scope="col">Date Of Commissioning</th>
        <th scope="col">User Id</th>
        <th scope="col">Room Id</th>
        <th scope="col">Actions</th>
      </tr>
      </thead>
      <tbody v-for="(device, index) in device" :key="index">
      <tr>
        <td>{{ device.model }}</td>
        <td>{{ device.manufacturer }}</td>
        <td>{{ device.serialNumber }}</td>
        <td>{{ device.pagePerMinute }}</td>
        <td>{{ device.dateOfReceipt }}</td>
        <td>{{ device.dateOfCommissioning }}</td>
        <td>{{ device.userId }}</td>
        <td>{{ device.roomId }}</td>
        <td><a :href="'/device/' + device.id" class="btn btn-primary">Edit</a></td>
      </tr>
      </tbody>
    </table>
  </div>
</template>
