package com.example.pismobank.repositories;

import com.example.pismobank.models.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account, Long> {
}
