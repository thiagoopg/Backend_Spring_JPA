package thiagoopg.com.backend.Dto;

import thiagoopg.com.backend.Enum.TransactionType;

public record UpdateTransactionDto(
        Integer idTransaction,
        String name,
        float price,
        TransactionType transactionType,
        Integer idAccount) {
}
