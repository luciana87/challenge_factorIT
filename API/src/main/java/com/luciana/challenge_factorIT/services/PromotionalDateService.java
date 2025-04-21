package com.luciana.challenge_factorIT.services;

import com.luciana.challenge_factorIT.repositories.PromotionalDateRepository;
import com.luciana.challenge_factorIT.utils.Utils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PromotionalDateService {
    private final PromotionalDateRepository promotionalDateRepository;

    public PromotionalDateService(PromotionalDateRepository promotionalDateRepository) {
        this.promotionalDateRepository = promotionalDateRepository;
    }

    public boolean findByDate(String createdAt) {
        LocalDateTime date = Utils.parseToLocalDateTime(createdAt);
        System.out.println("Parsed Date: " + date);
        return promotionalDateRepository.isDateWithinRange(date);
    }
}
