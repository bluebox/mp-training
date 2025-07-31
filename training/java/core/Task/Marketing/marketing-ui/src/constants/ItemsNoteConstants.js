const ITEMS_REMOVE_NOTE = ["Item Remove: Excel file contains one column (ItemId)"]
const PAYBACK_NOTE = ["paybackPercentage is not allowed this applicableType"]
const CUSTOMERS_NOTE = [
    "Customer: Excel file contains one column (CustomerId) to remove or upload.",
    "While cloning the campaign Items/Customer Excel file need to uploaded freshly."
]

export const MIC_ITEM_NOTE = ["Item: Excel file contains one column (ItemId) to remove or upload",
    "At least 2 product ids are required to configure a multi-item campaign"].concat(CUSTOMERS_NOTE)

const SD_WITH_PB_COLS = " ProductId, FromQuantity, Discount Type (Exactsale_price, Discount_percentage, Discount_price), DiscountValue, MaxQuantity[optional], Display Message, paybackPercentage [Optional], PriceConsiderForSlab ('M' -> MRP, 'S' -> Sale Price) By default Sale Price Considered [Optional]";
const SD_COLS = " FromQuantity, Discount Type (Exactsale_price, Discount_percentage, Discount_price), DiscountValue, MaxQuantity[optional], Display Message, PriceConsiderForSlab ('M' -> MRP, 'S' -> Sale Price) By default Sale Price Considered [Optional]"
const NON_SD_COLS = " FromProductId, FromQuantity, ToProductId, ToQuantity, Display Message, paybackPercentage [Optional], PriceConsiderForSlab ('M' -> MRP, 'S' -> Sale Price) By default Sale Price Considered [Optional]";

export const ADD_ON_PROMO_NOTE = ["For campaign type Promotion Addon, Discount Type must only be Discount_percentage"]
export const PRICE_CONSIDERED_FOR_SLAB_NOTE=["PriceConsiderForSlab must be consistent for a product — all 'M' or all 'S'"]
export const SD_ITEM_NOTE = ["Product Upload: Excel file contains eight columns, (" + SD_WITH_PB_COLS + " )"].concat(PRICE_CONSIDERED_FOR_SLAB_NOTE).concat(ADD_ON_PROMO_NOTE).concat(ITEMS_REMOVE_NOTE).concat(CUSTOMERS_NOTE)
export const SD_MULTIPLE_ITEM_NOTE = ["Product Upload: Excel file contains nine columns, (" + SD_WITH_PB_COLS + " , StoreId)"].concat(PRICE_CONSIDERED_FOR_SLAB_NOTE).concat(ADD_ON_PROMO_NOTE).concat(ITEMS_REMOVE_NOTE).concat(CUSTOMERS_NOTE)
export const NON_SD_ITEM_NOTE = ["Product Upload: Excel file contains seven columns, (" + NON_SD_COLS + " )"].concat(PRICE_CONSIDERED_FOR_SLAB_NOTE).concat(ITEMS_REMOVE_NOTE).concat(CUSTOMERS_NOTE)
export const NON_SD_MULTIPLE_ITEM_NOTE = ["Product Upload: Excel file eight columns, (" + NON_SD_COLS + ", StoreId)"].concat(PRICE_CONSIDERED_FOR_SLAB_NOTE).concat(ITEMS_REMOVE_NOTE).concat(CUSTOMERS_NOTE)

export const SB_ITEM_NOTE = ["Test Upload: Excel file contains seven columns, (SpecialtyId," + SD_COLS + " )"].concat(PAYBACK_NOTE).concat(PRICE_CONSIDERED_FOR_SLAB_NOTE).concat(ITEMS_REMOVE_NOTE).concat(CUSTOMERS_NOTE)
export const SB_MULTIPLE_ITEM_NOTE = ["Test Upload: Excel file contains eight columns, (SpecialtyId," + SD_COLS + " , StoreId)"].concat(PAYBACK_NOTE).concat(PRICE_CONSIDERED_FOR_SLAB_NOTE).concat(ITEMS_REMOVE_NOTE).concat(CUSTOMERS_NOTE)
export const NON_SB_ITEM_NOTE = ["Test Upload: Excel file contains seven columns, (TestCode/ServiceCode," + SD_COLS + " )"].concat(PAYBACK_NOTE).concat(PRICE_CONSIDERED_FOR_SLAB_NOTE).concat(ITEMS_REMOVE_NOTE).concat(CUSTOMERS_NOTE)
export const NON_SB_MULTIPLE_ITEM_NOTE = ["Test Upload: Excel file contains eight columns, (TestCode/ServiceCode," + SD_COLS + " , StoreId)"].concat(PAYBACK_NOTE).concat(PRICE_CONSIDERED_FOR_SLAB_NOTE).concat(ITEMS_REMOVE_NOTE).concat(CUSTOMERS_NOTE)

export const LENS_ITEM_NOTE = ["Optical Product Upload: Excel file contains seven columns, (OpticalProductId," + SD_COLS + " )"].concat(PAYBACK_NOTE).concat(PRICE_CONSIDERED_FOR_SLAB_NOTE).concat(ITEMS_REMOVE_NOTE).concat(CUSTOMERS_NOTE)

export const NO_ITEMS_NOTE = ["No item upload file required for campaigns with service charge coupon"].concat(CUSTOMERS_NOTE)

export const COMP_GIFT_UPLOAD = ["Complimentary Gift`s Upload: Excel file contains four columns, (ProductId, Quantity, Discount, ProductAction), Discount should be in between 0 to 100, Product Action should be either A-Auto or R-Recommend"]
export const COMP_GIFT_REMOVE = ["Complimentary Gift`s Remove: Excel file contains one columns (ProductId)"]
export const REF_UPLOAD = ["References Upload: Excel file contains four columns ProductID Upload, ProductID Remove, CompositionID Upload, CompositionID Remove"]
export const REF_CUST_UPLOAD = ["References and Customers Upload: Excel file contains six columns ProductID Upload, ProductID Remove, CompositionID Upload, CompositionID Remove, CustomerID Upload, CustomerID Remove"]

export const COMP_CREATE_REF_NOTE = COMP_GIFT_UPLOAD.concat(REF_UPLOAD).concat(['References Upload: Excel fields concerning removal (ProductID, CompositionID) will not affect the process'])
export const COMP_CREATE_REF_CUST_NOTE = COMP_GIFT_UPLOAD.concat(REF_CUST_UPLOAD).concat(['References and Customers Upload: Excel fields concerning removal (ProductID, CompositionID, CustomerID) will not affect the process'])
export const COMP_EDIT_REF_NOTE = COMP_GIFT_UPLOAD.concat(COMP_GIFT_REMOVE).concat(REF_UPLOAD)
export const COMP_EDIT_REF_CUST_NOTE = COMP_GIFT_UPLOAD.concat(COMP_GIFT_REMOVE).concat(REF_CUST_UPLOAD)



