import React, {useState} from 'react';
import {Item, Pinelist} from "./Family";

interface Props {
    pinelist: Pinelist
}

export const PinelistDetail = (props: Props) => {

    const [newItem, setNewItem] = useState('');

    const addItem = () => {

    };

    return (<>
        <h2>{props.pinelist.name}</h2>

        <input value={newItem} onChange={(event => setNewItem(event.target.value))}/>
        <button onClick={addItem}>add</button>

        <ul>
            {props.pinelist.items.map((item: Item) => <li>{item.name}</li>)}
        </ul>
    </>)
};