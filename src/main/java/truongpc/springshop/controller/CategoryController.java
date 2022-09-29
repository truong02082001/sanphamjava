package truongpc.springshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import truongpc.springshop.entity.Category;
import truongpc.springshop.repontory.CategoryRepo;

@Controller
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    CategoryRepo categoryRepo;

    @GetMapping("/search")
    public String search(Model model){
        model.addAttribute("categoryList", categoryRepo.findAll());
        return "category/search";
    }

    @GetMapping("/create")
    public String create(){

        return "category/create";
    }
    @PostMapping("/create")
    public String create(@ModelAttribute Category category){
        categoryRepo.save(category);
        return "redirect:/category/search";
    }

    @GetMapping("/delete")
    public String delete(Model model,@RequestParam("id") int id){
        categoryRepo.deleteById(id);
        return "redirect:/category/search";
    }

    @GetMapping("/edit")
    public String edit(Model model, @RequestParam("id") int id){
        model.addAttribute("category",categoryRepo.findById(id).orElse(null));
        return "category/edit";
    }
    @PostMapping("/edit")
    public String edit(@ModelAttribute Category category){
        Category category1 = categoryRepo.findById(category.getId()).orElse(null);
        category1.setName(category.getName());
        categoryRepo.save(category1);
        return "redirect:/category/search";
    }
}
