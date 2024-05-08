<script>
import DeviceDataService from '../services/DeviceDataService'
import * as XLSX from "xlsx";

export default {
  name: 'device-list',
  data() {
    return {
      device: [],
      room: [],
      users: [],
      name:''
    }
  },
  methods: {
    checkAmortization(dateOfCommissioning) {
      const today = new Date();
      const amortizationYears = 5;
      const commissioningDate = new Date(dateOfCommissioning);
      let yearsPassed = today.getFullYear() - commissioningDate.getFullYear();
      // Проверка, учитывая месяц и день
      if (today.getMonth() < commissioningDate.getMonth() ||
          (today.getMonth() === commissioningDate.getMonth() && today.getDate() < commissioningDate.getDate())) {
        yearsPassed--;
      }
      return yearsPassed >= amortizationYears ? 'Истек' : 'Не истек';
    },
    retrieveDevice() {
      DeviceDataService.getAll()
          .then(response => {
            this.device = response.data
          })
          .catch(e => {
            alert(e)
          })
    },
    ExportExcel(type, fn, dl) {
      var elt = this.$refs.exportable_table;
      var wb = XLSX.utils.table_to_book(elt, {sheet:"Sheet JS"});
      return dl ?
          XLSX.write(wb, {bookType:type, bookSST:true, type: 'base64'}) :
          XLSX.writeFile(wb, fn || ((this.name.length ?  this.name + '.' : 'SheetJSTableExport.') + (type || 'xlsx')));
    }
  },
  mounted() {
    this.retrieveDevice()
  }
}
</script>

<template>
  <div>
    <table ref="exportable_table" class="table">
      <thead>
      <tr>
        <th scope="col">Model</th>
        <th scope="col">Manufacturer</th>
        <th scope="col">Serial Number</th>
        <th scope="col">Page Per Minute</th>
        <th scope="col">Date Of Receipt</th>
        <th scope="col">Date Of Commissioning</th>
        <th scope="col">Paper Format</th>
        <th scope="col">Print Color</th>
        <th scope="col">Min Dpi</th>
        <th scope="col">User FIO</th>
        <th scope="col">Room Name</th>
        <th scope="col">Срок Амортизации</th>
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
        <td>{{ device.paperFormat }}</td>
        <td>{{ device.printColor }}</td>
        <td>{{ device.midDpi }}</td>
        <td>{{ device.userFio }}</td>
        <td>{{ device.roomName }}</td>
        <td>{{ checkAmortization(device.dateOfCommissioning) }}</td>
        <td><a :href="'/device/' + device.id" class="btn btn-primary">Edit</a></td>
      </tr>
      </tbody>
    </table>
    <div>
      <button @click="ExportExcel('xlsx')">Скачать Excel</button>
    </div>
  </div>
</template>

<style scoped>

button {
  padding: 10px 20px;
  background-color: #4CAF50; /* Зеленый */
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

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
  border-bottom: 2px solid #eee;
}
</style>
