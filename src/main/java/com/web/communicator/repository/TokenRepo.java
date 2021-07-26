package com.web.communicator.repository;

import com.web.communicator.domain.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TokenRepo extends JpaRepository<Token, Long> {


    Optional<Token> findByValue(String value);
}
