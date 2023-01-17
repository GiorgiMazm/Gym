import { describe, it, expect } from "vitest";
import { mount } from "@vue/test-utils";
import { createVuetify } from "vuetify";
import ExercisesCatalogPage from "@/components/ExercisesCatalogPage.vue";
import { createPinia, setActivePinia } from "pinia";

describe("Exercises Catalog page", () => {
  let wrapper;
  // eslint-disable-next-line no-undef
  beforeEach(() => {
    setActivePinia(createPinia());
    wrapper = mountFunction();
  });
  const vuetify = createVuetify();
  const mountFunction = (options) => {
    return mount(ExercisesCatalogPage, {
      ...options,
      global: {
        plugins: [vuetify],
      },
    });
  };
  it("has the name of the page", () => {
    expect(wrapper.text()).toContain("Exercises Catalog page");
  });

  it("open create new form", async () => {
    expect(wrapper.find('[data-test="create-button"]').text()).toBe(
      "New exercise"
    );

    await wrapper.find('[data-test="create-button"]').trigger("click");
    expect(wrapper.find('[data-test="save-button"]').text()).toBe("Save");
    expect(wrapper.find('[data-test="cancel-button"]').text()).toBe("Cancel");
    expect(wrapper.find('[data-test="exercise-name"]').text()).contain(
      "Exercise's Name"
    );
    expect(wrapper.text()).not.toContain("Muscle group");
    await wrapper.find('[data-test="save-button"]').trigger("click");
    expect(wrapper.text()).toContain("Muscle group");
  });
});
