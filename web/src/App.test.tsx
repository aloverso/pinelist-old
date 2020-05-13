import React from 'react';
import {render, RenderResult} from '@testing-library/react';
import { App } from './App';
import axios from 'axios';
import {strict} from "assert";
import {act} from "react-dom/test-utils";

function flushPromises(): Promise<any> {
  return new Promise(resolve => setImmediate(resolve));
}

jest.mock('axios');
const mockedAxios = axios as jest.Mocked<typeof axios>;

describe('<App />', () => {
  it('says hello from the server', async () => {
    mockedAxios.get.mockResolvedValue({ data: "hello world" });
    let subject = render(<App />);

    await act(flushPromises);

    expect(subject.getByText("hello world", {exact: false})).toBeInTheDocument();
  });
});
