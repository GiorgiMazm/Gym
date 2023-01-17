import "vuetify/styles"; // Global CSS has to be
import "@mdi/font/css/materialdesignicons.css";
import { createApp } from "vue";
import { createVuetify } from "vuetify";
import App from "./App.vue";
import { createPinia } from "pinia";
import { useGymStore } from "@/stores/GymStore";
import { useCatalogStore } from "@/stores/CatalogStore";

import router from "./router/router";
const app = createApp(App);
const vuetify = createVuetify();
const pinia = createPinia();

app.use(router);
app.use(pinia);
app.use(vuetify);
app.mount("#app");

useCatalogStore();
useGymStore();
