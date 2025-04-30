package thiagoopg.com.backend.Service;

import thiagoopg.com.backend.Dto.CreateContasDto;
import thiagoopg.com.backend.Entity.Account;
import thiagoopg.com.backend.Entity.Transaction;
import thiagoopg.com.backend.Repository.RepositoryAccount;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceAccounts {

    final private RepositoryAccount contaRepository;

    public ServiceAccounts(RepositoryAccount contaRepository) {
        this.contaRepository = contaRepository;
    }

    /**
     * Cria uma nova conta no sistema
     * @param createContasDto DTO com os dados da conta
     * @return ID da conta criada
     */
    public Integer createAccount(CreateContasDto createContasDto) {
        var entity = new Account(createContasDto.name());
        var saved = contaRepository.save(entity);
        return saved.getId_account();
    }

    /**
     * Recupera todas as contas cadastradas
     * @return Lista de todas as contas
     */
    public List<Account> getAllAccounts() {
        return contaRepository.findAll();
    }
    /**
     * Recupera todas as Transações da conta por ID
     * @param id ID da conta selecionada
     * @return Lista de todas as transações da conta
     */
    public List<Transaction> getAllTransactionsOfAccountById(Integer id) {
        return contaRepository.findById(id).isPresent()?contaRepository.findById(id).get().getTransactionList():null;
    }

    /**
     * Atualiza o nome de uma conta existente
     * @param id ID da conta a ser atualizada
     * @param nome Novo nome para a conta
     * @return true se a conta foi atualizada, false se não encontrada
     */
    public boolean updateNameOfAccount(Integer id, String nome) {
        Optional<Account> optionalAccount = contaRepository.findById(id);
        if (optionalAccount.isPresent()) {
            Account account = optionalAccount.get();
            account.setName(nome);
            contaRepository.save(account);
            return true;
        }
        return false;
    }

    /**
     * Remove todas as contas do sistema
     * @return true se excluiu com sucesso e false se não há contas para excluir
     */
    public boolean deleteAllAccount() {
        if(contaRepository.findAll().isEmpty()){
            contaRepository.deleteAll();
            return true;
        }
        return false;
    }

    /**
     * Busca uma conta pelo ID
     * @param id ID da conta
     * @return A conta encontrada ou null se não existir
     */
    public Account getAccountById(Integer id) {
        return contaRepository.findById(id).orElse(null);
    }

    /**
     * Remove uma conta específica
     * @param id ID da conta a ser removida
     * @return true se a conta foi removida, false se não encontrada
     */
    public boolean deleteAccountById(Integer id) {
        if (contaRepository.existsById(id)) {
            contaRepository.deleteById(id);
            return true;
        }
        return false;
    }
}