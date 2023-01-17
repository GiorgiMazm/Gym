import { describe, it, expect } from "vitest";

import { mount } from "@vue/test-utils";
import { createVuetify } from "vuetify";
import HomePage from "@/components/HomePage.vue";

describe("HomePage", () => {
  const vuetify = createVuetify();
  const mountFunction = (options) => {
    return mount(HomePage, {
      ...options,
      global: {
        plugins: [vuetify],
      },
    });
  };

  it("has the name of the page", () => {
    const wrapper = mountFunction();
    expect(wrapper.text()).toContain("Home page");
  });
});
