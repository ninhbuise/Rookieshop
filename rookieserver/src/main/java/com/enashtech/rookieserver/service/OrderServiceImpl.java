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
    private final AddressService addressService;
    private final SizeService sizeService;
    private final ColorService colorService;

    @Autowired
    public OrderServiceImpl(ProductService productService, OrderRepository orderRepository,
            CustomerService customerService, OrderDetailService orderDetailService, AddressService addressService,
            SizeService sizeService, ColorService colorService) {
        this.productService = productService;
        this.orderRepository = orderRepository;
        this.customerService = customerService;
        this.orderDetailService = orderDetailService;
        this.addressService = addressService;
        this.sizeService = sizeService;
        this.colorService = colorService;
    }

    @Override
    public List<Order> getOrderList() {
        return orderRepository.findAll();
    }

    @Override
    public Order saveOrder(OrderDTO orderDTO, String username) {
        if (username == null)
            throw new RuntimeExceptionHandle("The username is null");
        Customer customer = customerService.findCustomerByUserName(username);

        List<OrderDetail> orderDetails = new ArrayList<>();
        orderDTO.getDetailDTOs().forEach(detail -> {
            Product product = productService.findById(detail.getProduct_id());
            int remaining_amount = product.getQuantity() - detail.getAmount();

            if (remaining_amount >= 0) {
                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setAmount(detail.getAmount());
                product.setQuantity(remaining_amount);
                orderDetail.setProduct(product);
                orderDetail.setColor(colorService.findByColorId(detail.getColor_id()));
                orderDetail.setSize(sizeService.findSizeById(detail.getSize_id()));
                orderDetails.add(orderDetail);
            } else {
                throw new RuntimeExceptionHandle(
                        "The product is not in sufficient quantity, product: " + product.getName());
            }
        });

        // Check address is duplicate
        if (customer.getAddress() == null) {
            addressService.saveAddress(orderDTO.getAddress());
            customer.setAddress(orderDTO.getAddress());
        } else {
            if (customer.getAddress().equals(orderDTO.getAddress())) {
                orderDTO.setAddress(customer.getAddress());
            } else {
                addressService.saveAddress(orderDTO.getAddress());
                customer.setAddress(orderDTO.getAddress());
            }
        }

        Order order = new Order();
        order.setAddress(orderDTO.getAddress());
        order.setPhone(orderDTO.getPhone());
        order.setOrderDetails(orderDetails);
        // update product Quantity
        orderDetails.forEach(detail -> {
            productService.updateProduct(detail.getProduct());
            orderDetailService.saveOrderDetail(detail);
        });
        orderRepository.save(order);
        customer.addOrder(order);
        customerService.updateCustomer(customer);
        return order;
    }

}
