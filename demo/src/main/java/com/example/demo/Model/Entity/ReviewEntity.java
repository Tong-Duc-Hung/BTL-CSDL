package com.example.demo.Model.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

/**
 * Lớp Entity đại diện cho bảng reviews.
 */
@Entity
@Table(name = "reviews")
public class ReviewEntity {

    /**
     * Khóa chính của bảng.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id", nullable = false, unique = true)
    private int reviewId;

    /**
     * Liên kết với bảng movies thông qua movie_id.
     */
    @ManyToOne
    @JoinColumn(name = "movie_id", nullable = false)
    private MovieEntity movie;

    /**
     * Liên kết với bảng users thông qua user_id.
     */
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    /**
     * Xếp hạng bằng sao (từ 1 đến 5).
     */
    @Min(1)
    @Max(5)
    @Column(name = "star_rating", nullable = false)
    private int starRating;

    /**
     * Nội dung đánh giá.
     */
    @Column(name = "review_text", nullable = false, columnDefinition = "TEXT")
    private String reviewText;

    /**
     * Constructor mặc định.
     */
    public ReviewEntity() {}

    /**
     * Constructor có tham số.
     *
     * @param movie Đối tượng movie liên kết.
     * @param user Đối tượng user liên kết.
     * @param starRating Xếp hạng bằng sao.
     * @param reviewText Nội dung đánh giá.
     */
    public ReviewEntity(MovieEntity movie, UserEntity user, int starRating, String reviewText) {
        this.movie = movie;
        this.user = user;
        this.starRating = starRating;
        this.reviewText = reviewText;
    }

    // Các hàm getter và setter.

    public int getReviewId() {
        return reviewId;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }

    public MovieEntity getMovie() {
        return movie;
    }

    public void setMovie(MovieEntity movie) {
        this.movie = movie;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public int getStarRating() {
        return starRating;
    }

    public void setStarRating(int starRating) {
        this.starRating = starRating;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }
}
