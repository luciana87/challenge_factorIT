import { Item } from "./Item";

export interface Cart {
    id: number,
    total: number,
    totalProducts: number,
    deleted: boolean,
    confirmed: boolean,
    itemsDTO: Item[]
}