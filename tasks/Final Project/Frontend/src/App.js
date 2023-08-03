import Header from './components/Header'
import Footer from './components/Footer' 
import {BrowserRouter as Router, Routes, Route} from 'react-router-dom'
import HomeScreen from './screens/HomeScreen' 
import MovieScreen from './screens/MovieScreen'
import WishlistScreen from './screens/WishlistScreen'
import PaymentScreen from './screens/PaymentScreen'
import TicketBookedScreen from './screens/TicketBookedScreen'
import LoginScreen from './screens/LoginScreen'
import RegisterScreen from './screens/RegisterScreen'
import ProfileScreen from './screens/ProfileScreen'
import BookingScreen from './screens/BookingScreen'
import UserListScreen from './screens/UserListScreen'
import UserEditScreen from './screens/UserEditScreen'
import MovieListScreen from './screens/MovieListScreen'
import MovieEditScreen from './screens/MovieEditScreen'
import BookingOrderScreen from './screens/BookingOrderScreen'

function App() {
  return (
    <Router>
      <Header />
        <main className="py-3">
          <Routes>
            <Route exact path="/" element={<HomeScreen/>} />
            <Route path="/login" element={<LoginScreen/>}  /> 
            <Route path="/register" element={<RegisterScreen/>}  />
            <Route path="/profile" element={<ProfileScreen/>}  /> 
            <Route path="/booking" element={<BookingScreen/>}  />
            <Route path="/ticketbooked" element={<TicketBookedScreen/>}  />
            <Route path="/booking/:id" element={<BookingOrderScreen/>}  />
            <Route path="/payment" element={<PaymentScreen/>}  />      
            <Route path="/product/:id" element={<MovieScreen/>} />
            <Route path="/wishlist/:id?" element={<WishlistScreen/>} />
            <Route path="/admin/userlist" element={<UserListScreen/>} />
            <Route path="/admin/user/:id" element={<UserEditScreen/>} />
            <Route path="/admin/movielist" element={<MovieListScreen/>} />
            <Route path="/admin/movie/:id" element={<MovieEditScreen/>} />
            <Route path="/admin/movie/:id" element={<MovieEditScreen/>} />
          </Routes>
        </main>
      <Footer />
    </Router>
  );
}

export default App;
