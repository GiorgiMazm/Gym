describe("Navbar Routing", () => {
  beforeEach(() => {
    cy.visit("");
  });

  it("get catalog page", () => {
    cy.get("a").contains("catalog").click();
    cy.url().should("eq", "http://localhost:5173/catalog");
  });

  it("get records page", () => {
    cy.get("a").contains("records").click();
    cy.url().should("eq", "http://localhost:5173/records");
  });

  it("get home page", () => {
    cy.get("a").contains("home").click();
    cy.url().should("eq", "http://localhost:5173/");
  });

  it("404", () => {
    cy.visit("brokeeeeeenRoooutteeeer");
    cy.get(".v-card-title").should(
      "contain.text",
      "Ooops... You seemed to be drunk! Cuz there is no such a page"
    );
  });
});

describe("Catalog Routing", () => {
  beforeEach(() => {
    cy.visit("catalog");
  });

  it("exercise does not exist", () => {
    cy.visit("catalog/6464457457457646");
    cy.get(".v-card-title").should(
      "contain.text",
      "Ooops... there is no such an exercise"
    );
  });

  it("exercise edit does not exist", () => {
    cy.visit("catalog/edit/64644574557646");
    cy.get(".v-card-title").should(
      "contain.text",
      "Ooops... You cant edit this exercise! There is no such an exercise!"
    );
  });
});

describe("Record Routing", () => {
  beforeEach(() => {
    cy.visit("");
  });

  it("record does not exist", () => {
    cy.visit("record/64457457457646");
    cy.get(".v-card-title").should(
      "contain.text",
      "Ooops... there is no such a record"
    );
  });

  it("record for edit does not exist", () => {
    cy.visit("record/edit/64457457457646");
    cy.get(".v-card-title").should(
      "contain.text",
      "Ooops... you can not edit this record... It does not even exist"
    );
  });
});
