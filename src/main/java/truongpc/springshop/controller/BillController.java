package truongpc.springshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import truongpc.springshop.entity.Bill;
import truongpc.springshop.repontory.BillRepo;
import truongpc.springshop.repontory.UserRepo;

import java.util.Date;

@Controller
@RequestMapping("/Bill")
public class BillController {
    @Autowired
    BillRepo billRepo;
    @Autowired
    UserRepo userRepo;

    @GetMapping("/search")
    public String search(Model model){
        model.addAttribute("billList", billRepo.findAll());
        return "bill/search";
    }
    @GetMapping("/create")
    public String create(Model model){
        model.addAttribute("userList", userRepo.findAll());
        return "bill/create";
    }
    @PostMapping("/create")
    public String create(@ModelAttribute Bill bill,
                         @RequestParam(name = "date") @DateTimeFormat(pattern = "YYYY-MM-dd") Date date){
        bill.setByDate(date);
        billRepo.save(bill);
        return "redirect:/bill/search";
    }
    @GetMapping("/delete")
    public String delete(@RequestParam("id") int id){
        billRepo.deleteById(id);
        return "redirect:/bill/search";
    }
    @GetMapping("/edit")
    public String edit(Model model, @RequestParam("id") int id) {
        model.addAttribute("bill", billRepo.findById(id).orElse(null));
        model.addAttribute("user", userRepo.findById(id).orElse(null));
        return "bill/edit";
    }
    @PostMapping("/edit")
    public String edit(@ModelAttribute Bill bill,
                       @RequestParam(name = "date") @DateTimeFormat(pattern = "YYYY-MM-dd") Date date){
        bill.setByDate(date);
        billRepo.save(bill);
        return "redirect:/bill/search";
    }
}
