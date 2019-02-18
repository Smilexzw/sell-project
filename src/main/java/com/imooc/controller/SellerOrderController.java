package com.imooc.controller;

import com.imooc.dto.OrderDTO;
import com.imooc.entity.OrderMaster;
import com.imooc.entity.SellerInfo;
import com.imooc.exception.SellException;
import com.imooc.service.OrderService;
import com.imooc.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;


/**
 * @Auther: xuzhangwang
 * @Description:  订单控制层
 */
@Controller
@RequestMapping("/seller/order")
@Slf4j
public class SellerOrderController {

    @Autowired
    OrderService orderService;

    @Autowired


    /**
     * 订单详细显示页面
     * @return
     */
    @GetMapping("/seller/order/details")
    public String adminOrderDetailsPage() {
        return "seller/order-details";
    }

    /**
     * 获取所有的订单, 在卖家order管理页面进行显示, 默认的是从的第0页开始查询的，大小的size的是10条数据
     */
    @GetMapping("/list")
    public String findOrderList(Model model, @RequestParam(value = "page", defaultValue = "1") Integer page,
                                @RequestParam(value = "size", defaultValue = "10") Integer size) {
        PageRequest request = new PageRequest(page - 1, size);
        Page<OrderDTO> orderDTOPage = orderService.findAllOrderList(request);
        model.addAttribute("orderDTOPage", orderDTOPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("size", size);
        return "seller/order-list";
    }


    /**
     * 取消订单
     */
    @GetMapping("/cancel")
    @ResponseBody
    public Map<String, Object> cancelOrderByOrderId(@RequestParam("orderId") String orderId) {
        Map<String, Object> map = new HashMap<>();

        try {
            OrderDTO orderDTO = orderService.findOne(orderId);
            orderService.cancel(orderDTO);
        } catch (SellException e) {
            log.error("【用户订单取消】, 订单取消失败");
            map.put("msg", "取消订单失败!!!");
            map.put("result", 1);
            return map;
        }
        map.put("msg", "取消订单成功!!!");
        map.put("result", 0);
        return map;
    }

    /**
     * 卖家查看用户订单的详细信息，根据的是订单orderId
     * @param model
     * @return
     */
    @GetMapping("details")
    public String orderDetails(@RequestParam("orderId") String orderid,
                               Model model, Map<String, Object> map) {
        OrderDTO orderDTO = null;
        try {
            orderDTO = orderService.findOne(orderid);
        } catch (SellException e) {
            // catch到异常就抛出异常
            log.error("【查看订单】, 查看订单的错误, ");
            map.put("msg", "查看订单出现异常!!!");
        }
        model.addAttribute("orderDTO", orderDTO);
        return "seller/order-details";
    }

    @GetMapping("/finsh")
    @ResponseBody
    public Map<String, Object> orderFinsh(@RequestParam("orderId") String orderid) {
        Map<String, Object> map = new HashMap<>();
        try {
            OrderDTO orderDTO = orderService.findOne(orderid);
            orderService.finish(orderDTO);
        } catch (SellException e) {
            log.error("【完结订单】, 完结订单出现异常, orderId={}", orderid);
            map.put("msg", "完结订单失败");
            return map;
         }
        map.put("msg", "完结订单成功");
        return map;
    }



}
