package thiagoopg.com.backend.Service;

import org.springframework.stereotype.Service;
import thiagoopg.com.backend.Dto.CreateTransactionDto;
import thiagoopg.com.backend.Dto.UpdateTransactionDto;
import thiagoopg.com.backend.Entity.Account;
import thiagoopg.com.backend.Entity.Transaction;
import thiagoopg.com.backend.Repository.RepositoryAccount;
import thiagoopg.com.backend.Repository.RepositoryTransaction;

import java.util.List;

@Service
public class ServiceTransaction {

    final private RepositoryTransaction repositoryTransaction;
    final private RepositoryAccount repositoryAccount;
    public ServiceTransaction(RepositoryTransaction repositoryTransaction, RepositoryAccount repositoryAccount) {
        this.repositoryTransaction = repositoryTransaction;
        this.repositoryAccount = repositoryAccount;
    }
    public List<Transaction> getAllTransaction(){
        return repositoryTransaction.findAll();
    }
    public void createTransaction(CreateTransactionDto createTransactionDto){
        Account account = repositoryAccount.findById(createTransactionDto.id())
                .orElseThrow(() -> new RuntimeException("Account not found"));
        var entity = new Transaction(
                createTransactionDto.nome(),
                createTransactionDto.valor(),
                createTransactionDto.transactionType(),
                account);

        repositoryTransaction.save(entity);
    }
    public void updateTransaction(UpdateTransactionDto transaction){

        var entity = repositoryTransaction.findById(transaction.idTransaction()).get();

        if(transaction.name() != null) entity.setName(transaction.name());
        if(transaction.price() != 0) entity.setPrice(transaction.price());
        if (transaction.transactionType() != null) entity.setType(transaction.transactionType());

        if (transaction.idAccount() != null){
            Account account = repositoryAccount.findById(transaction.idAccount())
                    .orElseThrow(() -> new RuntimeException("Account not found"));
            entity.setAccount(account);
        }
    }
    //Deletar todas as contas
    public boolean deleteAllTransactions(){
        repositoryTransaction.deleteAll();
        return true;
    }
}
