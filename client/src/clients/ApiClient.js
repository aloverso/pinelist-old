import axios from 'axios';

class ApiClient {
  get(url) {
    return axios.get(url)
      .then((response) => {
        console.log(response.data)
        return response.data
      })
  }
}

export default ApiClient;