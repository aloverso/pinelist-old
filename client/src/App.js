import React, { Component } from 'react';
import {Provider} from "mobx-react";
import ListContainer from "./containers/ListContainer";
import AppStore from "./stores/ListStore";
import ApiClient from "./clients/ApiClient";
import ApolloClient from "apollo-boost/lib/index";
import {ApolloProvider} from "react-apollo";

const client = new ApolloClient({
  uri: "http://localhost:8080/graphql"
});


class App extends Component {
  render() {
    return (
      <ApolloProvider client={client} >
        <ListContainer />

      </ApolloProvider>
    );
  }
}


export default App;
