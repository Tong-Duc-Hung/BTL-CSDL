package com.example.demo.Repository;

// Import các gói cần thiết từ Spring Data JPA và mô hình dữ liệu
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.Model.Entity.MovieEntity;
import java.sql.Date;
import java.util.List;

// Khai báo giao diện MovieRepository kế thừa từ JpaRepository
public interface MovieRepository extends JpaRepository<MovieEntity, String> {

    // Tìm phim theo ngày phát hành
    List<MovieEntity> findByReleaseDate(Date releaseDate);

    // Tìm phim theo thể loại không phân biệt hoa thường
    List<MovieEntity> findByGenresIgnoreCase(String genre);

    // Tìm phim theo đánh giá không phân biệt hoa thường
    List<MovieEntity> findByRatingIgnoreCase(String rating);

    // Tìm phim theo ngày phát hành, thể loại hoặc đánh giá, không phân biệt hoa thường
    List<MovieEntity> findByReleaseDateOrGenresIgnoreCaseOrRatingIgnoreCase(
        Date releaseDate, String genre, String rating
    );
}