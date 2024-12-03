package com.example.demo.DTO.ReviewDTO;

public class ReviewDTO {
    private int reviewId; // Mã đánh giá
    private String movieId; // Mã phim
    private int userId; // Mã người dùng
    private int starRating; // Đánh giá sao
    private String reviewText; // Nội dung đánh giá

    // Hàm khởi tạo không tham số
    public ReviewDTO() {}

    // Hàm khởi tạo có tham số
    public ReviewDTO(int reviewId, String movieId, int userId, int starRating, String reviewText) {
        this.reviewId = reviewId;
        this.movieId = movieId;
        this.userId = userId;
        this.starRating = starRating;
        this.reviewText = reviewText;
    }

    // Các phương thức getter và setter
    public int getReviewId() {
        return reviewId;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }
    
    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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
