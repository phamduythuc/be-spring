package com.example.springsecurity.reposiroty;

import com.example.springsecurity.entities.Log;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogRepository extends JpaRepository<Log,Long> {
}
