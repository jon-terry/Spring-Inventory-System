package com.example.demo.controllers;

import com.example.demo.domain.InhousePart;
import com.example.demo.service.InhousePartService;
import com.example.demo.service.InhousePartServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import javax.validation.Valid;

/**
 *
 *
 *
 *
 */
@Controller
public class AddInhousePartController{
    @Autowired
    private ApplicationContext context;

    @GetMapping("/showFormAddInPart")
    public String showFormAddInhousePart(Model theModel){
        InhousePart inhousepart=new InhousePart();
        theModel.addAttribute("inhousepart",inhousepart);
        return "InhousePartForm";
    }

    @PostMapping("/showFormAddInPart")
    public String submitForm(@Valid @ModelAttribute("inhousepart") InhousePart part, BindingResult theBindingResult, Model theModel){
        theModel.addAttribute("inhousepart",part);

        theModel.addAttribute("inv", part.getInv());
        theModel.addAttribute("price", part.getPrice());
        theModel.addAttribute("minInv", part.getMinInv());
        theModel.addAttribute("maxInv", part.getMaxInv());


        if (part.isInventoryInvalid()) {
            theModel.addAttribute("errorMessage", "Inventory is not valid.");
            if (part.getInv() < part.getMinInv()) {
                theBindingResult.rejectValue("minInv", "minInv.error");
            }

            if (part.getInv() > part.getMaxInv()) {
                theBindingResult.rejectValue("maxInv", "maxInv.error");
            }

            if (part.getInv() < part.getMinInv() || part.getInv() > part.getMaxInv()) {
                theBindingResult.rejectValue("inv", "inv.error");
            }


            if (theBindingResult.hasErrors()) {
                // Testing
                if (part.getInv() < part.getMinInv()) {
                    theModel.addAttribute("minInvError", "The inventory amount is under the minimum inventory amount.");
                }
                if (part.getInv() > part.getMaxInv()) {
                    theModel.addAttribute("maxInvError", "The inventory amount is over the maximum inventory amount.");
                }
                if (part.getInv() < part.getMinInv() || part.getInv() > part.getMaxInv()) {
                    theModel.addAttribute("invError", "Inventory error. Please check all inventory values (Inventory, Min, and Max).");
                }
                //testing, restore below if did not work
                //return "errorpage";

                return "InhousePartForm";

            }
                InhousePartService repo = context.getBean(InhousePartServiceImpl.class);
                InhousePart ip = repo.findById((int) part.getId());
                if (ip != null) part.setProducts(ip.getProducts());
                repo.save(part);

                return "confirmationaddpart";
            }
        InhousePartService repo = context.getBean(InhousePartServiceImpl.class);
        InhousePart ip = repo.findById((int) part.getId());
        if (ip != null) part.setProducts(ip.getProducts());
        repo.save(part);

        return "confirmationaddpart";

        }
    }


