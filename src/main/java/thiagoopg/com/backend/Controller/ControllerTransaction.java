package thiagoopg.com.backend.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import thiagoopg.com.backend.Dto.CreateTransactionDto;
import thiagoopg.com.backend.Dto.UpdateTransactionDto;
import thiagoopg.com.backend.Entity.Transaction;
import thiagoopg.com.backend.Service.ServiceTransaction;

import java.util.List;

@RestController
@RequestMapping("/Transaction/")
public class ControllerTransaction {
    final private ServiceTransaction serviceTransaction;

    public ControllerTransaction(ServiceTransaction serviceTransaction) {
        this.serviceTransaction = serviceTransaction;
    }

    @GetMapping
    public ResponseEntity<List<Transaction>> getAllTransactions() {
        List<Transaction> transactions = serviceTransaction.getAllTransaction();
        return ResponseEntity.ok(transactions);
    }

    // Create a new transaction
    @PostMapping
    public ResponseEntity<Void> createTransaction(@RequestBody CreateTransactionDto createTransactionDto) {
        serviceTransaction.createTransaction(createTransactionDto);
        return ResponseEntity.ok().build();
    }

    // Update a transaction's name (or other fields)
    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateTransactionName(
            @PathVariable Integer id,
            @RequestParam UpdateTransactionDto updateTransactionDto) {
        serviceTransaction.updateTransaction(updateTransactionDto);
        return ResponseEntity.noContent().build();
    }

    // Delete all transactions (use with caution!)
    @DeleteMapping
    public ResponseEntity<Void> deleteAllTransactions() {
        boolean isDeleted = serviceTransaction.deleteAllTransactions();
        return isDeleted ?
                ResponseEntity.noContent().build() :  // 204 if successful
                ResponseEntity.notFound().build();    // 404 if no transactions exist
    }
}
