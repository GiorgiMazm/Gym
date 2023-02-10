import { describe, it, expect } from "vitest";
import { mount } from "@vue/test-utils";
import { createVuetify } from "vuetify";
import { createPinia, setActivePinia } from "pinia";
import GymRecordsPage from "@/components/Records/GymRecordsPage.vue";

describe("Records page", () => {
  // eslint-disable-next-line no-undef
  beforeEach(() => {
    setActivePinia(createPinia());
  });
  const vuetify = createVuetify();
  const mountFunction = (options) => {
    return mount(GymRecordsPage, {
      ...options,
      global: {
        plugins: [vuetify],
      },
    });
  };

  it("has the name of the page", () => {
    const wrapper = mountFunction();
    expect(wrapper.text()).toContain("Records page");
  });
});
