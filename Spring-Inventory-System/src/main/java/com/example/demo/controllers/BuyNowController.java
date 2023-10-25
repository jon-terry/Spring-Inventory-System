package com.example.demo.controllers;
import com.example.demo.domain.Product;
import com.example.demo.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.Optional;

@Controller

public class BuyNowController {

    @Autowired
    private ProductRepository productRepository;

    private boolean checkValue = false;

    @PostMapping("/buyProduct")
    public String buyProduct(@RequestParam("productID") Long productID, RedirectAttributes attributes) {

        Optional<Product> productInit = productRepository.findById(productID);
        Product product = new Product();

        if (productInit.isPresent()) {
            product = productInit.get();
        }

        if (product.getInv() > 0) {
            int amount = product.getInv();
            product.setInv(amount - 1);
            productRepository.save(product);
            checkValue = true;
        }

        if (checkValue) {
            return "redirect:/successpage";

        } else {

            if (!productInit.isPresent()) {
                    attributes.addFlashAttribute("error1", "Product not found.");
                    return "redirect:/errorpage";
            }

            if (product.getInv() <= 0) {
                attributes.addFlashAttribute("error2", "Product out of stock.");
                //return "redirect:/errorpage";
                return "redirect:/errorpage";
            }


            //return "redirect:/errorpage";
            return "redirect:/errorpage";
        }

    }

    @GetMapping("/successpage")
    public String returnSuccess(){return "successpage";}

    @GetMapping("/errorpage")
    public String returnError(@ModelAttribute("error") String error, Model model){
        model.addAttribute("error", error);
        return "errorpage";
    }

}
