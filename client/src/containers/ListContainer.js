import React, { Component } from 'react';
import {inject, observer} from "mobx-react";
import {buildSchema, graphql} from "graphql";
import ApolloClient, { gql} from "apollo-boost";
import {Mutation, Query} from "react-apollo";

const GET_LISTS = gql`
        {
          lists {
              name
              id
          }
        }`;


const CREATE_LIST = gql`
  mutation createList($name: String!) {
    createList(name: $name) {
      id
      name
    }
  }
`;


class ListContainer extends Component {

  state = {
    listName: ''
  };

  render() {
    let input;

    return (
      <div>
        {/*<input type="text" onChange={this.handleInput} value={this.state.listName}/>*/}
        {/*<button onClick={this.submitNewList}>Submit</button>*/}

        <Mutation mutation={CREATE_LIST}
                  update={(cache, response) => {
                    const createdList =  response.data.createList;
                    const lists = cache.readQuery({ query: GET_LISTS }).lists;
                    cache.writeQuery({
                      query: GET_LISTS,
                      data: { lists: lists.concat([createdList]) },
                    });
                  }}
        >
          {(createList, { data }) => (
            <div>
              <form
                onSubmit={e => {
                  e.preventDefault();
                  createList({ variables: { name: input.value } });
                  input.value = "";
                }}
              >
                <input
                  ref={node => {
                    input = node;
                  }}
                />
                <button type="submit">Add List</button>
              </form>
            </div>
          )}
        </Mutation>

        <Query query={GET_LISTS}>
          {({ loading, error, data }) => {
            if (loading) return <p>Loading...</p>;
            if (error) return <p>Error :(</p>;

            return data.lists.map(({ name, id }) => (
              <div key={id}>
                <p>{name}: {id}</p>
              </div>
            ));
          }}
        </Query>
      </div>
    );
  }
}


export default ListContainer;
