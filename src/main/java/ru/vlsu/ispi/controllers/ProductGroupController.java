package ru.vlsu.ispi.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.vlsu.ispi.dto.productGroupDAO.CreateProductGroupDAO;
import ru.vlsu.ispi.models.ProductGroup;
import ru.vlsu.ispi.services.ProductGroupService;

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
    public String create(@ModelAttribute("createProductGroupDAO") CreateProductGroupDAO createProductGroupDAO)
    {
        ProductGroup productGroup = new ProductGroup();
        productGroup.setName(createProductGroupDAO.getName());
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
        model.addAttribute("productGroup", productGroupService.show(id));
        return "productGroup/edit.html";
    }
    @PostMapping("/update/{id}")
    public String update(@ModelAttribute("productGroup") ProductGroup productGroup, @PathVariable("id") Long id)
    {
//        productGroup.setCharacteristics(productGroupService.show(id).getCharacteristics());
        productGroupService.save(productGroup);
        return "redirect:/productGroups";
    }
}
