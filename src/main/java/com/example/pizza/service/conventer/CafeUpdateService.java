package com.example.pizza.service.conventer;

import com.example.pizza.entity.Cafe;
import org.springframework.stereotype.Service;

@Service
public class CafeUpdateService {

    public Cafe convert(Cafe cafe, Cafe cafeUpdate) {
        if (cafeUpdate.getName() != null) {
            cafe.setName(cafeUpdate.getName());
        }
        if (cafeUpdate.getPhone() != null) {
            cafe.setPhone(cafeUpdate.getPhone());
        }
        if (cafeUpdate.getAddress() != null) {
            cafe.setAddress(cafeUpdate.getAddress());
        }
        if (cafeUpdate.getSchedule() != null) {
            cafe.setSchedule(cafeUpdate.getSchedule());
        }

        return cafe;

    }

}
