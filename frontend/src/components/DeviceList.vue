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
      name:'',
      filters: {
        manufacturer: '',
        userFio: '',
        roomName: '',
        amortization: '',
        startDate: null,
        endDate: null
      }
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
  computed: {
    filteredDevices() {
      return this.device.filter(device => {
        return (
            device.manufacturer.toLowerCase().includes(this.filters.manufacturer.toLowerCase()) &&
            device.userFio.toLowerCase().includes(this.filters.userFio.toLowerCase()) &&
            device.roomName.toLowerCase().includes(this.filters.roomName.toLowerCase()) &&
            (this.filters.amortization === '' || this.checkAmortization(device.dateOfCommissioning) === this.filters.amortization) &&
            (!this.filters.startDate || new Date(device.dateOfCommissioning) >= new Date(this.filters.startDate)) &&
            (!this.filters.endDate || new Date(device.dateOfCommissioning) <= new Date(this.filters.endDate))
        );
      });
    }
  },
  mounted() {
    this.retrieveDevice()
  }
}
</script>

<template>
  <div>
    <div>
      <label for="manufacturerFilter">Manufacturer:</label>
      <input type="text" id="manufacturerFilter" v-model="filters.manufacturer">
    </div>
    <div>
      <label for="userFioFilter">User FIO:</label>
      <input type="text" id="userFioFilter" v-model="filters.userFio">
    </div>
    <div>
      <label for="roomNameFilter">Room Name:</label>
      <input type="text" id="roomNameFilter" v-model="filters.roomName">
    </div>
    <div>
      <label for="amortizationFilter">Срок Амортизации:</label>
      <select id="amortizationFilter" v-model="filters.amortization">
        <option value="">Все</option>
        <option value="Истек">Истек</option>
        <option value="Не истек">Не истек</option>
      </select>
    </div>
    <div>
      <label for="startDate">Начальная дата:</label>
      <input type="date" id="startDate" v-model="filters.startDate">
    </div>
    <div>
      <label for="endDate">Конечная дата:</label>
      <input type="date" id="endDate" v-model="filters.endDate">
    </div>
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
      <tbody v-for="(device, index) in filteredDevices" :key="index">
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
