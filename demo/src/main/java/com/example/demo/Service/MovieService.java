package com.example.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.DTO.MovieDTO.MovieDTO;
import com.example.demo.Mapper.MovieMapper;
import com.example.demo.Model.Entity.MovieEntity;
import com.example.demo.Repository.MovieRepository;
import com.example.demo.Exception.ValidationException;
import java.sql.Date;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private MovieMapper movieMapper;

    // Tạo phim mới
    public MovieDTO createMovie(MovieDTO movieDTO) {
        // Kiểm tra xem movieId có tồn tại hay không
        if (movieRepository.existsById(movieDTO.getMovieId())) {
            throw new ValidationException.InvalidInputException("Phim với ID này đã tồn tại");
        }
    
        // Chuyển đổi DTO thành Entity và lưu vào cơ sở dữ liệu
        MovieEntity movieEntity = movieMapper.toMovieEntity(movieDTO);
        MovieEntity savedMovie = movieRepository.save(movieEntity);

        // Trả về đối tượng DTO của phim đã lưu
        return movieMapper.toMovieInfoDTO(savedMovie);
    }

    // Xóa phim theo ID
    public void deleteMovie(String movieId) {
        // Kiểm tra xem phim có tồn tại hay không
        if (!movieRepository.existsById(movieId)) {
            throw new ValidationException.ResourceNotFoundException("Không tìm thấy phim");
        }
        // Xóa phim theo ID
        movieRepository.deleteById(movieId);
    }

    // Lấy danh sách tất cả phim
    public List<MovieDTO> getAllMovies() {
        List<MovieEntity> movies = movieRepository.findAll();
        return movieMapper.toDTOList(movies);
    }

    // Lấy thông tin phim theo ID
    public Optional<MovieDTO> getMovieById(String movieId) {
        Optional<MovieEntity> movieEntity = movieRepository.findById(movieId);
        // Kiểm tra xem phim có tồn tại hay không
        if (movieEntity.isEmpty()) {
            throw new ValidationException.ResourceNotFoundException("Không tìm thấy phim");
        }
        return movieEntity.map(movieMapper::toMovieInfoDTO);
    }

    // Tìm phim theo ngày phát hành
    public List<MovieDTO> findMoviesByReleaseDate(Date releaseDate) {
        List<MovieEntity> movies = movieRepository.findByReleaseDate(releaseDate);
        // Kiểm tra nếu không tìm thấy phim nào
        if (movies.isEmpty()) {
            throw new ValidationException.ResourceNotFoundException("Không tìm thấy phim cho ngày phát hành đã cho");
        }
        return movieMapper.toDTOList(movies);
    }

    // Tìm phim theo thể loại không phân biệt hoa thường
    public List<MovieDTO> findMoviesByGenre(String genre) {
        List<MovieEntity> movies = movieRepository.findAll();
        // Lọc danh sách phim theo thể loại
        List<MovieEntity> filteredMovies = movies.stream()
                .filter(movie -> movie.getGenres() != null && 
                                 Arrays.stream(movie.getGenres().split(","))
                                       .map(String::trim)
                                       .anyMatch(g -> g.equalsIgnoreCase(genre)))
                .collect(Collectors.toList());
        // Kiểm tra nếu không tìm thấy phim nào
        if (filteredMovies.isEmpty()) {
            throw new ValidationException.ResourceNotFoundException("Không tìm thấy phim cho thể loại đã cho");
        }
        return movieMapper.toDTOList(filteredMovies);
    }

    // Tìm phim theo đánh giá không phân biệt hoa thường
    public List<MovieDTO> findMoviesByRating(String rating) {
        List<MovieEntity> movies = movieRepository.findByRatingIgnoreCase(rating);
        // Kiểm tra nếu không tìm thấy phim nào
        if (movies.isEmpty()) {
            throw new ValidationException.ResourceNotFoundException("Không tìm thấy phim cho đánh giá đã cho");
        }
        return movieMapper.toDTOList(movies);
    }

    // Tìm phim theo ngày phát hành, thể loại hoặc đánh giá không phân biệt hoa thường
    public List<MovieDTO> findMoviesByReleaseDateOrGenreOrRating(Date releaseDate, String genre, String rating) {
        List<MovieEntity> movies = movieRepository.findByReleaseDateOrGenresIgnoreCaseOrRatingIgnoreCase(releaseDate, genre, rating);
        // Kiểm tra nếu không tìm thấy phim nào
        if (movies.isEmpty()) {
            throw new ValidationException.ResourceNotFoundException("Không tìm thấy phim cho các tiêu chí đã cho");
        }
        return movieMapper.toDTOList(movies);
    }
}
