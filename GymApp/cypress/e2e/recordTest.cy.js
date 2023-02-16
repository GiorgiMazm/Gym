import recordFactory from "./factories/RecordFactory";
describe("recordsPage", () => {
  beforeEach(() => {
    cy.visit("records");
  });

  it("create new and delete it", () => {
    const trainingType = "Push6574857381";
    recordFactory.create({
      type: trainingType,
      exercise: "bench",
      weight: 100,
      repetition: 8,
      date: "2000-11-23",
    });
    cy.get(".records-wrapper").should("contain.text", trainingType);
    recordFactory.delete(trainingType);
    cy.get(".records-wrapper").should("not.contain.text", trainingType);
  });

  it("edit", () => {
    const trainingType = "Push6574857381";
    recordFactory.create({
      type: trainingType,
      exercise: "bench",
      weight: 100,
      repetition: 8,
      date: "2000-11-23",
    });
    cy.get(".records-wrapper").should("contain.text", trainingType);
    cy.get(".v-card-text")
      .contains(trainingType)
      .parent()
      .parent()
      .parent()
      .within(() => {
        cy.get("button").contains("Edit").click();
      });
    cy.url().should("contain", "http://localhost:5173/record/edit/");
    cy.get(".v-toolbar-title__placeholder").should(
      "contain.text",
      "Edit Single Record"
    );

    const newTrainingType = "Pull4654535";
    cy.get('input[name="recordType"').clear().type(newTrainingType);
    cy.get("button").contains("Save").click();

    cy.get(".records-wrapper").should("not.contain.text", trainingType);
    cy.get(".records-wrapper").should("contain.text", newTrainingType);

    recordFactory.delete(newTrainingType);
  });

  it("learn more", () => {
    const trainingType = "Push6574717381";
    recordFactory.create({
      type: trainingType,
      exercise: "bench",
      weight: 100,
      repetition: 8,
      date: "2000-11-23",
    });
    cy.get(".records-wrapper").should("contain.text", trainingType);

    cy.get(".v-card-text")
      .contains(trainingType)
      .parent()
      .parent()
      .parent()
      .within(() => {
        cy.get("button").contains("Learn more").click();
      });

    cy.url().should("contain", "http://localhost:5173/record/");

    cy.get(".v-toolbar-title__placeholder").should(
      "contain.text",
      "Single Record"
    );

    cy.get(".v-list-item-title").should("contain.text", "Exercise 1: bench");
    cy.get("button").contains("Back").click();
    recordFactory.delete(trainingType);
  });
});
