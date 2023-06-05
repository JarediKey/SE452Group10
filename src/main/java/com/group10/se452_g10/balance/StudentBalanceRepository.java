package com.group10.se452_g10.balance;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentBalanceRepository extends MongoRepository<StudentBalance, Long> {

}

