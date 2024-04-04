package ru.vlsu.ispi.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.vlsu.ispi.dto.productGroupDAO.CreateProductGroupDAO;
import ru.vlsu.ispi.dto.productGroupDAO.EditProductGroupDAO;
import ru.vlsu.ispi.models.ProductGroup;
import ru.vlsu.ispi.models.Role;
import ru.vlsu.ispi.services.ProductGroupService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/productGroups")
public class ProductGroupController {
    private final ProductGroupService productGroupService;

    public ProductGroupController(ProductGroupService productGroupService) {
        this.productGroupService = productGroupService;
    }

    @GetMapping
    public String index(Model model)
    {
        model.addAttribute("productGroups", productGroupService.groupList());
        return "productGroup/index";
    }
    @GetMapping("/{id}")
    public String show(@PathVariable Long id, Model model )
    {
        ProductGroup productGroup = productGroupService.show(id);
        model.addAttribute("productGroup", productGroup);
        return "productGroup/show";
    }
    @GetMapping("/new")
    public String newGroup(Model model)
    {
        model.addAttribute("createProductGroupDAO", new CreateProductGroupDAO());
        return "productGroup/new.html";
    }

    @PostMapping()
    public String create( @Valid @ModelAttribute("createProductGroupDAO") CreateProductGroupDAO createProductGroupDAO, BindingResult bindingResult, Model model)
    {
        if(bindingResult.hasErrors())
        {
            model.addAttribute("createProductGroupDAO", new CreateProductGroupDAO());
            return "productGroup/new.html";
        }

        ProductGroup productGroup = new ProductGroup();
        productGroup.setName(createProductGroupDAO.getName());
        productGroup.setSvgIcon(createProductGroupDAO.getSvgIcon());
        productGroupService.save(productGroup);
        return "redirect:/productGroups";
    }
    @PostMapping ("/delete/{id}")
    public String delete(@PathVariable Long id)
    {
        productGroupService.delete(id);
        return "redirect:/productGroups";
    }
    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id)
    {
        EditProductGroupDAO editProductGroupDAO = new EditProductGroupDAO();
        ProductGroup productGroup = productGroupService.show(id);
        editProductGroupDAO.setId(productGroup.getId());
        editProductGroupDAO.setName(productGroup.getName());
        editProductGroupDAO.setSvgIcon(productGroup.getSvgIcon());
        model.addAttribute("editProductGroupDAO", editProductGroupDAO);
        return "productGroup/edit.html";
    }
    @PostMapping("/update")
    public String update( @Valid @ModelAttribute("editProductGroupDAO") EditProductGroupDAO editProductGroupDAO, BindingResult bindingResult, Model model)
    {
        if(bindingResult.hasErrors())
        {
            model.addAttribute("editProductGroupDAO", editProductGroupDAO);
            return "productGroup/edit.html";
        }

        ProductGroup productGroup = new ProductGroup();
        productGroup.setId(editProductGroupDAO.getId());
        productGroup.setName(editProductGroupDAO.getName());
        productGroup.setSvgIcon(editProductGroupDAO.getSvgIcon());
//        productGroup.setCharacteristics(productGroupService.show(id).getCharacteristics());
        productGroupService.save(productGroup);
        return "redirect:/productGroups";
    }
}
