package thiagoopg.com.backend.Controller;

import jakarta.persistence.PostUpdate;
import thiagoopg.com.backend.Dto.CreateContasDto;
import thiagoopg.com.backend.Entity.Account;
import thiagoopg.com.backend.Entity.Transaction;
import thiagoopg.com.backend.Service.ServiceAccounts;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

/**
 * Controller REST para gerenciamento de contas bancárias.
 * Responsável por receber requisições HTTP e retornar respostas padronizadas.
 *
 * Mapeamento base: "/accounts/" - todos os endpoints começam com este prefixo
 */
@RestController
@RequestMapping("/accounts/")
public class ControllerAccount {

    // Serviço injetado contendo as regras de negócio
    final private ServiceAccounts serviceAccounts;

    /**
     * Construtor para injeção de dependência
     * @param serviceAccounts Serviço com as operações de negócio para contas
     */
    public ControllerAccount(ServiceAccounts serviceAccounts) {
        this.serviceAccounts = serviceAccounts;
    }

    /**
     * Recupera todas as contas cadastradas
     * @return ResponseEntity contendo:
     *         - Lista de contas (body)
     *         - HTTP 200 OK            se existirem contas
     *         - HTTP 204 No Content    se a lista estiver vazia
     */
    @GetMapping
    public ResponseEntity<List<Account>> getAccounts() {
        List<Account> accounts = serviceAccounts.getAllAccounts();
        return accounts.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(accounts);
    }

    /**
     * Busca uma conta específica por ID
     * @param id ID da conta (path variable)
     * @return ResponseEntity contendo:
     *         - Conta encontrada (body)
     *         - HTTP 200 OK            se encontrada
     *         - HTTP 404 Not Found     se não existir
     */
    @GetMapping("{id}")
    public ResponseEntity<Account> getAccountById(@PathVariable Integer id) {
        Account account = serviceAccounts.getAccountById(id);
        return account != null ?
                ResponseEntity.ok(account) :
                ResponseEntity.notFound().build();
    }
    /**
     * Busca uma conta específica por ID
     * @param id ID da conta (path variable)
     * @return ResponseEntity contendo:
     *         - Conta encontrada (body)
     *         - HTTP 200 OK            se encontrada
     *         - HTTP 404 Not Found     se não existir
     */
    @GetMapping("transactions/{id}")
    public ResponseEntity<List<Transaction>> getTransactionsByID(@PathVariable Integer id) {
        List<Transaction> transactions = serviceAccounts.getAllTransactionsOfAccountById(id);
        return transactions != null ?
                ResponseEntity.ok(transactions) :
                ResponseEntity.notFound().build();
    }

    /**
     * Cria uma nova conta bancária
     * @param createContasDto DTO com dados para criação da conta
     * @return ResponseEntity contendo:
     *         - ID da nova conta (body)
     *         - HTTP 201 Created com header Location
     */
    @PostMapping("create")
    public ResponseEntity<Integer> createAccount(@RequestBody CreateContasDto createContasDto) {
        Integer newAccountId = serviceAccounts.createAccount(createContasDto);
        return ResponseEntity
                .created(URI.create("/accounts/" + newAccountId))
                .body(newAccountId);
    }

    /**
     * Atualiza o nome de uma conta existente
     * @param id ID da conta a ser atualizada
     * @param nome Novo nome para a conta
     * @return ResponseEntity contendo:
     *         - HTTP 200 OK            se atualizado com sucesso
     *         - HTTP 404 Not Found     se a conta não existir
     */
    @PostMapping("update")
    public ResponseEntity<Void> updateNameOfAccount(@RequestBody Integer id, String nome) {
        boolean updated = serviceAccounts.updateNameOfAccount(id, nome);
        return updated ?
                ResponseEntity.ok().build() :
                ResponseEntity.notFound().build();
    }

    /**
     * Remove todas as contas do sistema
     * @return ResponseEntity contendo:
     *         - HTTP 200 OK            se deletado com sucesso
     *         - HTTP 404 Not Found     se não houver contas
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteContas() {

        return serviceAccounts.deleteAllAccount()?
                ResponseEntity.ok().build():
                ResponseEntity.notFound().build();
    }

    /**
     * Remove uma conta específica por ID
     * @param id ID da conta a ser removida
     * @return ResponseEntity contendo:
     *         - HTTP 200 OK se removida com sucesso
     *         - HTTP 404 Not Found se a conta não existir
     */
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteAccountById(@PathVariable Integer id) {
        boolean deleted = serviceAccounts.deleteAccountById(id);
        return deleted ?
                ResponseEntity.ok().build() :
                ResponseEntity.notFound().build();
    }
}