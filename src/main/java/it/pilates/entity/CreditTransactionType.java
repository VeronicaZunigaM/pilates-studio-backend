package it.pilates.entity;

public enum CreditTransactionType {
    PURCHASE,           // acquisto del pacchetto: +N
    BOOKING,            // credito usato per una prenotazione: -1
    REFUND,             // cancellazione nei termini: +1
    LATE_CANCELLATION   // cancellazione tardiva: 0, credito trattenuto (solo storico)
}