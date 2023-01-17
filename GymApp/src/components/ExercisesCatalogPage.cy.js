import ExercisesCatalogPage from "./ExercisesCatalogPage.vue";
import { createPinia, setActivePinia } from "pinia";
import { createVuetify } from "vuetify";

// eslint-disable-next-line no-undef
describe("<ExercisesCatalogPage />", () => {
  // eslint-disable-next-line no-undef
  beforeEach(() => {
    setActivePinia(createPinia());
  });
  const vuetify = createVuetify();
  const mountFunction = (options) => {
    return cy.mount(ExercisesCatalogPage, {
      ...options,
      global: {
        plugins: [vuetify],
      },
    });
  };
  // eslint-disable-next-line no-undef
  it("renders", () => {
    // see: https://test-utils.vuejs.org/guide/
    mountFunction(ExercisesCatalogPage);
  });
});
