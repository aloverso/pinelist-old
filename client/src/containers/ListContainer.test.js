import StationContainer from "./ListContainer";
import {mount} from "enzyme";
import React from "react";

describe('<ListContainer />', () => {
  it('should render stations from the station store', () => {

    const stubAppStore = {
      stations: [
        {
          name: 'My Cool Station',
          id: '1'
        }
      ],

      currentView: 'stations',
      getStations: jest.fn(),
    };

    const component = mount(
      <StationContainer appStore={stubAppStore}/>
    );
    expect(stubAppStore.getStations).toHaveBeenCalled();
    expect(component.find('[data-station]').text())
      .toContain('My Cool Station');
  });

  it('should add a station on button click', () => {

    const stubAppStore = {
      stations: [],
      getStations: jest.fn(),
      addStation: jest.fn()
    };

    const component = mount(
      <StationContainer appStore={stubAppStore}/>
    );
    component.find('[data-add-station-button]').simulate('click');

    expect(stubAppStore.addStation).toHaveBeenCalled();
  });
});