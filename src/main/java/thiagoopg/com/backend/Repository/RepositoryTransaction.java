package thiagoopg.com.backend.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import thiagoopg.com.backend.Entity.Transaction;

@Repository
public interface RepositoryTransaction extends JpaRepository<Transaction, Integer> {

}
