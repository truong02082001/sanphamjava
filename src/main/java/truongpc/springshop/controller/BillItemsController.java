package truongpc.springshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import truongpc.springshop.entity.BillItems;
import truongpc.springshop.repontory.BillItemsRepo;
import truongpc.springshop.repontory.BillRepo;
import truongpc.springshop.repontory.ProductRepo;

@Controller
@RequestMapping("/billitems")
public class BillItemsController {
    @Autowired
    BillItemsRepo billItemsRepo;
    @Autowired
    ProductRepo productRepo;
    @Autowired
    BillRepo billRepo;

    @GetMapping("/search")
    public String search(Model model){
        model.addAttribute("billitemsList", billItemsRepo.findAll());
        return"billitems/search";
    }
    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("productList", productRepo.findAll());
        model.addAttribute("billList", billRepo.findAll());
        return "billItems/create";
    }
    @PostMapping("/create")
    public String create(@ModelAttribute BillItems billItems) {
       billItemsRepo.save(billItems);
       return "redirect:/billItems/search";
    }
    @GetMapping("/delete")
    public String delete(@RequestParam("id") int id){
        billItemsRepo.deleteById(id);
        return"redirect:/billitems/search";
    }
    @GetMapping("/edit")
    public String edit(Model model, @RequestParam("id") int id){
        model.addAttribute("billitems", billItemsRepo.findById(id).orElse(null));
        model.addAttribute("bill", billRepo.findById(id).orElse(null));
        model.addAttribute("product",productRepo.findById(id).orElse(null));
        return "billitems/edit";
    }
    @PostMapping("/edit")
    public String edit(@ModelAttribute BillItems billItems){
        billItemsRepo.save(billItems);
        return "redirect:/billitems/search";
    }
}
