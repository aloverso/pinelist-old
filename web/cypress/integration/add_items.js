describe('viewing and adding items', () => {

  beforeEach(() => {
    cy.exec('cd $(git rev-parse --show-toplevel) && ./scripts/db-reset.sh && ./scripts/db-seed.sh')
    cy.visit('/')
  });

  it('user can view existing list', () => {
    cy.contains("Christmas List 2020").should('exist');

    cy.contains("laptop").should('not.exist');
    cy.contains("potted plant").should('not.exist');

    cy.contains("Christmas List 2020").click();

    cy.contains("Christmas List 2020").should('exist');
    cy.contains("potted plant").should('exist');
    cy.contains("laptop").should('exist');
  })

  // it('user can add a new list', () => {
  //   cy.visit('/')
  //   cy.contains("my cool wishlist item").should('not.exist')
  //   cy.contains("new item").type("my cool wishlist item")
  //   cy.contains("add").click()
  //   cy.contains("my cool wishlist item").should('exist')
  // })
});