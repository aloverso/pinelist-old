import React, {useEffect, useState} from 'react';
import axios, {AxiosResponse} from 'axios';
import {Family} from "./list/Family";
import {findAllLists, findList} from "./list/ListClient";

export const App = () => {

  // const [greeting, setGreeting] = useState("");
  //
  // useEffect(() => {
  //   axios.get('/api/hello')
  //       .then((response: AxiosResponse<string>) => {
  //           setGreeting(response.data)
  //       })
  // });

  return (
    <>
        <Family listClient={{findAllLists: findAllLists, findList: findList}}/>
    </>
  );
};