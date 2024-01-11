package com.ra.controller;

import com.ra.model.entity.Category;
import com.ra.model.entity.Product;
import com.ra.service.CategoryService;
import com.ra.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
public class ProductController {
    @Value("${path-upload}")
    private String pathUpload;
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/product")
    public String product(Model model){
        List<Product> products = productService.getAll();
        model.addAttribute("products",products);
        return "product/index";
    }

    @GetMapping("/add-product")
    public String addProduct(Model model){
        List<Category> categories = categoryService.getAll();
        model.addAttribute("categories",categories);
        Product product = new Product();
        model.addAttribute("product",product);
        return "product/add";
    }

    @PostMapping("/add-product")
    public String createProduct(
            @ModelAttribute("product") Product product,
            @RequestParam("img")MultipartFile file
            ){
        // upload file
        String fileName = file.getOriginalFilename();
        try {
            FileCopyUtils.copy(file.getBytes(),new File(pathUpload+fileName));
            // lưu tên file vào database
            product.setImage(fileName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        productService.saveOrUpdate(product);
        return "redirect:/product";
    }
}
