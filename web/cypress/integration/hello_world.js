describe('greeting', () => {
  it('user can see server greeting', () => {
    cy.visit('/')
    cy.contains("hello world").should('exist')
  })
})