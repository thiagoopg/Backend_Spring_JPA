package thiagoopg.com.backend.Dto;

import thiagoopg.com.backend.Enum.TransactionType;

public record CreateTransactionDto(String nome, float valor, TransactionType transactionType, Integer id) {
}
