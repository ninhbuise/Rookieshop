package com.enashtech.rookieserver.service;

import java.util.ArrayList;
import java.util.List;

import com.enashtech.rookieserver.entity.Customer;
import com.enashtech.rookieserver.entity.Order;
import com.enashtech.rookieserver.entity.OrderDTO;
import com.enashtech.rookieserver.entity.OrderDetail;
import com.enashtech.rookieserver.entity.Product;
import com.enashtech.rookieserver.handleException.RuntimeExceptionHandle;
import com.enashtech.rookieserver.repository.OrderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    private final ProductService productService;
    private final OrderRepository orderRepository;
    private final CustomerService customerService;
    private final OrderDetailService orderDetailService;

    @Autowired
    public OrderServiceImpl(ProductService productService, OrderRepository orderRepository, 
                            CustomerService customerService, OrderDetailService orderDetailService) {
        this.productService = productService;
        this.orderRepository = orderRepository;
        this.customerService = customerService;   
        this.orderDetailService = orderDetailService;
    }

    @Override
    public Order saveOrder(OrderDTO orderDTO, String username) {
        List<OrderDetail>  orderDetails = new ArrayList<>();
        orderDTO.getDetailDTOs().forEach(detail ->{
            Product product = productService.findById(detail.getProduct_id());
            int remaining_amount = product.getQuantity() - detail.getAmount();
            if(remaining_amount >= 0) {
                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setAmount(detail.getAmount());
                product.setQuantity(remaining_amount);
                orderDetail.setProduct(product);
                orderDetails.add(orderDetail);
            } else {
                throw new RuntimeExceptionHandle("The product is not in sufficient quantity, product: " + detail.getProduct_id());
            }
        });
        Customer customer =  customerService.findCustomerByUserName(username);
        Order order = new Order();

        order.setAddress(null);
        order.setPhone(orderDTO.getPhone());
        //update product Quantity
        orderDetails.forEach(detail ->{
            int id = detail.getProduct().getId();
            productService.updateProduct(detail.getProduct(), id);
            orderDetailService.saveOrderDetail(detail);
        });
        order.setOrderDetails(orderDetails);
        customer.addOrder(order);
        orderRepository.save(order);
        customerService.updateCustomer(customer, customer.getId());
        return order;
    }
    
}
