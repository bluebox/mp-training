import Header from './components/Header';
import Footer from './components/Footer';
import { Container } from 'react-bootstrap';
import HomeScreen from './screens/HomeScreen';
import {BrowserRouter as Router, Route, Routes} from 'react-router-dom';
import ProductScreen from './screens/ProductScreen';
import CartScreen from './screens/CartScreen';
import LoginScreen from './screens/LoginScreen';
import RegisterScreen from './screens/RegisterScreen';
import ProfileScreen from './screens/ProfileScreen';
import ShippingScreen from './screens/ShippingScreen';
import PaymentScreen from './screens/PaymentScreen';
import PlaceOrderScreen from './screens/PlaceOrderScreen';
import UserListScreen from './screens/UserListScreen';
import UserDetailScreen from './screens/UserDetailScreen';
import ProductListScreen from './screens/ProductListScreen';
import ProductEditScreen from './screens/ProductEditScreen';
import ProductCreateScreen from './screens/ProductCreateScreen';
import OrderListScreen from './screens/OrderListScreen';
import OrderScreen from './screens/OrderScreen';
import PageNotFound from './screens/PageNotFound';
import Protected from './components/Protected';
import { createContext, useState } from 'react';

export const SearchKeywordContext = createContext();
export const UsernameContext = createContext();
function App() {
  
  const [searchKeyword, setSearchKeyword] = useState('')
  const [username, setUsername] = useState('')
  return (
    <SearchKeywordContext.Provider value={[searchKeyword, setSearchKeyword]}>
    <UsernameContext.Provider value={[username, setUsername]}>
    <Router>
      <Header/>
      <Container>
        <main>
        <Routes>
          <Route exact path='/' element={<HomeScreen/>}/>
          <Route path='/product/:id' element={<ProductScreen/>}/>
          <Route path='/cart' element={
          <Protected>
            <CartScreen/>
          </Protected>
          }/>
          <Route path='/login/' element={<LoginScreen/>}/>
          <Route path='register' element={<RegisterScreen/>}/>
          <Route path='/profile' element={
          <Protected>
            <ProfileScreen/>
          </Protected>
          }/>
          <Route path='/login/shipping' element={
          <Protected>
            <ShippingScreen/>
          </Protected>
          }/>
          <Route path='/payment/' element={
          <Protected>
            <PaymentScreen/>
          </Protected>
          }/>
          <Route path='/placeorder/' element={
          <Protected>
            <PlaceOrderScreen/>
          </Protected>
          }/>
          <Route path='/admin/userlist' element={
          <Protected>
            <UserListScreen/>
          </Protected>
          }/>
          <Route path='/admin/user/:id' element={
          <Protected>
            <UserDetailScreen/>
          </Protected>
          }/>
          <Route path='/admin/productlist' element={
          <Protected>
            <ProductListScreen/>
          </Protected>
          }/>
          <Route path='/admin/product/:id' element={
          <Protected>
            <ProductEditScreen/>
          </Protected>
          }/>
          <Route path='/admin/product/create' element={
          <Protected>
            <ProductCreateScreen/>
          </Protected>
          }/>
          <Route path='/admin/orders' element={
          <Protected>
            <OrderListScreen/>
          </Protected>
          }/>
          <Route path='/order/:id' element={
          <Protected>
            <OrderScreen/>
          </Protected>
          }/>
          <Route path="*" element={<PageNotFound/>}/>
        </Routes> 
        </main>
      </Container>
      <Footer/>
    </Router>
    </UsernameContext.Provider>
    </SearchKeywordContext.Provider>
  );
}

export default App;
