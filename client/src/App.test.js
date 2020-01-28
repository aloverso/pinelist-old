import React from 'react';
import { mount } from 'enzyme';
import App from './App';
import StationStore from "./stores/StationStore";

describe('<App />', () => {
  it('displays stations from the station store', () => {
    const stubApiClient = new StubApiClient();
    const stationStore = new StationStore(stubApiClient);

    stubApiClient.stubbedGet = [
      {
        "name": "Union Square",
        "id": "1234"
      }
    ];

    const component = mount(<App stationStore={stationStore}/>);
    expect(component.find('[data-station]').first().text()).toContain('Union Square');
  });
});
