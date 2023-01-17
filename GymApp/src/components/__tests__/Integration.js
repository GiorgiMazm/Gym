import { describe, it, expect } from "vitest";
import { cy } from "cypress";

describe("Exercises Catalog page", () => {
  // eslint-disable-next-line no-undef
  beforeEach(() => {
    cy.visit(" http://localhost:5173/");
  });

  it("has the name of the page", () => {
    cy.get(".links").children().should("have.length", 2);
  });
});
