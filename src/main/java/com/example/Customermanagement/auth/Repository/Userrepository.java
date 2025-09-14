package com.example.Customermanagement.auth.Repository;

import com.example.Customermanagement.auth.Model.Usermodel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface Userrepository extends JpaRepository<Usermodel, Long> {

    Optional<Usermodel> findByUsername(String username);

    Optional<Usermodel> findFirstByUsername(String username);
}
