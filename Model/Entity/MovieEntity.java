package com.example.movieticketbooking.Model.Entity;

import java.util.*;
import jakarta.persistence.*;

/**
 * Lớp Entity đại diện cho bảng movies.
*/
@Entity
@Table(name = "movies")
public class MovieEntity {

    /**
     * Khóa chính của bảng.
    */
    @Id
    @Column(name = "movie_id", nullable = false, length = 45, unique = true)
    private String movie_id;

    /**
     * Tiêu đề của phim.
    */
    @Column(name = "title", nullable = false, length = 255, unique = true)
    private String title;

    /**
     * Mô tả của phim.
    */
    @Column(name = "description", nullable = false)
    private String description;

    /**
     * Thể loại của phim.
    */
    @Column(name = "genres", nullable = false)
    private String genres;

    /**
     * Đạo diễn của phim.
    */
    @Column(name = "director")
    private String director;

    /**
     * Diễn viên của phim.
    */
    @Column(name = "actor")
    private String actor;

    /**
     * Nhà sản xuất của phim.
    */
    @Column(name = "producter")
    private String producter;

    /**
     * Ngôn ngữ của phim.
    */
    @Column(name = "language")
    private String language;

    /**
     * Thời lượng của phim.
    */
    @Column(name = "duration", nullable = false)
    private String duration;

    /**
     * Ngày phát hành của phim.
    */
    @Column(name = "release_date", nullable = false)
    private String release_date;

    /**
     * Đánh giá của phim.
    */
    @Column(name = "rating", nullable = false)
    private String rating;

    /**
     * Hình ảnh của phim.
    */
    @Column(name = "image")
    private String image;

    /**
     * URL trailer của phim.
    */
    @Column(name = "trailer_url")
    private String trailer_url;

    /**
     * Giá phim.
    */
    @Column(name = "movie_price", nullable = false, precision = 10, scale = 2)
    private double movie_price;

    /**
     * Liên kết với bảng showtimes thông qua thuộc tính movie.
    */
    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ShowtimeEntity> showtimes = new ArrayList<>();

    /**
     * Liên kết với bảng reviews thông qua thuộc tính movie.
    */
    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ReviewEntity> reviews = new ArrayList<>();

    /**
     * Constructor mặc định.
    */
    public MovieEntity() {}

    /**
     * Constructor có tham số.
     *
     * @param movie_id Mã định danh của phim.
     * @param title Tiêu đề của phim.
     * @param description Mô tả của phim.
     * @param genres Thể loại của phim.
     * @param director Đạo diễn của phim.
     * @param actor Diễn viên của phim.
     * @param producter Nhà sản xuất của phim.
     * @param language Ngôn ngữ của phim.
     * @param duration Thời lượng của phim.
     * @param release_date Ngày phát hành của phim.
     * @param rating Đánh giá của phim.
     * @param image Hình ảnh của phim.
     * @param trailer_url URL trailer của phim.
     * @param movie_price Giá phim.
    */
    public MovieEntity(String movie_id, String title, String description, String genres, String director, String actor,
                       String producter, String language, String duration, String release_date, String rating,
                       String image, String trailer_url, double movie_price) {
        this.movie_id = movie_id;
        this.title = title;
        this.description = description;
        this.genres = genres;
        this.director = director;
        this.actor = actor;
        this.producter = producter;
        this.language = language;
        this.duration = duration;
        this.release_date = release_date;
        this.rating = rating;
        this.image = image;
        this.trailer_url = trailer_url;
        this.movie_price = movie_price;
    }

    // Các hàm getter và setter.
    
    public String getMovie_id() {
        return movie_id;
    }

    public void setMovie_id(String movie_id) {
        this.movie_id = movie_id;
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

    public String getProducter() {
        return producter;
    }

    public void setProducter(String producter) {
        this.producter = producter;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
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

    public String getTrailer_url() {
        return trailer_url;
    }

    public void setTrailer_url(String trailer_url) {
        this.trailer_url = trailer_url;
    }

    public double getMovie_price() {
        return movie_price;
    }

    public void setMovie_price(double movie_price) {
        this.movie_price = movie_price;
    }

    /**
     * Lấy danh sách showtimes liên kết.
     *
     * @return danh sách showtimes liên kết.
    */
    public List<ShowtimeEntity> showtimes()  {
        return showtimes;
    }

    /**
     * Lấy danh sách reviews liên kết.
     *
     * @return danh sách reviews liên kết.
    */
    public List<ReviewEntity> reviews() {
        return reviews;
    }
}