package com.treeapp.controller;

import com.treeapp.dao.TreeDao;
import com.treeapp.model.Tree;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class TreeController {

    private TreeDao treeDao = new TreeDao();

    @RequestMapping("/")
    public String home(Model model) {
        model.addAttribute("trees", treeDao.getAllTrees());
        return "index";
    }

    @RequestMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("tree", new Tree());
        return "addTree";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveTree(@ModelAttribute Tree tree) {
        treeDao.addTree(tree);
        return "redirect:/";
    }

    @RequestMapping("/edit/{id}")
    public String editForm(@PathVariable int id, Model model) {
        model.addAttribute("tree", treeDao.getTreeById(id));
        return "editTree";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateTree(@ModelAttribute Tree tree) {
        treeDao.updateTree(tree);
        return "redirect:/";
    }

    @RequestMapping("/delete/{id}")
    public String deleteTree(@PathVariable int id) {
        treeDao.deleteTree(id);
        return "redirect:/";
    }
}