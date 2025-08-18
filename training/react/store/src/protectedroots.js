import React, { Children } from "react";
import { Navigate } from "react-router-dom";


export default function Protect({children}){
const role=localStorage.getItem('role')
const access=localStorage.getItem('access')
const refresh=localStorage.getItem('refresh')
const child=children.type.name

if (!access || !refresh ){
return <Navigate to="/" replace />;
}
else if(role === 'admin'){
   if (['AdminHomePage','CustomerRelated','BooksPage','Authorrelatedpage'].includes(child)){
    return children}
    
   return <Navigate to="/" replace />;

 
 }
 else if (role === 'customer'){
   if (['CustomerPage','MyOrders','MyProfile'].includes(child)){
    return children}
    return <Navigate to="/" replace />;
 }
 else if ( role === 'author'){
   if (['AuthorsPage','Handlebookedit','MyProfileAuthors'].includes(child)){
    return children}
   return <Navigate to="/" replace />;
 }
 else{
   return children
 }
 

};