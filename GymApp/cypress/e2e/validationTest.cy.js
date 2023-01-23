describe("Can't create a new exercise if", () => {
  beforeEach(() => {
    cy.visit("catalog");
  });

  it("Name is empty", () => {
    cy.get("[data-test='create-button']").click();
    cy.get("[data-test='muscle-group']").find("input").type("Chest");

    cy.get("[data-test='difficulty']").click();
    cy.get(".v-overlay-container").within(() => {
      cy.get("div").contains("Medium").click();
    });

    cy.get("[data-test='description']").find("input").type("Do it correct!");

    cy.get("[data-test='save-button']").click();

    cy.get(".v-messages__message")
      .first()
      .should("have.text", "Field is required!");
  });

  it("Muscle is empty", () => {
    const exerciseName = "Chest4464464";
    cy.get("[data-test='create-button']").click();
    cy.get("[data-test='exercise-name']").find("input").type(exerciseName);

    cy.get("[data-test='difficulty']").click();
    cy.get(".v-overlay-container").within(() => {
      cy.get("div").contains("Medium").click();
    });

    cy.get("[data-test='description']").find("input").type("Do it correct!");

    cy.get("[data-test='save-button']").click();

    cy.get(".v-messages__message")
      .first()
      .should("have.text", "Field is required!");

    cy.get(".all-exercises").should("not.contain.text", exerciseName);
  });

  it("Difficulty is empty", () => {
    const exerciseName = "Chest446146464";
    cy.get("[data-test='create-button']").click();
    cy.get("[data-test='exercise-name']").find("input").type(exerciseName);
    cy.get("[data-test='muscle-group']").find("input").type("Chest");
    cy.get("[data-test='description']").find("input").type("Do it correct!");

    cy.get("[data-test='save-button']").click();

    cy.get(".v-messages__message").should("have.text", "Field is required!");
    cy.get(".all-exercises").should("not.contain.text", exerciseName);
  });

  it("Description is empty", () => {
    const exerciseName = "Chest446436464";
    cy.get("[data-test='create-button']").click();
    cy.get("[data-test='exercise-name']").find("input").type(exerciseName);
    cy.get("[data-test='muscle-group']").find("input").type("Chest");

    cy.get("[data-test='difficulty']").click();
    cy.get(".v-overlay-container").within(() => {
      cy.get("div").contains("Medium").click();
    });

    cy.get("[data-test='save-button']").click();

    cy.get(".v-messages__message")
      .first()
      .should("have.text", "Field is required!");

    cy.get(".all-exercises").should("not.contain.text", exerciseName);
  });
});

describe("Can create a new exercise if", () => {
  beforeEach(() => {
    cy.visit("catalog");
  });

  it("All field are correct", () => {
    const exerciseName = "Chest474614";
    cy.get("[data-test='create-button']").click();
    cy.get("[data-test='exercise-name']").find("input").type(exerciseName);
    cy.get("[data-test='muscle-group']").find("input").type("Chest");

    cy.get("[data-test='difficulty']").click();
    cy.get(".v-overlay-container").within(() => {
      cy.get("div").contains("Medium").click();
    });

    cy.get("[data-test='description']").find("input").type("Do it correct!");

    cy.get("[data-test='save-button']").click();

    cy.get("div").should("not.contain.text", "Field is required!");

    cy.get(".all-exercises").should("contain.text", exerciseName);
  });
});
