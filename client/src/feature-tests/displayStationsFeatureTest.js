import { Selector } from 'testcafe';

fixture `ListsDashboard`
    .page `http://localhost:3000`;

test('Should display list names', async t => {
    await t
        .expect(Selector('[data-list]').innerText).eql('My Cool List');
});