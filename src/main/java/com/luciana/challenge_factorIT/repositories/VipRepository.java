package com.luciana.challenge_factorIT.repositories;

import com.luciana.challenge_factorIT.entities.Vip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VipRepository extends JpaRepository<Vip, Long> {

    @Query("SELECT v FROM Vip v " +
            "WHERE (:active IS NULL OR v.active = :active) " +
            "AND FUNCTION('MONTH', v.modifiedAt) = :month " +
            "AND FUNCTION('YEAR', v.modifiedAt) = :year")
    List<Vip> findVipsByMonthAndActive(@Param("active") Boolean active,
                                        @Param("month") int month,
                                        @Param("year") int year);
}
