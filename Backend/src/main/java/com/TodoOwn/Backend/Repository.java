package com.TodoOwn.Backend;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;


@org.springframework.stereotype.Repository
public interface Repository extends JpaRepository<Models, Long> {
    @Transactional
    @Modifying
    void deleteByDone(boolean done);
}
