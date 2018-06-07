package com.learningstuff.onlineaddressbook.Controller;


import com.learningstuff.onlineaddressbook.Model.AddressBook;
import com.learningstuff.onlineaddressbook.Model.User;
import com.learningstuff.onlineaddressbook.Service.AddressBookService;
import com.learningstuff.onlineaddressbook.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class ViewController {

    @Autowired
    private AddressBookService addressBookService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/index")
    public String IndexView(Model model)
    {
        model.addAttribute("title", "Index");
        return "view/Index";
    }

    @RequestMapping(value = "/addresses")
    public String addresses(Model model, Principal principal , @RequestParam(defaultValue = "") String name)
    {
        User user = userService.findByEmail(principal.getName());
        model.addAttribute("addressBook", new AddressBook());
        model.addAttribute("title", "Addresses");
        model.addAttribute("addresses",addressBookService.findUserAddressBook(user, name));
        return "view/Addresses";
    }

    @GetMapping(value = "/addAddress")
    public String addAddress(Model model)
    {
        model.addAttribute("title","addAddress");
        model.addAttribute("addressBook",new AddressBook());
        return "view/AddAddresses";
    }
    @PostMapping(value = "/addAddress")
    public String processAddAddress(@ModelAttribute @Valid AddressBook addressBook, BindingResult bindingResult, Model model, Principal principal)
    {
        if(bindingResult.hasErrors()){
            model.addAttribute("title","addAddress");
            return "view/AddAddresses";
        }

        User user = userService.findByEmail(principal.getName());

        addressBookService.addAddressBook(user , addressBook);

        return "redirect:/addresses";
    }

    @GetMapping(value = "/addressEdit/{id}")
    public String editAddress(@PathVariable(value = "id") long id, Model model)
    {
        AddressBook addressBook = addressBookService.findAddressBookById(id);
        model.addAttribute("title","editAddress");
        model.addAttribute(addressBook);

        return "view/EditAddresses";
    }
    @PostMapping(value = "/addressEdit/{id}")
    public String editAddress(@ModelAttribute @Valid AddressBook addressBook, BindingResult bindingResult, Model model, Principal principal)
    {
        if (bindingResult.hasErrors())
        {
            model.addAttribute("title","editAddress");
            return "view/EditAddresses";
        }

        addressBook.setUser(userService.findByEmail(principal.getName()));

        addressBookService.updateAddressBook(addressBook);

        return "redirect:/addresses";
    }


    @GetMapping(value = "/addressDelete/{id}")
    public String deleteAddress(@PathVariable(value = "id") long id)
    {
        addressBookService.deleteAddressBookById(id);
        return "redirect:/addresses";
    }

    @GetMapping(value = "/users")
    public String Users(Model model, @RequestParam(value = "name", defaultValue = "") String name)
    {
        model.addAttribute("title","Users");
        model.addAttribute("users", userService.getUserByName(name));

        return "view/Users";
    }


}
