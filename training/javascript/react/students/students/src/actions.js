import { ADD_USER,UPDATE_USER,DELETE_USER } from "./availableActions";

let idCounter = 1;
export const add_user = (user)=>({
    type:ADD_USER,
    payload:{...user,id:idCounter++}
});

export const update_user = (id,data)=>({
    type:UPDATE_USER,
    payload: {id,data}
})

export const delete_user = (id) =>({
    type:DELETE_USER,
    payload:id
})