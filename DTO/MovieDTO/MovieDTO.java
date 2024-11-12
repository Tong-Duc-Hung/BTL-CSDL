package com.example.demo.DTO.MovieDTO;

import java.sql.Date;

public class MovieDTO {
    private String movieId; // Mã phim
    private String title; // Tiêu đề
    private String description; // Mô tả
    private String genres; // Thể loại
    private String director; // Đạo diễn
    private String actor; // Diễn viên
    private String producer; // Nhà sản xuất
    private String language; // Ngôn ngữ
    private int duration; // Thời lượng
    private Date releaseDate; // Ngày phát hành
    private String rating; // Đánh giá
    private String image; // Ảnh
    private String trailerUrl; // Đường dẫn trailer
    private double moviePrice; // Giá vé phim

    // Hàm khởi tạo không tham số
    public MovieDTO() {}

    // Hàm khởi tạo có tham số
    public MovieDTO(String movieId, String title, String description, String genres, String director, String actor, String producer, String language, int duration, Date releaseDate, String rating, String image, String trailerUrl, double moviePrice) {
        this.movieId = movieId;
        this.title = title;
        this.description = description;
        this.genres = genres;
        this.director = director;
        this.actor = actor;
        this.producer = producer;
        this.language = language;
        this.duration = duration;
        this.releaseDate = releaseDate;
        this.rating = rating;
        this.image = image;
        this.trailerUrl = trailerUrl;
        this.moviePrice = moviePrice;
    }

    // Các phương thức getter và setter
    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTrailerUrl() {
        return trailerUrl;
    }

    public void setTrailerUrl(String trailerUrl) {
        this.trailerUrl = trailerUrl;
    }

    public double getMoviePrice() {
        return moviePrice;
    }

    public void setMoviePrice(double moviePrice) {
        this.moviePrice = moviePrice;
    }
}
