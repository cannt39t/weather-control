package com.cantt39t.weathercontrol.repository;

import com.cantt39t.weathercontrol.model.UserHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;

public interface UserHistoryRepository extends JpaRepository<UserHistory, Integer> {

    @Query(value = "SELECT COUNT(*) FROM users_history WHERE email = :email AND timestamp >= :startTime", nativeQuery = true)
    int countRequestsForEmailSinceTime(@Param("email") String email, @Param("startTime") Date startTime);

    default boolean canUserAccessData(String email) {
        Date startTime = new Date(System.currentTimeMillis() - 3600_000);
        int numRequests = countRequestsForEmailSinceTime(email, startTime);
        return numRequests < 5;
    }
}


