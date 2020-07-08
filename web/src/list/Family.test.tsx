import {render, RenderResult, fireEvent} from "@testing-library/react";
import React from "react";
import {ListClient, Observer} from "./ListClient";
import {Family, Item, Pinelist} from "./Family";

describe("<Family />", () => {

    it("displays all lists from server", () => {
        let findAllLists = jest.fn();

        findAllLists.mockImplementationOnce((observer: Observer) => observer.onSuccess(
            [
                buildPinelist({name: "list name"})
            ]
        ));
        let subject = render(<Family listClient={{findAllLists, findList: jest.fn()}}/>)
        expect(subject.getByText("list name")).toBeVisible();
    })

    it("links a list to show its items", () => {
        let findAllLists = jest.fn();
        let findList = jest.fn();

        findAllLists.mockImplementationOnce((observer: Observer) => observer.onSuccess(
            [
                buildPinelist({name: "list1"}),
                buildPinelist({name: "list2"})
            ]
        ));

        findList.mockImplementationOnce((observer: Observer) => observer.onSuccess(
            buildPinelist({ name: "list1", items: [buildItem({name: "item1"})]})
        ));

        let subject = render(<Family listClient={{findAllLists, findList}}/>)

        expect(subject.queryByText("list1")).toBeInTheDocument();
        expect(subject.queryByText("list2")).toBeInTheDocument();
        expect(subject.queryByText("item1")).not.toBeInTheDocument();

        fireEvent.click(subject.getByText("list1"));

        expect(subject.queryByText("list1")).toBeInTheDocument();
        expect(subject.queryByText("list2")).not.toBeInTheDocument();
        expect(subject.queryByText("item1")).toBeInTheDocument();
    })
})

const random = () => Math.floor(Math.random() * Math.floor(1000000));

const buildPinelist = (partial: Partial<Pinelist>): Pinelist => {
    return {
        name: 'some-name-' + random(),
        id: 'some-id-' + random(),
        items: [buildItem({})],
        ...partial
    }
};

const buildItem = (partial: Partial<Item>): Item => {
    return {
        name: 'some-item-name-' + random(),
        id: 'some-item-id-' + random(),
        ...partial
    }
};