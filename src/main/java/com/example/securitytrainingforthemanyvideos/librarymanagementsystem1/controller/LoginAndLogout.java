package com.example.securitytrainingforthemanyvideos.librarymanagementsystem1.controller;

import com.example.securitytrainingforthemanyvideos.librarymanagementsystem1.model.Books;
import com.example.securitytrainingforthemanyvideos.librarymanagementsystem1.model.Type;
import com.example.securitytrainingforthemanyvideos.librarymanagementsystem1.model.UserInfo;
import com.example.securitytrainingforthemanyvideos.librarymanagementsystem1.service.implementation.UserInfoImpl;
import com.example.securitytrainingforthemanyvideos.librarymanagementsystem1.service.interfaces.BooksService;
import com.example.securitytrainingforthemanyvideos.librarymanagementsystem1.service.interfaces.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class LoginAndLogout {
    @Autowired
    private UserInfoImpl service;
    @Autowired
    private BooksService bookService;
    @Autowired
    private TypeService typeService;
    @GetMapping("/welcome")
    public String welcomePage(){
        return "welcom";
    }
    @GetMapping("/")
    public  String homePage(@ModelAttribute("book") Books book, Model model){
        List<Books> bookList = bookService.getAllBooks();
        model.addAttribute("bookList", bookList);
        List<Type> typeList = typeService.getAllType();
        model.addAttribute("typeList", typeList);

        return "book";
    }



    @GetMapping("/login")
    public String login(){
        return "login";
    }
    @PostMapping("/admin/new")
    public String addNewUser(@RequestParam("password")String pass, @ModelAttribute("userInfo") UserInfo userInfo){
        userInfo.setPassword(pass);
        service.addUser(userInfo);
        return "redirect:/admin/userInfoPage";

    }
    @GetMapping("/admin/register")
    public String showAddNewUser(@ModelAttribute("userInfo") UserInfo userInfo){

        return "userInfo";
    }
}
