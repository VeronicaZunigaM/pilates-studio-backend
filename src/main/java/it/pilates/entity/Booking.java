package it.pilates.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "booking")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User client;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lesson_id", nullable = false)
    private Lesson lesson;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subscription_id", nullable = false)
    private Subscription subscription;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BookingStatus status;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    private LocalDateTime cancelledAt;

    protected Booking() {
    }

    public Booking(User client, Lesson lesson, Subscription subscription, LocalDateTime createdAt) {
        this.client = client;
        this.lesson = lesson;
        this.subscription = subscription;
        this.createdAt = createdAt;
        this.status = BookingStatus.CONFIRMED;
    }

    public boolean isActive() {
        return status == BookingStatus.CONFIRMED;
    }

    public void cancel(boolean late, LocalDateTime when) {
        if (!isActive()) {
            throw new IllegalStateException("Booking is already cancelled");
        }
        this.status = late ? BookingStatus.LATE_CANCELLED : BookingStatus.CANCELLED;
        this.cancelledAt = when;
    }

    public Long getId() { return id; }
    public User getClient() { return client; }
    public Lesson getLesson() { return lesson; }
    public Subscription getSubscription() { return subscription; }
    public BookingStatus getStatus() { return status; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getCancelledAt() { return cancelledAt; }
}