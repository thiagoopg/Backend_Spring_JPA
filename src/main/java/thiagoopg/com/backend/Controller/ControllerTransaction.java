package thiagoopg.com.backend.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import thiagoopg.com.backend.Dto.CreateTransactionDto;
import thiagoopg.com.backend.Dto.UpdateTransactionDto;
import thiagoopg.com.backend.Entity.Transaction;
import thiagoopg.com.backend.Service.ServiceTransaction;

import java.net.URI;
import java.util.List;

/**
 * Mapeamento base: "/transaction/" - todos os endpoints começam com este prefixo
 */
@RestController
@RequestMapping("/transaction/")
public class ControllerTransaction {

    // Serviço injetado contendo a lógica de negócios
    final private ServiceTransaction serviceTransaction;

    /**
     * Construtor para injeção de dependência
     * @param serviceTransaction Serviço com operações de transações
     */
    public ControllerTransaction(ServiceTransaction serviceTransaction) {
        this.serviceTransaction = serviceTransaction;
    }

    /**
     * Recupera uma transação específica por ID
     * @param id ID da transação
     * @return ResponseEntity contendo:
     *         - Transação encontrada (body) e HTTP 200 OK
     *         - HTTP 404 Not Found se não existir
     */
    @GetMapping("{id}")
    public ResponseEntity<Transaction> getTransactionById(@PathVariable Integer id) {
        Transaction transaction = serviceTransaction.getTransactionById(id);
        return transaction != null ?
                ResponseEntity.ok(transaction) :
                ResponseEntity.notFound().build();
    }

    /**
     * Recupera todas as transações cadastradas
     * @return ResponseEntity contendo:
     *         - Lista de transações (body) e HTTP 200 OK
     *         - HTTP 204 No Content se não houver transações
     */
    @GetMapping
    public ResponseEntity<List<Transaction>> getAllTransactions() {
        List<Transaction> transactions = serviceTransaction.getAllTransaction();
        return transactions.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(transactions);
    }

    /**
     * Cria uma nova transação
     * @param createTransactionDto DTO com dados da transação
     * @return ResponseEntity contendo:
     *         - HTTP 201 Created
     *         - HTTP 400 Bad Request se a conta associada não existir
     */
    @PostMapping("create")
    public ResponseEntity<Void> createTransaction(@RequestBody CreateTransactionDto createTransactionDto) {
        try {
            serviceTransaction.createTransaction(createTransactionDto);
            return ResponseEntity.created(URI.create("/transaction/")).build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Atualiza uma transação existente
     * @param id ID da transação
     * @param updateTransactionDto DTO com dados para atualização
     * @return ResponseEntity contendo:
     *         - HTTP 204 No Content se atualizado com sucesso
     *         - HTTP 404 Not Found se transação ou conta não existirem
     */
    @PatchMapping("update/{id}")
    public ResponseEntity<Void> updateTransaction(
            @PathVariable Integer id,
            @RequestBody UpdateTransactionDto updateTransactionDto) {
        try {
            serviceTransaction.updateTransaction(updateTransactionDto);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Remove todas as transações
     * @return ResponseEntity contendo:
     *         - HTTP 204 No Content
     */
    @DeleteMapping
    public ResponseEntity<Void> deleteAllTransactions() {
        serviceTransaction.deleteAllTransactions();
        return ResponseEntity.noContent().build();
    }

    /**
     * Remove uma transação específica
     * @param id ID da transação a ser removida
     * @return ResponseEntity contendo:
     *         - HTTP 204 No Content se removida com sucesso
     *         - HTTP 404 Not Found se a transação não existir
     */
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteTransactionById(@PathVariable Integer id) {
        boolean deleted = serviceTransaction.deleteTransactionById(id);
        return deleted ?
                ResponseEntity.noContent().build() :
                ResponseEntity.notFound().build();
    }
}