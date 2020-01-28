import ApiClient from "./ApiClient";
import axios from 'axios';

jest.mock('axios');

describe('ApiClient', () => {
  it('should make HTTP GET calls', async () => {

    const stubData = {
      data: {
        'result': 1
      }
    };
    axios.get.mockImplementation(() => Promise.resolve(stubData));

    const apiClient = new ApiClient();
    const response = await apiClient.get('www.example.com');
    expect(response).toEqual({ 'result': 1});
    expect(axios.get).toHaveBeenCalledWith('www.example.com')
  });

  it('should make HTTP GET calls for different data', async () => {

    const stubData = {
      data: {
        'asparagus': 2
      }
    };
    axios.get.mockImplementation(() => Promise.resolve(stubData));

    const apiClient = new ApiClient();
    const response = await apiClient.get('www.vegetables.com');
    expect(response).toEqual({ 'asparagus': 2});
    expect(axios.get).toHaveBeenCalledWith('www.vegetables.com')
  });
});