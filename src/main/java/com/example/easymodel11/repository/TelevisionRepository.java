package com.example.easymodel11.repository;
import com.example.easymodel11.model.Television;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


public interface TelevisionRepository extends JpaRepository<Television, Long> {


    @Transactional
    @Modifying

    @Query("delete from Television t where t.name = :name ")
     void deleteByName(@Param("name") String name);
    Optional<Television> findByName(String name);


}
