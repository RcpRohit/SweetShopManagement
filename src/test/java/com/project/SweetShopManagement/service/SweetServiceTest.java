package com.project.SweetShopManagement.service;


import com.project.SweetShopManagement.model.Sweet;
import com.project.SweetShopManagement.repository.SweetRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SweetServiceTest
{

    @Mock
    private SweetRepository sweetRepository;

    @InjectMocks
    private SweetService sweetService;

    @Test
    void purchaseSweet_shouldThrowException_whenStockIsNegative() {

        Sweet sweet = new Sweet();
        sweet.setId(1L);
        sweet.setQuantity(5);

        when(sweetRepository.findById(1L))
                .thenReturn(Optional.of(sweet));

        assertThrows(RuntimeException.class, () -> {
            sweetService.purchaseSweet(1L, -1);
        });
    }


}
