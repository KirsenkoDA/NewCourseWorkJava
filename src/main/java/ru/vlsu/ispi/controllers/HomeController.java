package ru.vlsu.ispi.controllers;

import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.vlsu.ispi.models.Product;
import ru.vlsu.ispi.models.ProductGroup;
import ru.vlsu.ispi.models.SalesTable;
import ru.vlsu.ispi.models.User;
import ru.vlsu.ispi.services.ProductGroupService;
import ru.vlsu.ispi.services.ProductService;
import ru.vlsu.ispi.services.SalesTableService;

import java.util.List;

@Controller
@RequestMapping("/home")
public class HomeController {
    private final ProductService productService;
    private final ProductGroupService productGroupService;
    private final SalesTableService salesTableService;

    public HomeController(ProductService productService, ProductGroupService productGroupService, SalesTableService salesTableService) {
        this.productService = productService;
        this.productGroupService = productGroupService;
        this.salesTableService = salesTableService;
    }

//    @GetMapping("/personalAccount/page/{pageNo}")
//    public String personalAccount(@PathVariable(value = "pageNo") int pageNo, Model model, @AuthenticationPrincipal User user) {
//        int pageSize = 3;
//        Long userId = 8L;
//        Page<SalesTable> page = salesTableService.findByUser(pageNo - 1, pageSize, user);
//        List<SalesTable> salesTables = page.getContent();
//
//        model.addAttribute("currentPage", pageNo);
//        model.addAttribute("totalPages", page.getTotalPages());
//        model.addAttribute("totalItems", page.getTotalElements());
//        model.addAttribute("salesTables", salesTables);
//        return "userMainPage/personalAccount";
//    }
    @GetMapping("/")
    public String index()
    {
        return "redirect:/home/page/1";
    }
    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo, Model model, @RequestParam(name="selectByProductGroup", required = false) String productGroupId)
    {
        String selectedParam;
        ProductGroup productGroup = null;

        if(productGroupId == null) {
            productGroupId = "1";
            selectedParam = productGroupId;
        }
        else
        {
            productGroup = productGroupService.getProductGroup(Long.parseLong(productGroupId));
            selectedParam = productGroupId;
        }
        int pageSize = 3;


        Page<Product> page = productService.findPaginatedByProductGroup(pageNo - 1, pageSize, productGroup);
        List< Product > listProducts = page.getContent();
        model.addAttribute("groups", productGroupService.groupList());
        model.addAttribute("selectedGroup", productGroupService.findById(Long.parseLong(selectedParam)));
        model.addAttribute("selectedParam", selectedParam);
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("listProducts", listProducts);
        return "userMainPage/home.html";
    }
}
