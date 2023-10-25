package com.example.demo.controllers;

import com.example.demo.domain.OutsourcedPart;
import com.example.demo.domain.Part;
import com.example.demo.service.OutsourcedPartService;
import com.example.demo.service.OutsourcedPartServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.validation.Valid;

/**
 *
 *
 *
 *
 */
@Controller
public class AddOutsourcedPartController {
    @Autowired
    private ApplicationContext context;

    @GetMapping("/showFormAddOutPart")
    public String showFormAddOutsourcedPart(Model theModel){
        Part part=new OutsourcedPart();
        theModel.addAttribute("outsourcedpart",part);
        return "OutsourcedPartForm";
    }

    @PostMapping("/showFormAddOutPart")
    public String submitForm(@Valid @ModelAttribute("outsourcedpart") OutsourcedPart part, BindingResult bindingResult, Model theModel, @RequestParam("minInv") int minInv,
                             @RequestParam("maxInv") int maxInv, @RequestParam ("inv") int inv) {
        theModel.addAttribute("outsourcedpart", part);

        //Testing
        theModel.addAttribute("inv", part.getInv());
        theModel.addAttribute("price", part.getPrice());
        theModel.addAttribute("minInv", part.getMinInv());
        theModel.addAttribute("maxInv", part.getMaxInv());
        //


        if (part.isInventoryInvalid()) {
            theModel.addAttribute("errorMessage", "Inventory is not valid.");
            if (part.getInv() < part.getMinInv()) {
                bindingResult.rejectValue("minInv", "minInv.error");
            }

            if (part.getInv() > part.getMaxInv()) {
                bindingResult.rejectValue("maxInv", "maxInv.error");
            }

            if (part.getInv() < part.getMinInv() || part.getInv() > part.getMaxInv()) {
                bindingResult.rejectValue("inv", "inv.error");
            }


            if (bindingResult.hasErrors()) {
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

                    return "OutsourcedPartForm";

                }
                OutsourcedPartService repo = context.getBean(OutsourcedPartServiceImpl.class);
                OutsourcedPart op = repo.findById((int) part.getId());
                if (op != null) part.setProducts(op.getProducts());
                repo.save(part);
                return "confirmationaddpart";

            }

            OutsourcedPartService repo = context.getBean(OutsourcedPartServiceImpl.class);
            OutsourcedPart op = repo.findById((int) part.getId());
            if (op != null) part.setProducts(op.getProducts());
            repo.save(part);

            return "confirmationaddpart";

        }



}