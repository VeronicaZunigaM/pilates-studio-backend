package it.pilates.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "subscription")
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User client;

    @Column(nullable = false)
    private String packageName;

    @Column(nullable = false)
    private Integer totalCredits;

    @Column(nullable = false)
    private Integer remainingCredits;

    @Column(nullable = false)
    private LocalDate purchaseDate;

    @Column(nullable = false)
    private LocalDate expirationDate;

    protected Subscription() {
    }

    public Subscription(User client, String packageName, Integer totalCredits,
                        LocalDate purchaseDate, LocalDate expirationDate) {

        if (totalCredits == null || totalCredits <= 0) {
            throw new IllegalArgumentException("Credits must be greater than zero");
        }
        if (expirationDate.isBefore(purchaseDate)) {
            throw new IllegalArgumentException("Expiration date must be after purchase date");
        }

        this.client = client;
        this.packageName = packageName;
        this.totalCredits = totalCredits;
        this.remainingCredits = totalCredits;
        this.purchaseDate = purchaseDate;
        this.expirationDate = expirationDate;
    }

    public boolean isUsableOn(LocalDate date) {
        return remainingCredits > 0 && !date.isAfter(expirationDate);
    }

    public void useCredit() {
        if (remainingCredits <= 0) {
            throw new IllegalStateException("No credits left");
        }
        remainingCredits--;
    }

    public void refundCredit() {
        if (remainingCredits >= totalCredits) {
            throw new IllegalStateException("Cannot refund more credits than purchased");
        }
        remainingCredits++;
    }

    public Long getId() { return id; }
    public User getClient() { return client; }
    public String getPackageName() { return packageName; }
    public Integer getTotalCredits() { return totalCredits; }
    public Integer getRemainingCredits() { return remainingCredits; }
    public LocalDate getPurchaseDate() { return purchaseDate; }
    public LocalDate getExpirationDate() { return expirationDate; }
}