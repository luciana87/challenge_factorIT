package com.luciana.challenge_factorIT.services;

import com.luciana.challenge_factorIT.entities.Cart;
import com.luciana.challenge_factorIT.entities.Vip;
import com.luciana.challenge_factorIT.repositories.VipRepository;
import com.luciana.challenge_factorIT.utils.Utils;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class VipService {
    private final VipRepository vipRepository;

    public VipService(VipRepository vipRepository) {
        this.vipRepository = vipRepository;
    }

    public List<Vip> findByActiveAndDate(boolean active, int  year, int month){
        return vipRepository.findVipsByMonthAndActive(active, month, year);
    }
}
