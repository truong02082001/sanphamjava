package truongpc.springshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import truongpc.springshop.entity.User;
import truongpc.springshop.repontory.UserRepo;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserRepo userRepo;

    @GetMapping("/search")
    public String search(Model model){
        model.addAttribute("userList",  userRepo.findAll());
        return "user/search";
    }

    @GetMapping("/create")
    public String create(){

        return "user/create";
    }
    @PostMapping("/create")
    public String create(@ModelAttribute User user){
        userRepo.save(user);
        return "redirect:/user/search";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") int id){
        userRepo.deleteById(id);
        return "redirect:/user/search";
    }

    @GetMapping("/eidt")
    public String edit(Model model,
            @RequestParam("id") int id){
        model.addAttribute("user",userRepo.findById(id));
        return "user/edit";
    }
    @PostMapping("edit")
    public String edit(@ModelAttribute User user){
        User user1  = userRepo.findById(user.getId()).orElse(null);
        userRepo.save(user1);
        return "redirect:/user/search";
    }
}
