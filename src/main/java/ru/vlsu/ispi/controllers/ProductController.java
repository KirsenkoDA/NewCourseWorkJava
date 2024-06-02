package ru.vlsu.ispi.controllers;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import ru.vlsu.ispi.dto.productDTO.CreateProductDTO;
import ru.vlsu.ispi.dto.productDTO.EditProductDTO;
//import ru.vlsu.ispi.models.Image;
import ru.vlsu.ispi.models.Product;
import ru.vlsu.ispi.repositories.ProductRepository;
//import ru.vlsu.ispi.services.ImageService;
import ru.vlsu.ispi.services.ProductGroupService;
import ru.vlsu.ispi.services.ProductService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;//инжект бина модели
    private final ProductGroupService productGroupService;
//    private final ImageService imageService;
//    private final ProductCharacteristicService productCharacteristicService;
//    private final CharacteristicService characteristicService;
    private final ProductRepository productRepository;

//    public ProductController(ProductService productService, ProductGroupService productGroupService, ImageService imageService, ProductRepository productRepository) {
//        this.productService = productService;
//        this.productGroupService = productGroupService;
//        this.imageService = imageService;
//        this.productRepository = productRepository;
//    }
    public ProductController(ProductService productService, ProductGroupService productGroupService, ProductRepository productRepository) {
        this.productService = productService;
        this.productGroupService = productGroupService;
        this.productRepository = productRepository;
    }
    @GetMapping("/{id}")
    public String show(@PathVariable Long id, Model model )
    {
        Product product = productService.show(id);

        model.addAttribute("product", product);
        model.addAttribute("groups", productGroupService.groupList());
        return "product/show.html";
    }

    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo, Model model) {
        int pageSize = 3;

        Page<Product> page = productService.findPaginated(pageNo - 1, pageSize);
        List< Product > listProducts = page.getContent();
        Product pr = new Product();
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("listProducts", listProducts);
        return "product/index";
    }
//    @GetMapping("/createCharacteristics/{id}")
//    public String createCharacteristics(Model model, @PathVariable("id") Long id)
//    {
//        Product product = productService.show(id);
////        productService.createProductCharacteristics(product);
//        return "redirect:/products/" + Long.toString(id);
//    }
    @GetMapping("/new")
    public String newProduct(Model model)
    {
        model.addAttribute("createProductDTO", new CreateProductDTO());
        model.addAttribute("groups", productGroupService.groupList());
        return "product/new.html";
    }
    @PostMapping()
    public String create(Model model
            , @ModelAttribute("createProductDTO") CreateProductDTO createProductDTO
            , BindingResult bindingResult) throws IOException
    {
        if(bindingResult.hasErrors()) {
            model.addAttribute("createProductDTO", new CreateProductDTO());
            model.addAttribute("groups", productGroupService.groupList());;
            return "product/new.html";
        }
        Product product = new Product();
        product.setName(createProductDTO.getName());
        product.setDiscription(createProductDTO.getDiscription());
        product.setPrice(createProductDTO.getPrice());
        product.setProductGroup(productGroupService.show(createProductDTO.getProductGroupId()));
        product.setPreviewImage(createProductDTO.getPreviewImageUrl());

        productService.save(product);
        return "redirect:/products/page/1";
    }


    @PostMapping ("/delete/{id}")
    public String delete(@PathVariable Long id)
    {
        productService.delete(id);
        return "redirect:/products/page/1";
    }
    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id)
    {
        Product product = productService.show(id);
        EditProductDTO editProductDTO = new EditProductDTO();

        editProductDTO.setId(product.getId());
        editProductDTO.setName(product.getName());
        editProductDTO.setDiscription(product.getDiscription());
        editProductDTO.setQuantity(product.getQuantity());
        editProductDTO.setPrice(product.getPrice());
        editProductDTO.setPreviewImageUrl(product.getPreviewImage());
        editProductDTO.setProductGroupId(product.getProductGroup().getId());

        model.addAttribute("editProductDTO", editProductDTO);
        model.addAttribute("groups", productGroupService.groupList());
        return "product/edit.html";
    }
    @PostMapping("/update")
    public String update(@ModelAttribute("editProductDTO") EditProductDTO editProductDTO) throws IOException
    {
        Product updatedProduct = new Product();
        updatedProduct.setId(editProductDTO.getId());
        updatedProduct.setName(editProductDTO.getName());
        updatedProduct.setDiscription(editProductDTO.getDiscription());
        updatedProduct.setQuantity(editProductDTO.getQuantity());
        updatedProduct.setPrice(editProductDTO.getPrice());
        updatedProduct.setPreviewImage(editProductDTO.getPreviewImageUrl());
        updatedProduct.setProductGroup(productGroupService.show(editProductDTO.getProductGroupId()));
        productService.save(updatedProduct);
        return "redirect:/products/page/1";
    }
//    @GetMapping("/{id}/editCharacteristics")
//    public String editCharacteristics(@PathVariable("id") Long id, Model model)
//    {
//        Product product = productService.show(id);
//        model.addAttribute("product", product);
//        return "product/editCharacteristics";
//    }
//    @PostMapping("/{id}/editCharacteristics")
//    public String updateCharacteristics(@RequestParam(name="characteristicValue") String characteristicValue, @RequestParam(name="characteristicId") Long characteristicId, @PathVariable("id") Long id)
//    {
//        productCharacteristicService.deleteIfExist(productService.show(id), characteristicService.show(characteristicId));
//        ProductCharacteristic productCharacteristic = new ProductCharacteristic();
//        productCharacteristic.setProduct(productService.show(id));
//        productCharacteristic.setCharacteristic(characteristicService.show(characteristicId));
//        productCharacteristic.setProductCharacteristicValue(characteristicValue);
//        productCharacteristicService.save(productCharacteristic);
//        return "redirect:/products";
//    }
}
