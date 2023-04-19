import { 
    PRODUCT_LIST_FAILURE, 
    PRODUCT_LIST_SUCCESS, 
    PRODUCT_LIST_REQUEST,
    PRODUCT_DETAIL_FAILURE, 
    PRODUCT_DETAIL_SUCCESS, 
    PRODUCT_DETAIL_REQUEST,
    PRODUCT_CREATE_REVIEW_FAILURE, 
    PRODUCT_CREATE_REVIEW_SUCCESS, 
    PRODUCT_CREATE_REVIEW_REQUEST,
    PRODUCT_CREATE_REVIEW_RESET,
    
 } 
    from "../constants/productConstants";
export const productListReducer = (state = {loading: true, products: []}, action) =>{
    switch(action.type){
        case PRODUCT_LIST_REQUEST:
            return {loading: true, products: []}
        case PRODUCT_LIST_SUCCESS:
            return {loading: false, products: action.payload.products, page: action.payload.page, pages: action.payload.pages           }
        case PRODUCT_LIST_FAILURE:
            return {loading: false, error: action.payload}
        default:
            return state
    }
}

export const productDetailReducer = (state = {product: {reviews:[]}}, action) =>{
    switch(action.type){
        case PRODUCT_DETAIL_REQUEST:
            return {loading: true, ...state}
        case PRODUCT_DETAIL_SUCCESS:
            return {loading: false, product: action.payload}
        case PRODUCT_DETAIL_FAILURE:
            return {loading: false, error: action.payload}
        default:
            return state
    }
}

export const productCreateReviewReducer = (state = {}, action) =>{
    switch(action.type){
        case PRODUCT_CREATE_REVIEW_REQUEST:
            return {loading: true}
        case PRODUCT_CREATE_REVIEW_SUCCESS:
            return {loading: false, success: true}
        case PRODUCT_CREATE_REVIEW_FAILURE:
            return {loading: false, error: action.payload}
        case PRODUCT_CREATE_REVIEW_RESET:
            return {}
        default:
            return state
    }
}