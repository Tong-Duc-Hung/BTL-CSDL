package com.example.demo.Controller;

import com.example.demo.DTO.MovieDTO.MovieDTO;
import com.example.demo.Service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.Config.RequiresRole;
import com.example.demo.Model.Entity.UserEntity.Role;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    /**
     * Tạo phim mới - chỉ cho phép các vai trò ADMIN và STAFF.
     * 
     * @param movieDTO Thông tin phim cần tạo.
     * @return Thông tin phim vừa được tạo.
     */
    @RequiresRole({Role.admin, Role.staff})
    @PostMapping("/create")
    public ResponseEntity<MovieDTO> createMovie(@RequestBody MovieDTO movieDTO) {
        MovieDTO createdMovie = movieService.createMovie(movieDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMovie);
    }

    /**
     * Xóa phim theo ID - chỉ cho phép vai trò ADMIN.
     * 
     * @param movieId ID của phim cần xóa.
     * @return Trả về mã trạng thái 204 khi xóa thành công.
     */
    @RequiresRole(Role.admin)
    @DeleteMapping("/{movieId}")
    public ResponseEntity<Void> deleteMovie(@PathVariable("movieId") String movieId) {
        movieService.deleteMovie(movieId);
        return ResponseEntity.noContent().build();
    }

    /**
     * Lấy danh sách tất cả phim.
     * 
     * @return Danh sách tất cả các phim.
     */
    @GetMapping
    public ResponseEntity<List<MovieDTO>> getAllMovies() {
        List<MovieDTO> movies = movieService.getAllMovies();
        return ResponseEntity.ok(movies);
    }

    /**
     * Lấy thông tin phim theo ID.
     * 
     * @param movieId ID của phim.
     * @return Thông tin phim tương ứng với ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<MovieDTO> getMovieById(@PathVariable("id") String movieId) {
        return movieService.getMovieById(movieId)
                .map(movieDTO -> ResponseEntity.ok(movieDTO))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    /**
     * Tìm phim theo ngày phát hành.
     * 
     * @param releaseDate Ngày phát hành của phim.
     * @return Danh sách các phim tương ứng với ngày phát hành.
     */
    @GetMapping("/release-date")
    public ResponseEntity<List<MovieDTO>> findMoviesByReleaseDate(@RequestParam Date releaseDate) {
        List<MovieDTO> movies = movieService.findMoviesByReleaseDate(releaseDate);
        return ResponseEntity.ok(movies);
    }

    /**
     * Tìm phim theo thể loại (không phân biệt hoa thường).
     * 
     * @param genre Thể loại của phim.
     * @return Danh sách các phim tương ứng với thể loại.
     */
    @GetMapping("/genre")
    public ResponseEntity<List<MovieDTO>> findMoviesByGenre(@RequestParam String genre) {
        List<MovieDTO> movies = movieService.findMoviesByGenre(genre.toLowerCase());
        return ResponseEntity.ok(movies);
    }

    /**
     * Tìm phim theo đánh giá (không phân biệt hoa thường).
     * 
     * @param rating Đánh giá của phim.
     * @return Danh sách các phim tương ứng với đánh giá.
     */
    @GetMapping("/rating")
    public ResponseEntity<List<MovieDTO>> findMoviesByRating(@RequestParam String rating) {
        List<MovieDTO> movies = movieService.findMoviesByRating(rating.toLowerCase());
        return ResponseEntity.ok(movies);
    }

    /**
     * Tìm phim theo ngày phát hành, thể loại hoặc đánh giá (không phân biệt hoa thường).
     * 
     * @param releaseDate Ngày phát hành của phim (tùy chọn).
     * @param genre Thể loại của phim (tùy chọn).
     * @param rating Đánh giá của phim (tùy chọn).
     * @return Danh sách các phim tương ứng với các tiêu chí tìm kiếm.
     */
    @GetMapping("/search")
    public ResponseEntity<List<MovieDTO>> findMovies(
            @RequestParam(required = false) Date releaseDate,
            @RequestParam(required = false) String genre,
            @RequestParam(required = false) String rating) {
        List<MovieDTO> movies = movieService.findMoviesByReleaseDateOrGenreOrRating(
                releaseDate, 
                genre != null ? genre.toLowerCase() : null, 
                rating != null ? rating.toLowerCase() : null
        );
        return ResponseEntity.ok(movies);
    }
}
