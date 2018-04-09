package com.learningstuff.librarymanagentsystem.Controller;

import com.learningstuff.librarymanagentsystem.Model.Book;
import com.learningstuff.librarymanagentsystem.Model.Dao.BookDao;
import com.learningstuff.librarymanagentsystem.Model.Dao.IssuedBookHistoryDao;
import com.learningstuff.librarymanagentsystem.Model.Dao.ReturnBookHistoryDao;
import com.learningstuff.librarymanagentsystem.Model.Dao.UserDao;
import com.learningstuff.librarymanagentsystem.Model.IssueBookHistory;
import com.learningstuff.librarymanagentsystem.Model.ReturnBookHistory;
import com.learningstuff.librarymanagentsystem.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "admin")
public class AdminController {

    @Autowired
    private BookDao bookDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private IssuedBookHistoryDao issuedBookHistoryDao;

    @Autowired
    private ReturnBookHistoryDao returnBookHistoryDao;


    @RequestMapping(value = "")
    public String adminPanel(Model model)
    {
        List<Book> totalBook = new ArrayList<>();
        List<User> totalUser = new ArrayList<>();

        bookDao.findAll().forEach(book -> totalBook.add(book)); // Lamda Expression
        userDao.findAll().forEach(totalUser::add); // Lamda with reference method
        List<User> totalIssuedBook = totalUser.stream().filter(user -> user.getBook() != null).collect(Collectors.toList());
        model.addAttribute("totalBook", totalBook.size());
        model.addAttribute("totalUser", totalUser.size());
        model.addAttribute("totalIssuedBook", totalIssuedBook.size());
        model.addAttribute("title", "Dash Board");
        return "admin/adminDashBoard";
    }

    // Book Controller Process
    @RequestMapping(value = "addBook", method = RequestMethod.GET)
    public String addBook(Model model)
    {
        model.addAttribute("book",new Book());
        model.addAttribute("title","Add Book");
        return "admin/addBook";
    }
    @RequestMapping(value = "addBook", method = RequestMethod.POST)
    public String processAddBook(@ModelAttribute @Valid Book book, Errors errors, Model model)
    {
        if (errors.hasErrors())
        {
            model.addAttribute("title","Add Book");
            return "admin/addBook";
        }
        if(book.getQuantity() > 0)
        {
            book.setActivity(true);
        }

        bookDao.save(book);
        return "redirect:/admin/bookList";
    }
    @RequestMapping(value = "bookList")
    public String bookList(Model model)
    {
        model.addAttribute("bookList",bookDao.findAll());
        model.addAttribute("title","List Of Book");
        return "admin/BookList";
    }
    @RequestMapping(value = "/bookEdit/{bookId}", method = RequestMethod.GET)
    public String editBook(@PathVariable int bookId,Model model)
    {
        Book book = bookDao.findOne(bookId);
        model.addAttribute("title", "Edit Book");
        model.addAttribute(book);

        return "admin/updateBook";
    }
    @RequestMapping(value = "/bookEdit/{bookId}", method = RequestMethod.POST)
    public String processEditBook(@ModelAttribute @Valid Book book, Errors errors, Model model)
    {
        if(errors.hasErrors())
        {
            model.addAttribute("title", "Edit Book");
            return "admin/updateBook";
        }
        if(book.getQuantity() > 0)
        {
            book.setActivity(true);
        }
        bookDao.save(book);
        return "redirect:/admin/bookList";
    }
    @RequestMapping(value = "/bookDelete/{bookId}")
    public String deleteBook(@PathVariable int bookId)
    {
        bookDao.delete(bookId);
        return "redirect:/admin/bookList";
    }

    // User Controller Process
    @RequestMapping(value = "addUser", method = RequestMethod.GET)
    public String addUser(Model model)
    {
        model.addAttribute("user",new User());
        model.addAttribute("title","Add User");
        return "admin/addUser";
    }
    @RequestMapping(value = "addUser", method = RequestMethod.POST)
    public String processAddUser(@ModelAttribute @Valid User user, Errors errors, Model model)
    {
        if (errors.hasErrors())
        {
            model.addAttribute("title","Add User");
            return "admin/addUser";
        }
        for (User user1: userDao.findAll()) {
            if (user.getUserId().equals(user1.getUserId()))
            {
                model.addAttribute("title","Add User");
                model.addAttribute("userNameExistError", "This user name already exist !");
                return "admin/addUser";
            }
        }
        userDao.save(user);
        return "redirect:/admin/userList";
    }
    @RequestMapping(value = "userList")
    public String userList(Model model)
    {
        model.addAttribute("userList",userDao.findAll());
        model.addAttribute("title","List Of User");
        return "admin/userList";
    }
    @RequestMapping(value = "/userEdit/{userId}", method = RequestMethod.GET)
    public String editUser(@PathVariable String userId, Model model)
    {
        User user = userDao.findOne(userId);
        model.addAttribute("title","Edit User");
        model.addAttribute(user);
        return "admin/updateUser";
    }
    @RequestMapping(value = "/userEdit/{userId}", method = RequestMethod.POST)
    public String processEditUser(@ModelAttribute @Valid User user, Errors errors, Model model)
    {
        if(errors.hasErrors())
        {
            model.addAttribute("title","Edit User");
            return "admin/updateUser";
        }

        userDao.save(user);
        return "redirect:/admin/userList";
    }
    @RequestMapping(value = "/userDelete/{userId}")
    public String deleteUser(@PathVariable String userId)
    {
        userDao.delete(userId);
        return "redirect:/admin/userList";
    }
    // Issue Book
    @RequestMapping(value = "issueBook",method = RequestMethod.GET)
    public String issueBook(Model model)
    {
        model.addAttribute("title","Choose User");
        model.addAttribute("searchField", null);
        return "admin/issueBook";
    }
    @RequestMapping(value = "issueBook", method = RequestMethod.POST)
    public String processIssueBook(@RequestParam(value = "userSearch", required = false) String userSearch ,Model model)
    {
        if(userSearch == null || userSearch.trim() == "")
        {
            model.addAttribute("title","Choose User");
            model.addAttribute("searchFieldCheck", "Field must not be empty !");
            return "admin/issueBook";
        }

        User user = userDao.findOne(userSearch);
        if (user == null)
        {
            model.addAttribute("title", "Choose User");
            model.addAttribute("bookStatus", "Invalid User Id !");
            return "admin/issueBook";
        }
        model.addAttribute("userId", user.getUserId());
        model.addAttribute("userName", user.getUserName());
        model.addAttribute("address", user.getAddress());
        model.addAttribute("email", user.getEmail());
        model.addAttribute("phoneNumber", user.getPhoneNumber());

        if(user.getBook() != null)
        {
            model.addAttribute("bookStatus", "You already have a book, please return previous one !");
            model.addAttribute("BookName", user.getBook().getBookName());
        }

        model.addAttribute("title","Choose User");
        return "admin/issueBook";
    }

    @RequestMapping(value = "bookIssuedForUser/{userId}")
    public String bookIssuedForUser(@PathVariable String userId, Model model)
    {
        model.addAttribute("title", "Choose a book");
        model.addAttribute("userId", userId);
        return "admin/bookIssuedForUser";
    }
    @RequestMapping(value = "bookIssuedForUsers/{userId}")
    public String processBookIssuedForUser(@RequestParam(value = "bookSearch") String bookSearch,
                                           @PathVariable String userId, Model model)
    {
        if(bookSearch == null || bookSearch.trim() == "")
        {
            model.addAttribute("title", "Choose a book");
            model.addAttribute("searchFieldCheck", "Field must not be empty !");
            return "admin/bookIssuedForUser";
        }

        if (bookSearch.matches("\\d+") == false)
        {
            model.addAttribute("title", "Choose a book");
            model.addAttribute("bookStatus", "Invalid Book Id !");
            return "admin/bookIssuedForUser";
        }
        int bookId = Integer.parseInt(bookSearch);
        Book book = bookDao.findOne(bookId);
        if (book == null)
        {
            model.addAttribute("title", "Choose a book");
            model.addAttribute("bookStatus", "Invalid Book Id !");
            return "admin/bookIssuedForUser";
        }

        model.addAttribute("bookId", book.getBookId());
        model.addAttribute("bookName", book.getBookName());
        model.addAttribute("author", book.getAuthor());
        model.addAttribute("edition", book.getEdition());
        model.addAttribute("quantity", book.getQuantity());
        model.addAttribute("activity", book.isActivity());

        if(book.isActivity() == false)
        {
            model.addAttribute("title", "Choose a book");
            model.addAttribute("bookStatus", "Book is not available in this moment !");
            return "admin/bookIssuedForUser";
        }
        model.addAttribute("userId",userId);
        model.addAttribute("title", "Choose a book");
        return "admin/bookIssuedForUser";
    }

    @RequestMapping(value = "addBookForUser/{userId}/{bookId}")
    public String addBookForUser(@PathVariable String userId , @PathVariable String bookId, Model model)
    {
        User user = userDao.findOne(userId);
        int BookId = Integer.parseInt(bookId);
        Book book = bookDao.findOne(BookId);
        int quantity = book.getQuantity() - 1;
        book.setQuantity(quantity);
        if(quantity == 0)
        {
            book.setActivity(false);
        }
        user.setBook(book);
        userDao.save(user);
        bookDao.save(book);

        issuedBookHistoryDao.save(new IssueBookHistory(user.getUserId(),
                user.getUserName(),
                book.getBookId(),
                book.getBookName(),
                LocalDate.now()));
       // model.addAttribute("bookStatus","Book name = " + book.getBookName() + " is issued for user = " + user.getUserName());
        return "redirect:/admin/issueBook";
    }

    //Return Book
    @RequestMapping(value = "returnBook", method = RequestMethod.GET)
    public String returnBook(Model model)
    {
        model.addAttribute("title", "Return Book");
        return "admin/returnBook";
    }
    @RequestMapping(value = "returnBook", method = RequestMethod.POST)
    public String processReturnBook(@RequestParam String userSearch, Model model)
    {
        if(userSearch == null || userSearch.trim() == "")
        {
            model.addAttribute("title","Return Book");
            model.addAttribute("searchFieldCheck", "Field must not be empty !");
            return "admin/returnBook";
        }

        User user = userDao.findOne(userSearch);
        if (user == null)
        {
            model.addAttribute("title", "Return Book");
            model.addAttribute("bookStatus", "Invalid User Id !");
            return "admin/returnBook";
        }
        model.addAttribute("userId", user.getUserId());
        model.addAttribute("userName", user.getUserName());
        model.addAttribute("email", user.getEmail());
        model.addAttribute("address", user.getAddress());

        if(user.getBook() == null)
        {
            model.addAttribute("bookStatus", "You didn't take any book before !");
            model.addAttribute("title", "Return Book");
            return "admin/returnBook";
        }

        model.addAttribute("bookId", user.getBook().getBookId());
        model.addAttribute("bookName", user.getBook().getBookName());
        model.addAttribute("author",user.getBook().getAuthor());
        model.addAttribute("edition",user.getBook().getEdition());

        model.addAttribute("BookName", user.getBook().getBookName());
        model.addAttribute("title", "Return Book");
        return "admin/returnBook";
    }

    @RequestMapping(value = "bookReturnForUser/{userId}")
    public String bookReturnForUser(@PathVariable String userId)
    {
        User user = userDao.findOne(userId);
        returnBookHistoryDao.save(new ReturnBookHistory(
                user.getUserId(),
                user.getUserName(),
                user.getBook().getBookId(),
                user.getBook().getBookName(),LocalDate.now()));
        Book book = user.getBook();
        if(book.getQuantity() == 0)
        {
            book.setActivity(true);
        }

        book.setQuantity(book.getQuantity() + 1);
        bookDao.save(book);
        user.setBook(null);
        userDao.save(user);
        return "redirect:/admin/returnBook";
    }

    // Issue and Return History

    @RequestMapping(value = "issueHistory")
    public String issueHistory(Model model)
    {
        model.addAttribute("title","Issued Book History");
        model.addAttribute("issueHistory", issuedBookHistoryDao.findAll());
        return "admin/issueHistory";
    }
    @RequestMapping(value = "returnHistory")
    public String returnHistory(Model model)
    {
        model.addAttribute("title","Return Book History");
        model.addAttribute("returnHistory", returnBookHistoryDao.findAll());
        return "admin/returnHistory";
    }

}
