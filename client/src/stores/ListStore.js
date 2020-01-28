import {action, observable} from "mobx";

class ListStore {

  constructor(apiClient) {
    this.apiClient = apiClient;
  }

  lists = observable([]);

  getLists() {
    console.log('getting')
    return this.apiClient.get('http://localhost:8080/api/v1/lists')
      .then((data) => {
        console.log(data)
        this.stations = data;
      })
  }

}

export default ListStore