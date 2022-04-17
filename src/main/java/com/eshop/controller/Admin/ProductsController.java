package com.eshop.controller.Admin;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.eshop.dto.UpdateProductDto;
import com.eshop.entities.Category;
import com.eshop.entities.Discount;
import com.eshop.entities.Product;
import com.eshop.service.CategoryService;
import com.eshop.service.DiscountService;
import com.eshop.service.ProductService;

@Controller
@RequestMapping("/dashboard/products")
public class ProductsController {
    @Autowired
    ProductService productService;
    @Autowired
    DiscountService discountService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    ModelMapper modelMapper;

    @GetMapping("")
    public String getProducts(Model model) {
        List<Discount> discounts = discountService.findAll();
        List<Product> products = productService.findAll();
        List<Category> categories = categoryService.findAll();
        model.addAttribute("discounts", discounts);
        model.addAttribute("categories", categories);
        model.addAttribute("productRequest", new UpdateProductDto());
        model.addAttribute("products", products);
        return "site/admin/product/manager-products";
    }

    @PostMapping("/save")
    public String doPostSaveProduct(@Valid @ModelAttribute("productRequest") UpdateProductDto productRequest,
                                    BindingResult errors, RedirectAttributes redirectAttributes) {
        String message = null;
        try {
            if (errors.hasErrors()) {
                message = "Vui lòng nhập đầy đủ thông tin sản phẩm";
                redirectAttributes.addFlashAttribute("errorMessage", message);
            }
            if(productRequest.getDiscount() == null) {
                productRequest.setDiscount(null);
            }
            this.uploadFile(productRequest,productRequest.getImageFile());
            Product productMapper = modelMapper.map(productRequest, Product.class);
            productService.save(productMapper);
            message = "Thêm mới sản phẩm thành công";
            redirectAttributes.addFlashAttribute("succeedMessage", message);
        } catch (Exception e) {
            e.printStackTrace();
            message = "Thêm sản phẩm thất bại";
            redirectAttributes.addFlashAttribute("errorMessage", message);
        }
        return "redirect:/dashboard/products";
    }

    @RequestMapping("/delete")
    public String doGetDelete(@RequestParam("productId") String productId, RedirectAttributes redirectAttributes) {
        try {
            productService.deleteLogical(Integer.valueOf(productId));
            redirectAttributes.addFlashAttribute("succeedMessage", "ProductId " + productId + "has been deleted");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/dashboard/products";
    }

    @RequestMapping("/edit")
    public String doGetEditProduct(@RequestParam("productId") String productId, Model model) {
        List<Discount> discounts = discountService.findAll();
        Product productRequest = productService.findById(Integer.valueOf(productId));
        model.addAttribute("discounts", discounts);
        model.addAttribute("productRequest", productRequest);
        List<Product> products = productService.findAll();
        model.addAttribute("products", products);
        return "site/admin/product/manager-products";
    }

    @RequestMapping("/active")
    public String doGetActive(@RequestParam("productId") String productId) {
        try {
            productService.activeProduct(Integer.valueOf(productId));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/dashboard/products";
    }

    public void uploadFile(@Valid UpdateProductDto product, MultipartFile photoFile) {
        if (photoFile.getOriginalFilename() != null && !photoFile.getOriginalFilename().isBlank()) {
            try {
                String path = new File("src/main/resources/static/assets/images/product/").getAbsolutePath();
                String fileName = product.getSlug() + "." + photoFile.getContentType().split("/")[1];
                product.setImage(fileName);
                photoFile.transferTo(new File(path + "/" + fileName));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
