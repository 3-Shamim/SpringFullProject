package com.learningstuff.onlineaddressbook.Service;

import com.learningstuff.onlineaddressbook.Model.AddressBook;
import com.learningstuff.onlineaddressbook.Model.User;
import com.learningstuff.onlineaddressbook.Repository.AddressBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;

@Service
public class AddressBookService {

    @Autowired
    private AddressBookRepository addressBookRepository;

    public List<AddressBook> getAllAddress()
    {
        return addressBookRepository.findAll();
    }

    public void addAddressBook(User user, AddressBook addressBook)
    {
        addressBook.setUser(user);
        addressBookRepository.save(addressBook);
    }

    public List<AddressBook> findUserAddressBook (User user, String name){
        return addressBookRepository.findAddressBookByUserAndNameIsLike(user, "%" +name+ "%");
    }


    public void deleteAddressBookById(long id) {

        addressBookRepository.deleteById(id);
    }

    public AddressBook findAddressBookById(long id) {

        return addressBookRepository.getOne(id);
    }

    public void updateAddressBook(AddressBook addressBook) {
        addressBookRepository.save(addressBook);
    }
}
