package com.luciana.challenge_factorIT.repositories;

import com.luciana.challenge_factorIT.entities.PromotionalDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface PromotionalDateRepository extends JpaRepository<PromotionalDate, Long> {

/*    @Query(value = "SELECT EXISTS(SELECT 1 FROM promotional_dates WHERE :date BETWEEN started_at AND ended_at)", nativeQuery = true)
    boolean isDateWithinRange(@Param("date") LocalDateTime date);*/

    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END " +
            "FROM PromotionalDate p " +
            "WHERE :date BETWEEN p.startedAt AND p.endedAt")
    boolean isDateWithinRange(@Param("date") LocalDateTime date);
}
