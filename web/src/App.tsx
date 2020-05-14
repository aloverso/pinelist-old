import React, {useEffect, useState} from 'react';
import './App.css';
import axios, {AxiosResponse} from 'axios';

export const App = () => {

  const [greeting, setGreeting] = useState("");

  useEffect(() => {
    axios.get('/api/hello')
        .then((response: AxiosResponse<string>) => {
            setGreeting(response.data)
        })
  });

  return (
    <div className="App">
      <header className="App-header">
        <h1>The server says: {greeting}</h1>
      </header>
    </div>
  );
};