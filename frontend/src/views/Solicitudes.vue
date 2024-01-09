<template>
  <div>
    <div class="header">
      <h1>Solicitudes</h1>
      
    </div>
    <button @click="crearSolicitud">Crear Solicitud</button>
    <table>
      <tr>
        <th>ID</th>
        <th>Usuario</th>
        <th>Descripción</th>
        <th>Acciones</th>
        <th>Fecha solicitud</th>
      </tr>
      <tr v-for="solicitud in solicitudes" :key="solicitud.id">
        <td>{{ solicitud.id }}</td>
        <td>{{ solicitud.requesterName }}</td>
        <td>{{ solicitud.description }}</td>
        <td>
          <button @click="editarSolicitud(solicitud.id)">Editar</button>
          <button @click="eliminarSolicitud(solicitud.id)">Eliminar</button>
        </td>
      </tr>
    </table>
    <button id="bclose" @click="cerrarSesion" class="cerrar-sesion">Cerrar Sesión</button>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      solicitudes: []
    }
  },
  created() {
    this.cargarSolicitudes();
  },
  methods: {
    cerrarSesion() {
      this.$router.push('/login');
    },
    cargarSolicitudes() {
      axios.get('http://localhost:8080/api/solicitudes')
        .then(response => {
          this.solicitudes = response.data;
        })
        .catch(error => {
          console.error('Error cargando las solicitudes:', error);
        });
    },
    editarSolicitud(id) {
      this.$router.push({ name: 'EditarCrearSolicitud', params: { id: id } });
    },
    crearSolicitud() {
      this.$router.push({ name: 'EditarCrearSolicitud', params: { id: null } });
    },
    eliminarSolicitud(id) {
      axios.delete(`http://localhost:8080/api/solicitudes/${id}`)
        .then(() => {
          this.cargarSolicitudes(); // Recargar la lista después de eliminar
        })
        .catch(error => {
          console.error('Error eliminando la solicitud:', error);
        });
    }
  }
}
</script>

<style>
h1 {
  color: #EF117A;
  text-align: center;
}

table {
  width: 100%;
  border-collapse: collapse;
  margin-top: 1rem;
}

th,
td {
  border: 1px solid #ddd;
  padding: 8px;
  text-align: left;
}

th {
  background-color: #EF117A;
  color: white;
}

tr:nth-child(even) {
  background-color: #f2f2f2;
}

button {
  padding: 5px 10px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  font-weight: bold;
}

.edit-button {
  background-color: #4CAF50;
  color: white;
}

.edit-button:hover {
  background-color: #45a049;
}

.delete-button {
  background-color: #EF117A;
  color: white;
}

.delete-button:hover {
  background-color: #EF117A;
}

.cerrar-sesion {
  padding: 5px 10px;
  background-color: #EF117A;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

#bclose {
  margin-top: 10%;
}
</style>

