package it.pilates.entity;

public enum BookingStatus {
    CONFIRMED,       // prenotazione attiva
    CANCELLED,       // cancellata nei termini: credito restituito
    LATE_CANCELLED   // cancellata in ritardo: credito trattenuto
}
