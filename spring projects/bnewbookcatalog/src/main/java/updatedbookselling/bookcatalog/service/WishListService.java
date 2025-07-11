package updatedbookselling.bookcatalog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import updatedbookselling.bookcatalog.daoimpl.WishListRepository;
import updatedbookselling.bookcatalog.domain.Book;
import updatedbookselling.bookcatalog.domain.WishList;

@Service
public class WishListService {

    @Autowired
    private WishListRepository wishListRepository;

    public List<WishList> getAllWishLists() {
        return wishListRepository.getAllWishLists();
    }

    public WishList getWishListById(int id) {
        return wishListRepository.getWishListById(id);
    }

    public int addToWishList(WishList wishList) {
        return wishListRepository.addToWishList(wishList);
    }
    
    public  List<Book> getWishListOfUserById(Integer userId) {
    	return wishListRepository.getWishListOfUserById(userId);
    }

    public int deleteWishListById(int id) {
        return wishListRepository.deleteWishListById(id);
    }

    public List<Book> getTopWishListedBook() {
        return wishListRepository.getTopWishListedBook();
    }
    
    public boolean isAlreadyWishListed(WishList wishList) {
    	boolean result = wishListRepository.checkAlreadyWishListed(wishList);
    	System.out.println(result + "from wishlist service layer");
        return result;
    }
}

