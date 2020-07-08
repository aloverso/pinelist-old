import React from 'react';
import {Pinelist} from "./Family";

interface Props {
    pinelist: Pinelist
}

export const PinelistOverview = (props: Props) => (
    <div>{props.pinelist.name}</div>
);