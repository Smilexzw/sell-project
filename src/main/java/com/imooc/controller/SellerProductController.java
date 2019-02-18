package com.imooc.controller;

import com.imooc.entity.ProductInfo;
import com.imooc.exception.SellException;
import com.imooc.form.ProductForm;
import com.imooc.service.CategoryService;
import com.imooc.service.ProductService;
import com.imooc.utils.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: xuzhangwang
 * @Description: 卖家端商品信息处理
 */
@Controller
@RequestMapping("/seller/product")
public class SellerProductController {

    @Autowired
    ProductService productService;

    @Autowired
    CategoryService categoryService;

    /**
     * 返回卖家端商品信息列表
     * @return
     */
    @GetMapping("/list")
    public String sellerProductList(Model model, @RequestParam(value = "page", defaultValue = "1") Integer page,
                                    @RequestParam(value = "size", defaultValue = "10") Integer size) {
        // 创建page对象
        PageRequest request = new PageRequest(page - 1, size);
        Page<ProductInfo> productInfoPage = productService.findAll(request);
        model.addAttribute("productInfoPage", productInfoPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("size", size);
        return "seller/product-list";
    }

    @ResponseBody
    @GetMapping("/onsale")
    public Map<String, Object> onSale(@RequestParam("productId") String productId) {
        Boolean result = false;
        Map<String,Object> map = new HashMap<>();
        try {
            productService.onSale(productId);
        } catch (SellException e) {
            map.put("msg", "商品上架失败");
            map.put("result", result);
            return map;
        }
        map.put("msg", "商品上架成功");
        result = !result;
        map.put("result", result);
        return map;
    }


    @ResponseBody
    @GetMapping("offsale")
    public Map<String, Object> offSale(@RequestParam("productId") String productId) {
        Map<String, Object> map = new HashMap<>();
        Boolean result = false;
        try {
            productService.offSale(productId);
        } catch (SellException e) {
            map.put("msg", "商品下架失败");
            map.put("result", result);
            return map;
        }
        result = !result;
        map.put("msg", "商品下架成功");
        map.put("result", result);
        return map;
    }

    @ResponseBody
    @GetMapping("/del")
    public Map<String, Object> delProduct(@RequestParam("productId") String productId) {
        Map<String, Object> map = new HashMap<>();
        boolean result = false;
        try {
            productService.delProduct(productId);
        } catch (SellException e) {
            // 抛出异常
            map.put("msg", "删除商品失败");
            map.put("result", result);
        }
        result = !result;
        map.put("msg", "删除商品成功");
        map.put("result", result);
        return map;
    }


    /**
     * 添加商品的页面的路由
     * @return
     */
    @GetMapping("/index")
    public String addPage(@RequestParam(value = "productId", required = false) String productId, Model model) {
        if (!StringUtils.isEmpty(productId)) {
            model.addAttribute("productInfo", productService.findOne(productId));
            model.addAttribute("category",categoryService.findAll());
            model.addAttribute("result", 0);
        } else {
            model.addAttribute("category",categoryService.findAll());
            model.addAttribute("result", 1);
        }
        return "seller/product-index";
    }


    /**
     * 处理商品的添加方法
     * @param productForm
     * @param bindingResult
     * @return
     */
    @ResponseBody
    @PostMapping("/doadd")
    public Map<String, Object> insertProduct(@Validated ProductForm productForm, BindingResult bindingResult) {
        Map<String, Object> map = new HashMap<>();
        if (bindingResult.hasErrors()) {
            map.put("result", bindingResult.getFieldError().getDefaultMessage());
            return map;
        }
            // 如果没有错误就进行数据的保存
//
//            productInfo.setProductId(KeyUtil.getUniqueKey());
//            // 如果数据没有错误的提示信息就进行商品的添加
//            productService.save(productInfo);
//        }
        ProductInfo productInfo = new ProductInfo();

        try {
            // 如果product Form中得到的id为空，就代表新增
            if (!StringUtils.isEmpty(productForm.getProductId())) {
                productInfo = productService.findOne(productForm.getProductId());
            } else {
                productForm.setProductId(KeyUtil.getUniqueKey());
            }
            BeanUtils.copyProperties(productForm, productInfo);
            productService.save(productInfo);
        } catch (SellException e) {
            map.put("result", e);
        }
        map.put("result", "success");
        return map;
    }
}
