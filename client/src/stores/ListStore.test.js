import ListStore from "./ListStore";
import readJsonContractFile from 'spring-cloud-contract-json-reader';
import ApiClient from "../clients/ApiClient";

describe('ListStore', () => {
  it('should fetch and store lists', async () => {
    const listStore = new ListStore(new ApiClient());
    const contractData = await readJsonContractFile('shouldReturnLists.json')

    await listStore.getLists();
    expect(listStore.lists).toEqual(JSON.parse(contractData))
  });

  // it('should add a random station to the store', () => {
  //   ...
  // });
});