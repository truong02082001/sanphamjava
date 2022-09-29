package truongpc.springshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import truongpc.springshop.entity.Product;
import truongpc.springshop.repontory.CategoryRepo;
import truongpc.springshop.repontory.ProductRepo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductRepo productRepo;
    @Autowired
    CategoryRepo categoryRepo;

    @GetMapping("/search")
    public String search(Model model) {
        model.addAttribute("productList", productRepo.findAll());
        return "product/search";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("categoryList", categoryRepo.findAll());
        return "product/create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute Product product,
                         @RequestParam(name = "file", required = false) MultipartFile file) {
        if (file != null && file.getSize() > 0) {
            // co luu lai file vao filder
            final String FOLDER = "D:\\IntrelIJ\\springshop\\src\\main\\resources\\static\\image\\";

            String filename = file.getOriginalFilename();

            java.io.File outFile = new java.io.File(FOLDER + filename);
            try {
                file.transferTo(outFile);
            } catch (Exception e) {
                e.printStackTrace();
            }
            product.setImage(filename);
        }
        productRepo.save(product);
        return "redirect:/product/search";
    }

    @RequestMapping(value = "/download-file")
    public void download(HttpServletRequest request, HttpServletResponse response) {
        String filename = request.getParameter("image");
        String dataDirectory = "D:\\IntrelIJ\\springshop\\src\\main\\resources\\static\\image\\"; // sau image nho de \\ de luu trong folder image
        java.nio.file.Path file = Paths.get(dataDirectory, filename);
        if (Files.exists(file)) {
            response.setContentType("image/jpeg");
            try {
                Files.copy(file, response.getOutputStream());
                response.getOutputStream().flush();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    @GetMapping("/delete")
    public String delete(@RequestParam("id") int id){
        productRepo.deleteById(id);
        return "redirect:/product/search";
    }

    @GetMapping("/edit")
    public String edit(Model model, @RequestParam("id") int id){
        model.addAttribute("product",productRepo.findById(id).orElse(null));
        model.addAttribute("category", categoryRepo.findById(id).orElse(null));
        return"product/edit";
    }
    @PostMapping("/edit")
    public String edit(@ModelAttribute Product product,
                       @RequestParam(name = "file", required = false) MultipartFile file){
        if (file != null && file.getSize() > 0) {
            // co luu lai file vao filder
            final String FOLDER = "D:\\IntrelIJ\\springshop\\src\\main\\resources\\static\\image\\";

            String filename = file.getOriginalFilename();

            java.io.File outFile = new java.io.File(FOLDER + filename);
            try {
                file.transferTo(outFile);
            } catch (Exception e) {
                e.printStackTrace();
            }
            product.setImage(filename);
        }
        productRepo.save(product);
        return "redirect:/product/search";
    }
}
