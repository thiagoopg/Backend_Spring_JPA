package thiagoopg.com.backend.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import thiagoopg.com.backend.Entity.Account;

@Repository
public interface RepositoryAccount extends JpaRepository<Account, Integer> {
}
