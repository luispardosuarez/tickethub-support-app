<template>
  <div>
    <h1>{{ esModoEdicion ? 'Editar Solicitud' : 'Crear Solicitud' }}</h1>
    <form @submit.prevent="enviarSolicitud">
      <div>
        <label for="requesterName">Usuario:</label>
        <input id="requesterName" v-model="solicitud.requesterName" required>
      </div>
      <div>
        <label for="description">Descripción:</label>
        <input id="description" v-model="solicitud.description" required>
      </div>
      <button type="submit">{{ esModoEdicion ? 'Actualizar' : 'Crear' }}</button>
    </form>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      solicitud: {
        requestName: '',
        description: ''
      },
      esModoEdicion: false
    }
  },
  created() {
    if (this.$route.params.id) {
      this.cargarSolicitud(this.$route.params.id);
      this.esModoEdicion = true;
    }
  },
  methods: {
    cargarSolicitud(id) {
      axios.get(`http://localhost:8080/api/solicitudes/${id}`)
        .then(response => {
        this.solicitud = response.data;
      })
        .catch(error => {
          console.error('Error cargando la solicitud:', error);
        });
    },
    enviarSolicitud() {
      if (this.esModoEdicion) {
        this.actualizarSolicitud();
      } else {
        this.crearSolicitud();
      }
    },
    actualizarSolicitud() {
      axios.put(`http://localhost:8080/api/solicitudes/${this.$route.params.id}`, this.solicitud)
        .then(() => {
        this.$router.push('/solicitudes');
      })
        .catch(error => {
          console.error('Error actualizando la solicitud:', error);
        });
    },
    crearSolicitud() {
      axios.post('http://localhost:8080/api/solicitudes', this.solicitud)
        .then(() => {
          this.$router.push('/solicitudes');
        })
        .catch(error => {
          console.error('Error creando la solicitud:', error);
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

form {
  max-width: 400px;
  margin: 2rem auto;
  padding: 1rem;
  border-radius: 8px;
  background-color: white;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

div {
  margin-bottom: 1rem;
}

label {
  display: block;
  margin-bottom: 0.5rem;
  font-weight: bold;
  color: #555;
}

input {
  width: 100%;
  padding: 8px;
  margin-top: 5px;
  border: 1px solid #ddd;
  border-radius: 4px;
  box-sizing: border-box;
}

button {
  width: 100%;
  padding: 10px;
  border: none;
  background-color: #EF117A;
 
  color: white;
  border-radius: 4px;
  cursor: pointer;
  font-weight: bold;
  font-size: 1rem;
}

button:hover {
  background-color: #EF117A;

}

</style>
  