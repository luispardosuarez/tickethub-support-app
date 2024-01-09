import { createRouter, createWebHistory } from 'vue-router';
import Login from '../views/Login.vue';
import Solicitudes from '../views/Solicitudes.vue';
import EditarCrearSolicitud from '../views/EditarCrearSolicitud.vue';

const routes = [
  { path: '/', redirect: '/login' },
  { path: '/login', name: 'Login', component: Login },
  { path: '/solicitudes', name: 'Solicitudes', component: Solicitudes },
  { path: '/solicitud/:id?', name: 'EditarCrearSolicitud', component: EditarCrearSolicitud },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
});

export default router;
