package com.example.demo.Model.Entity;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
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
    @Column(name = "movie_id", nullable = false, length = 45)
    private String movieId;

    /**
     * Tiêu đề của phim.
     */
    @Column(name = "title", nullable = false, length = 255)
    private String title;

    /**
     * Mô tả của phim.
     */
    @Column(name = "description", nullable = false, columnDefinition = "TEXT")
    private String description;

    /**
     * Thể loại của phim.
     */
    @Column(name = "genres", nullable = false, length = 255)
    private String genres;

    /**
     * Đạo diễn của phim.
     */
    @Column(name = "director", length = 255)
    private String director;

    /**
     * Diễn viên của phim.
     */
    @Column(name = "actor", length = 255)
    private String actor;

    /**
     * Nhà sản xuất của phim.
     */
    @Column(name = "producer", length = 255)
    private String producer;

    /**
     * Ngôn ngữ của phim.
     */
    @Column(name = "language", nullable = false, length = 100)
    private String language;

    /**
     * Thời lượng của phim.
     */
    @Column(name = "duration", nullable = false)
    private int duration;

    /**
     * Ngày phát hành của phim.
     */
    @Column(name = "release_date", nullable = false)
    private Date releaseDate;

    /**
     * Đánh giá của phim.
     */
    @Column(name = "rating", nullable = false, length = 10)
    private String rating;

    /**
     * Hình ảnh của phim.
     */
    @Column(name = "image", columnDefinition = "TEXT")
    private String image;

    /**
     * URL trailer của phim.
     */
    @Column(name = "trailer_url", length = 255)
    private String trailerUrl;

    /**
     * Giá phim.
     */
    @Column(name = "movie_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal moviePrice;

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
     * @param movieId Mã định danh của phim.
     * @param title Tiêu đề của phim.
     * @param description Mô tả của phim.
     * @param genres Thể loại của phim.
     * @param director Đạo diễn của phim.
     * @param actor Diễn viên của phim.
     * @param producer Nhà sản xuất của phim.
     * @param language Ngôn ngữ của phim.
     * @param duration Thời lượng của phim.
     * @param releaseDate Ngày phát hành của phim.
     * @param rating Đánh giá của phim.
     * @param image Hình ảnh của phim.
     * @param trailerUrl URL trailer của phim.
     * @param moviePrice Giá phim.
     */
    public MovieEntity(String movieId, String title, String description, String genres, String director, String actor,
                       String producer, String language, int duration, Date releaseDate, String rating,
                       String image, String trailerUrl, BigDecimal moviePrice) {
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

    // Các hàm getter và setter.
    
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

    public BigDecimal getMoviePrice() {
        return moviePrice;
    }

    public void setMoviePrice(BigDecimal moviePrice) {
        this.moviePrice = moviePrice;
    }

    /**
     * Lấy danh sách showtimes liên kết.
     *
     * @return danh sách showtimes liên kết.
     */
    public List<ShowtimeEntity> getShowtimes() {
        return showtimes;
    }

    /**
     * Lấy danh sách reviews liên kết.
     *
     * @return danh sách reviews liên kết.
     */
    public List<ReviewEntity> getReviews() {
        return reviews;
    }
}