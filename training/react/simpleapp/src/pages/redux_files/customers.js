import { createStore } from 'redux';
const ADD_CUSTOMER = 'ADD_CUSTOMER';
const UPDATE_CUSTOMER = 'UPDATE_CUSTOMER';
const DELETE_CUSTOMER = 'DELETE_CUSTOMER';
const SET_CUSTOMER_FOR_EDIT = 'SET_CUSTOMER_FOR_EDIT';
const CLEAR_EDIT_CUSTOMER = 'CLEAR_EDIT_CUSTOMER';


export const addCustomer = (customerData) => ({
  type: ADD_CUSTOMER,
  payload: customerData,
});

export const updateCustomer = (customerData) => ({
  type: UPDATE_CUSTOMER,
  payload: customerData,
});

export const deleteCustomer = (customerId) => ({
  type: DELETE_CUSTOMER,
  payload: customerId,
});

export const setCustomerForEdit = (customerData) => ({
  type: SET_CUSTOMER_FOR_EDIT,
  payload: customerData,
});

export const clearEditCustomer = () => ({
  type: CLEAR_EDIT_CUSTOMER,
});


const initialState = {
  Myformdata: [],
  customerBeingEdited: null,
};

const customerReducer = (state = initialState, action) => {
  switch (action.type) {
    case ADD_CUSTOMER:
      return {
        ...state,
        Myformdata: [...state.Myformdata, action.payload],
      };

    case UPDATE_CUSTOMER:
      return {
        ...state,
        Myformdata: state.Myformdata.map((cust) =>
          cust.id === action.payload.id ? action.payload : cust
        ),
        customerBeingEdited: null,
      };

    case DELETE_CUSTOMER:
      return {
        ...state,
        Myformdata: state.Myformdata.filter((cust) => cust.id !== action.payload),
      };

    case SET_CUSTOMER_FOR_EDIT:
      return {
        ...state,
        customerBeingEdited: action.payload,
      };

    case CLEAR_EDIT_CUSTOMER:
      return {
        ...state,
        customerBeingEdited: null,
      };

    default:
      return state;
  }
};export default customerReducer;


export const store = createStore(customerReducer);
