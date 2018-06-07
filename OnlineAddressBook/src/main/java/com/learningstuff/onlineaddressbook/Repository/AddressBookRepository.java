package com.learningstuff.onlineaddressbook.Repository;

import com.learningstuff.onlineaddressbook.Model.AddressBook;
import com.learningstuff.onlineaddressbook.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressBookRepository extends JpaRepository<AddressBook, Long> {
    List<AddressBook> findAddressBookByUserAndNameIsLike(User user, String name);
}
