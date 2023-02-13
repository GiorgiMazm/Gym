import { createRouter, createWebHistory } from "vue-router";
import HomePage from "@/components/HomePage.vue";
import GymRecords from "@/components/Records/GymRecordsPage.vue";
import ExercisesCatalogPage from "@/components/Catalog/ExercisesCatalogPage.vue";
import SingleExercise from "@/components/Catalog/SingleExercise.vue";
import NotFound from "@/components/NotFound.vue";
import EditExercise from "@/components/Catalog/EditExercise.vue";
import SingleRecord from "@/components/Records/SingleRecord.vue";
import EditRecord from "@/components/Records/EditRecord.vue";

const routes = [
  {
    path: "/records",
    component: GymRecords,
  },
  {
    path: "/record/:id",
    component: SingleRecord,
  },

  {
    path: "/record/edit/:id",
    component: EditRecord,
  },

  {
    path: "/",
    component: HomePage,
  },

  {
    path: "/catalog",
    component: ExercisesCatalogPage,
  },

  {
    path: "/catalog/:id",
    component: SingleExercise,
  },

  {
    path: "/catalog/edit/:id",
    component: EditExercise,
  },

  {
    path: "/:notFound(.*)",
    component: NotFound,
  },
];

const router = createRouter({
  routes,
  history: createWebHistory(),
});

export default router;
