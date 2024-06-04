package ru.vlsu.ispi.controllers;

import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.vlsu.ispi.models.SalesLine;
import ru.vlsu.ispi.models.SalesTable;
import ru.vlsu.ispi.models.User;
import ru.vlsu.ispi.services.*;

import java.util.List;

@Controller
@RequestMapping("/salesTables")
public class SalesTableController {
    private final SalesTableService salesTableService;
    private final SalesLineService salesLineService;
    private final StatusService statusService;
    private final UserService userService;
    private final WalletService walletService;
    private final ProductService productService;
    private final CartService cartService;
    private final PayService payService;

    public SalesTableController(SalesTableService salesTableService, SalesLineService salesLineService, StatusService statusService, UserService userService, WalletService walletService, ProductService productService, CartService cartService, PayService payService) {
        this.salesTableService = salesTableService;
        this.salesLineService = salesLineService;
        this.statusService = statusService;
        this.userService = userService;
        this.walletService = walletService;
        this.productService = productService;
        this.cartService = cartService;
        this.payService = payService;
    }

    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo, Model model, @AuthenticationPrincipal User user) {
        int pageSize = 3;
        Page<SalesTable> page;
        page = salesTableService.findPaginated(pageNo - 1, pageSize);
//        if (user.getRoles().contains("ROLE_ADMIN"))
//        {
//            page = salesTableService.findPaginated(pageNo - 1, pageSize);
//        }
//        else
//        {
//            page = salesTableService.findPaginatedByUser(pageNo - 1, pageSize, user);
//        }
        List<SalesTable> salesTables = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("salesTables", salesTables);
        return "salesTable/index";
    }
    @GetMapping("/{id}")
    public String show(@PathVariable Long id, Model model)
    {
        List<SalesLine> salesLines = salesLineService.findBySalesTableList(salesTableService.show(id));
        SalesTable salesTable = salesTableService.show(id);
        model.addAttribute("salesTable", salesTable);
        model.addAttribute("salesLines", salesLines);
        return "salesTable/show";
    }
    @Transactional
    @PostMapping("/accept/{id}")
    public String accept(@PathVariable Long id, @RequestParam(name="address", required = false) String address)
    {
        User user = (User) userService.getCurrentUser();
        SalesTable salesTable = salesTableService.show(id);
        salesTable.setAddress(address);
        salesTable.setStatus(statusService.show(2));
        payService.pay(salesTable, user);
        return "redirect:/home/personalAccount/page/1";
    }
    @GetMapping("/alterStatus/collect")
    public String alterStatusCollect(@RequestParam(name="salesTableId", required = false) String id)
    {
        SalesTable salesTable = salesTableService.show(Long.valueOf(id));
        salesTable.setStatus(statusService.show(3));
        salesTableService.save(salesTable);
        return "redirect:/salesTables/page/1";
    }
    @GetMapping ("/alterStatus/send")
    public String alterStatusSend(@RequestParam(name="salesTableId", required = false) String id)
    {
        SalesTable salesTable = salesTableService.show(Long.valueOf(id));
        salesTable.setStatus(statusService.show(4));
        salesTableService.save(salesTable);
        return "redirect:/salesTables/page/1";
    }
    @GetMapping("/alterStatusShow/{id}")
    public String alterStatusShow(@PathVariable Long id, Model model)
    {
        List<SalesLine> salesLines = salesLineService.findBySalesTableList(salesTableService.show(id));
        SalesTable salesTable = salesTableService.show(id);
        model.addAttribute("salesTable", salesTable);
        model.addAttribute("salesLines", salesLines);
        return "salesTable/alterStatus";
    }
}
