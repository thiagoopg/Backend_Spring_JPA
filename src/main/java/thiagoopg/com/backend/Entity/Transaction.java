package thiagoopg.com.backend.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import thiagoopg.com.backend.Enum.TransactionType;

import java.time.Instant;

@Entity
@Table(name = "tb_transactions")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id_transaction;

    private String name;

    private float price;

    @Enumerated(EnumType.STRING)
    private TransactionType type;

    @CreationTimestamp
    private Instant creationTimestamp;

    @UpdateTimestamp
    private Instant updateTimestamp;

    @ManyToOne
    @JoinColumn(name = "account_id") // ou o nome correto da FK no seu banco
    private Account account;

    public Transaction(String name, float price, TransactionType transactionType, Account account) {
        this.name = name;
        this.price = price;
        this.type = transactionType;
        this.creationTimestamp = Instant.now();
        this.updateTimestamp = null;
        this.account = account;
    }
}
