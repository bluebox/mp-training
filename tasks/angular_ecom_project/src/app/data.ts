export interface Customer{
    name: string;
    mail: string;
    address: string;
    ph: Number;
    city: string;
    country: string;
    pincode: Number;
}

export interface CustomerGroup{
    cust_id: number;
    uname: string;
    f_name: string;
    l_name: string;
    mail: string;
    password: string;
}

export interface Emp{
    id: number;
    emp_name: string;
    emp_age: number;
    emp_number: number;
    emp_address:string;
}


export interface Product{
    image: ImageData;
    name: string;
    price: number;  
    stock: number;
    product_type: string;
}


export interface product_type{
    name: string;
    image:ImageData
}


export interface Login{
    username : string;
    email: string;
    password: string;

}