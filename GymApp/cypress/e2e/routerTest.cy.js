import exerciseFactory from "./ExerciseFactory";

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

  it("get an exercise", () => {
    const exerciseName = "myName666";
    exerciseFactory.create({
      name: exerciseName,
      muscle: "all of muscles",
      difficulty: "Easy",
      description:
        "This exercise is too hard for you young man. Don't you dare even try to do it. You'll die...",
    });
    cy.get(".all-exercises").should("contain.text", exerciseName);

    cy.get(".catalogCardName")
      .contains(exerciseName)
      .parent()
      .parent()
      .within(() => {
        cy.get("span").contains("Learn more").click();
      });
    cy.get(".v-toolbar-title__placeholder").should(
      "contain.text",
      "Single Exercise"
    );
    cy.url().should("contain", "http://localhost:5173/catalog/");

    cy.get("span").contains("Back").click();
    exerciseFactory.delete(exerciseName);
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

  it("edit an exercise", () => {
    const exerciseName = "myName777";
    exerciseFactory.create({
      name: exerciseName,
      muscle: "all of muscles",
      difficulty: "Easy",
      description:
        "This exercise is too hard for you young man. Don't you dare even try to do it. You'll die...",
    });
    cy.get(".all-exercises").should("contain.text", exerciseName);

    cy.get(".catalogCardName")
      .contains(exerciseName)
      .parent()
      .parent()
      .within(() => {
        cy.get("span").contains("Edit").click();
      });
    cy.get(".v-toolbar-title__placeholder").should(
      "contain.text",
      "Edit Exercise"
    );
    cy.url().should("contain", "http://localhost:5173/catalog/edit/");

    cy.get("span").contains("Back").click();
    exerciseFactory.delete(exerciseName);
  });
});
