package it.pilates.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "credit_transaction")
public class CreditTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subscription_id", nullable = false, updatable = false)
    private Subscription subscription;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "booking_id", updatable = false)
    private Booking booking;

    @Column(nullable = false, updatable = false)
    private Integer amount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, updatable = false)
    private CreditTransactionType type;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    protected CreditTransaction() {
    }

    private CreditTransaction(Subscription subscription, Booking booking, Integer amount,
                              CreditTransactionType type, LocalDateTime createdAt) {
        this.subscription = subscription;
        this.booking = booking;
        this.amount = amount;
        this.type = type;
        this.createdAt = createdAt;
    }

    // --- Static factory: l'unico modo per creare un movimento ---

    public static CreditTransaction purchase(Subscription subscription, LocalDateTime when) {
        return new CreditTransaction(subscription, null,
                subscription.getTotalCredits(), CreditTransactionType.PURCHASE, when);
    }

    public static CreditTransaction booking(Booking booking, LocalDateTime when) {
        return new CreditTransaction(booking.getSubscription(), booking,
                -1, CreditTransactionType.BOOKING, when);
    }

    public static CreditTransaction refund(Booking booking, LocalDateTime when) {
        return new CreditTransaction(booking.getSubscription(), booking,
                1, CreditTransactionType.REFUND, when);
    }

    public static CreditTransaction lateCancellation(Booking booking, LocalDateTime when) {
        return new CreditTransaction(booking.getSubscription(), booking,
                0, CreditTransactionType.LATE_CANCELLATION, when);
    }

    public Long getId() { return id; }
    public Subscription getSubscription() { return subscription; }
    public Booking getBooking() { return booking; }
    public Integer getAmount() { return amount; }
    public CreditTransactionType getType() { return type; }
    public LocalDateTime getCreatedAt() { return createdAt; }
}