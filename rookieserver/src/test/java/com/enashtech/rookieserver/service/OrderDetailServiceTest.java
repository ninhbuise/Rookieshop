package com.enashtech.rookieserver.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import com.enashtech.rookieserver.entity.OrderDetail;
import com.enashtech.rookieserver.repository.OrderDetailRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class OrderDetailServiceTest {
    @MockBean
    OrderDetailRepository repo;

    @Autowired
    OrderDetailService service;
    
    @Test
    public void saveOrderDetail_ReturnOrderDetail() {
        OrderDetail detail = new OrderDetail();
        detail.setAmount(3);
        when(repo.save(detail)).thenReturn(detail);
        assertEquals(service.saveOrderDetail(detail), detail);
        verify(repo, times(1)).save(detail);
    }

    @Test
    public void getListProductsByStoreName_ReturnOrderDetailList() {
        List<OrderDetail> list = new ArrayList<>();
        OrderDetail detail = new OrderDetail();
        detail.setAmount(3);
        list.add(detail);
        detail.setAmount(2);
        list.add(detail);

        when(repo.getListProductsByStoreName("somename")).thenReturn(list);
        assertEquals(service.getListProductsByStoreName("somename"), list);
        verify(repo, times(1)).getListProductsByStoreName("somename");
    }
}
