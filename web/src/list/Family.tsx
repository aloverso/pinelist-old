import React, {useEffect, useReducer, useState} from 'react';
import {ListClient} from "./ListClient";
import {PinelistOverview} from "./PinelistOverview";
import {PinelistDetail} from "./PinelistDetail";

export interface Pinelist {
    name: string,
    id: string,
    items: Item[]
}

export interface Item {
    id: string,
    name: string
}

interface Props {
    listClient: ListClient
}

export const Family = (props: Props) => {

    const FAMILY = "FAMILY";

    const [pinelists, setPineLists] = useState<Pinelist[]>([]);
    const [currentList, setCurrentList] = useState<Pinelist | undefined>();
    const [currentView, setCurrentView] = useState<string>(FAMILY);

    useEffect(() => {
        props.listClient.findAllLists({
            onSuccess: data => {
                setPineLists(data)
            },
            onError: error => {}
        })
    }, []);

    useEffect(() => {
        if (currentView != FAMILY) {
            props.listClient.findList(currentView, {
                onSuccess: data => {
                    setCurrentList(data)
                },
                onError: error => {}
            })
        }
    }, [currentView]);

    const familyView = () => (
        <>
            <h1>Pinelists</h1>
            <ul>
                {pinelists.map(list =>
                    <li
                        onClick={() => setCurrentView(list.id)}
                        key={list.id}
                    >
                        <PinelistOverview pinelist={list}/>
                    </li>
                )}
            </ul>
        </>
    );

    const listDetailView = () => (
        currentList &&
        <>
            <PinelistDetail pinelist={currentList!} />
        </>
    );

    return (
        <div>
            {currentView === FAMILY ? familyView() : listDetailView()}
        </div>
    );
};