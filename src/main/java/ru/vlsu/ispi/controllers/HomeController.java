package ru.vlsu.ispi.controllers;

import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.vlsu.ispi.dto.UserUpdateDTO;
import ru.vlsu.ispi.models.*;
import ru.vlsu.ispi.services.ProductGroupService;
import ru.vlsu.ispi.services.ProductService;
import ru.vlsu.ispi.services.SalesTableService;
import ru.vlsu.ispi.services.WalletService;

import java.util.List;

@Controller
@RequestMapping("/home")
public class HomeController {
    private final ProductService productService;
    private final ProductGroupService productGroupService;
    private final SalesTableService salesTableService;
    private final WalletService walletService;

    public HomeController(ProductService productService, ProductGroupService productGroupService, SalesTableService salesTableService, WalletService walletService) {
        this.productService = productService;
        this.productGroupService = productGroupService;
        this.salesTableService = salesTableService;
        this.walletService = walletService;
    }

    @GetMapping("/personalAccount/page/{pageNo}")
    public String personalAccount(@PathVariable(value = "pageNo") int pageNo, Model model, @AuthenticationPrincipal User user) {
        int pageSize = 3;
        Page<SalesTable> page = salesTableService.findByUser(pageNo - 1, pageSize, user);
        List<SalesTable> salesTables = page.getContent();

        UserUpdateDTO userUpdateDTO = new UserUpdateDTO();
        userUpdateDTO.setId(user.getId());
        userUpdateDTO.setName(user.getName());
        userUpdateDTO.setEmail(user.getEmail());
        userUpdateDTO.setPhoneNumber(userUpdateDTO.getPhoneNumber());
        userUpdateDTO.setActive(userUpdateDTO.isActive());
        userUpdateDTO.setRoles(user.getRoles());
        int balance = user.getWallet().getBalance();

        model.addAttribute("walletBalance", balance);
        model.addAttribute("wallet", user.getWallet());
        model.addAttribute("userUpdateDTO", userUpdateDTO);
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("salesTables", salesTables);
        return "userMainPages/personalAccount";
    }
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
            productGroup = productGroupService.show(Long.parseLong(productGroupId));
            selectedParam = productGroupId;
        }
        else
        {
            productGroup = productGroupService.show(Long.parseLong(productGroupId));
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
        return "userMainPages/home.html";
    }
    @GetMapping("/page/{pageNo}/{filterBy}/{filterType}")
    public String findPaginatedSorted(@PathVariable(value = "pageNo") int pageNo, Model model, @RequestParam(name="selectByProductGroup", required = false) String productGroupId, @PathVariable("filterBy") String filterBy, @PathVariable("filterType") String filterType)
    {
        String selectedParam;
        ProductGroup productGroup = null;

        if(productGroupId == null) {
            productGroupId = "1";
            productGroup = productGroupService.show(Long.parseLong(productGroupId));
            selectedParam = productGroupId;
        }
        else
        {
            productGroup = productGroupService.show(Long.parseLong(productGroupId));
            selectedParam = productGroupId;
        }
        int pageSize = 3;


        Page<Product> page = productService.findByProductGroupSorted(pageNo - 1, pageSize, productGroup, filterBy, filterType);
        List< Product > listProducts = page.getContent();

        model.addAttribute("filterBy", filterBy);
        model.addAttribute("filterType", filterType);
        model.addAttribute("groups", productGroupService.groupList());
        model.addAttribute("selectedGroup", productGroupService.findById(Long.parseLong(selectedParam)));
        model.addAttribute("selectedParam", selectedParam);
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("listProducts", listProducts);
        return "userMainPages/home.html";
    }
}
