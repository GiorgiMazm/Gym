import GymRecordsPage from "./Records/GymRecordsPage.vue";
import { createPinia, setActivePinia } from "pinia";
import { createVuetify } from "vuetify";
// eslint-disable-next-line no-undef
describe("<GymRecordsPage />", () => {
  // eslint-disable-next-line no-undef
  beforeEach(() => {
    setActivePinia(createPinia());
  });
  const vuetify = createVuetify();
  const mountFunction = (options) => {
    // eslint-disable-next-line no-undef
    return cy.mount(GymRecordsPage, {
      ...options,
      global: {
        plugins: [vuetify],
      },
    });
  };
  // eslint-disable-next-line no-undef
  it("renders", () => {
    // see: https://test-utils.vuejs.org/guide/
    mountFunction(GymRecordsPage);
  });
});
