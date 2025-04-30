package thiagoopg.com.backend.Service;

import org.springframework.stereotype.Service;
import thiagoopg.com.backend.Dto.CreateTransactionDto;
import thiagoopg.com.backend.Dto.UpdateTransactionDto;
import thiagoopg.com.backend.Entity.Account;
import thiagoopg.com.backend.Entity.Transaction;
import thiagoopg.com.backend.Repository.RepositoryAccount;
import thiagoopg.com.backend.Repository.RepositoryTransaction;

import java.util.List;

/**
 * Serviço responsável pela lógica de negócios relacionada a transações financeiras.
 * Realiza operações CRUD e validações necessárias para transações.
 */
@Service
public class ServiceTransaction {

    // Repositório de transações (persistência)
    final private RepositoryTransaction repositoryTransaction;

    // Repositório de contas (para associação com transações)
    final private RepositoryAccount repositoryAccount;

    /**
     * Construtor para injeção de dependências
     * @param repositoryTransaction Repositório de transações
     * @param repositoryAccount Repositório de contas
     */
    public ServiceTransaction(RepositoryTransaction repositoryTransaction,
                              RepositoryAccount repositoryAccount) {
        this.repositoryTransaction = repositoryTransaction;
        this.repositoryAccount = repositoryAccount;
    }

    /**
     * Recupera transação por ID
     * @param id ID da transação
     * @return Transação se encontrada e null se não encontrada
     */
    public Transaction getTransactionById(Integer id){
        return repositoryTransaction.findById(id).orElse(null);
    }

    /**
     * Recupera todas as transações cadastradas no sistema
     * @return Lista completa de transações
     */
    public List<Transaction> getAllTransaction(){
        return repositoryTransaction.findAll();
    }

    /**
     * Cria uma nova transação financeira
     * @param createTransactionDto DTO com dados necessários para criação da transação
     * @throws RuntimeException Se a conta associada não for encontrada
     */
    public void createTransaction(CreateTransactionDto createTransactionDto){
        // Valida existência da conta
        Account account = repositoryAccount.findById(createTransactionDto.id())
                .orElseThrow(() -> new RuntimeException("Account not found"));

        // Cria entidade de transação
        var entity = new Transaction(
                createTransactionDto.nome(),
                createTransactionDto.valor(),
                createTransactionDto.transactionType(),
                account);

        // salva a transação
        repositoryTransaction.save(entity);
    }

    /**
     * Atualiza uma transação existente
     * @param transaction DTO com dados para atualização
     * @throws RuntimeException Se a conta associada não for encontrada
     */
    public void updateTransaction(UpdateTransactionDto transaction){
        // Busca a transação existente
        var entity = repositoryTransaction.findById(transaction.idTransaction())
                .orElseThrow(() -> new RuntimeException("Transaction not found"));

        // Atualiza campos se fornecidos
        if(transaction.name() != null) entity.setName(transaction.name());
        if(transaction.price() != 0) entity.setPrice(transaction.price());
        if (transaction.transactionType() != null) entity.setType(transaction.transactionType());

        // Atualiza conta associada se fornecida
        if (transaction.idAccount() != null){
            Account account = repositoryAccount.findById(transaction.idAccount())
                    .orElseThrow(() -> new RuntimeException("Account not found"));
            entity.setAccount(account);
        }

        // Salva alterações
        repositoryTransaction.save(entity);
    }

    /**
     * Remove todas as transações do sistema
     * @return true indicando sucesso na operação
     */
    public boolean deleteAllTransactions(){
        repositoryTransaction.deleteAll();
        return true;
    }
    /**
     * Remove uma transação pelo id
     * @param id ID da transação a ser removida
     * @return true se deletado com sucesso e false se não existir transação para deletar
     */
    public boolean deleteTransactionById(Integer id){
        if(repositoryTransaction.existsById(id)){
            repositoryTransaction.deleteById(id);
            return true;
        }
        return false;
    }
}