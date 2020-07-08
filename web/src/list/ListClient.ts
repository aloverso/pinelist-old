import axios, {AxiosResponse} from 'axios';

export interface ListClient {
    findAllLists: (observer: Observer) => void,
    findList: (id: string, observer: Observer) => void
}

export const findAllLists = (withObserver: Observer) => {
    axios.get('/api/lists').then(handleResponse(withObserver))
};

export const findList = (id: string, withObserver: Observer) => {
    axios.get('/api/lists/' + id).then(handleResponse(withObserver))
};

const handleResponse = (observer: Observer) =>
    (response: AxiosResponse<Response<any>>) => {
        console.log(response.data.outcome == Outcome.SUCCESS)
        if (response.data.outcome == Outcome.SUCCESS) {
            observer.onSuccess(response.data.data)
        } else {
            observer.onError(response.data.data)
        }
    };


interface PinelistOverview {
    id: String,
    name: string
}

interface Response<T> {
    outcome: Outcome,
    data: T
}

enum Outcome {
    SUCCESS = "SUCCESS",
    ERROR = "ERROR"
}

export interface Observer {
    onSuccess: (data: any) => void
    onError: (error: string) => void
}