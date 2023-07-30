package com.example.pizza.service.conventer;

import com.example.pizza.entity.Cafe;
import org.springframework.stereotype.Service;


/**
 * The CafeUpdateService class provides a method to update a cafe based on the changes specified in another cafe object.
 */
@Service
public class CafeUpdateService {

    /**
     * Updates the provided cafe based on the changes specified in the cafeUpdate object.
     *
     * @param cafe          the original cafe object
     * @param cafeUpdate    the cafe object containing the updates
     * @return the updated cafe
     */
    public Cafe convert(Cafe cafe, Cafe cafeUpdate) {
        if (cafe == null || cafeUpdate == null) {
            throw new IllegalArgumentException("argument is null");
        }
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

