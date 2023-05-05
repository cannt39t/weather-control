package com.cantt39t.weathercontrol.repository;

import com.cantt39t.weathercontrol.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> getUserByEmail(String email);

    List<User> findAllByIdInAndEmailNotNull(List<Integer> ids);

    @Query(value = "select * from users u where u.name like ?1", nativeQuery = true)
    List<User> findAllByName(String name);

    List<User> findAllByEmail(String email);

    Optional<User> findByEmail(String mail);
}
