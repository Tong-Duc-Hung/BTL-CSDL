package com.example.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.DTO.ReviewDTO.*;
import com.example.demo.Mapper.ReviewMapper;
import com.example.demo.Model.Entity.MovieEntity;
import com.example.demo.Model.Entity.ReviewEntity;
import com.example.demo.Model.Entity.UserEntity;
import com.example.demo.Repository.MovieRepository;
import com.example.demo.Repository.ReviewRepository;
import com.example.demo.Repository.UserRepository;
import com.example.demo.Exception.ValidationException;

import java.util.List;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ReviewMapper reviewMapper;

    // Thêm đánh giá mới
    public ReviewDTO createReview(ReviewDTO createReviewDTO) {
        // Lấy đối tượng Movie và User từ ID
        MovieEntity movie = movieRepository.findById(createReviewDTO.getMovieId())
            .orElseThrow(() -> new ValidationException.ResourceNotFoundException("Không tìm thấy phim"));
        UserEntity user = userRepository.findById(createReviewDTO.getUserId())
            .orElseThrow(() -> new ValidationException.ResourceNotFoundException("Không tìm thấy người dùng"));
    
        // Kiểm tra xem đánh giá đã tồn tại hay chưa
        if (reviewRepository.existsByMovie_MovieIdAndUser_UserId(movie.getMovieId(), user.getUserId())) {
            throw new ValidationException.InvalidInputException("Người dùng này đã đánh giá phim này rồi");
        }
    
        // Chuyển đổi DTO thành Entity và lưu vào cơ sở dữ liệu
        ReviewEntity review = reviewMapper.toReviewEntity(createReviewDTO);
        review.setMovie(movie);
        review.setUser(user);
        review = reviewRepository.save(review);

        // Trả về đối tượng DTO của đánh giá đã lưu
        return reviewMapper.toReviewDTO(review);
    }

    // Xem thông tin chi tiết đánh giá
    public ReviewDTO getReviewById(int reviewId) {
        // Tìm đánh giá theo ID
        ReviewEntity review = reviewRepository.findById(reviewId)
            .orElseThrow(() -> new ValidationException.ResourceNotFoundException("Không tìm thấy đánh giá"));
        return reviewMapper.toReviewDTO(review);
    }

    // Sửa đánh giá
    public ReviewDTO updateReview(int reviewId, ReviewDTO updateReviewDTO, int userId) {
        // Tìm đánh giá theo ID
        ReviewEntity review = reviewRepository.findById(reviewId)
            .orElseThrow(() -> new ValidationException.ResourceNotFoundException("Không tìm thấy đánh giá"));
    
        // Kiểm tra xem người dùng hiện tại có phải là người đã tạo đánh giá này không
        if (review.getUser().getUserId() != userId) {
            throw new ValidationException.ForbiddenException("Người dùng không có quyền sửa đánh giá này");
        }
    
        // Lấy đối tượng Movie từ ID
        MovieEntity movie = movieRepository.findById(updateReviewDTO.getMovieId())
            .orElseThrow(() -> new ValidationException.ResourceNotFoundException("Không tìm thấy phim"));
    
        // Cập nhật thông tin đánh giá
        review.setMovie(movie);
        review.setStarRating(updateReviewDTO.getStarRating());
        review.setReviewText(updateReviewDTO.getReviewText());
        review = reviewRepository.save(review);

        // Trả về đối tượng DTO của đánh giá đã sửa
        return reviewMapper.toReviewDTO(review);
    }

    // Xóa đánh giá
    public void deleteReview(int reviewId) {
        // Kiểm tra xem đánh giá có tồn tại không
        if (!reviewRepository.existsById(reviewId)) {
            throw new ValidationException.ResourceNotFoundException("Không tìm thấy đánh giá");
        }
        // Xóa đánh giá theo ID
        reviewRepository.deleteById(reviewId);
    }

    // Lấy danh sách tất cả các đánh giá
    public List<ReviewDTO> getAllReviews() {
        List<ReviewEntity> reviews = reviewRepository.findAll();
        return reviewMapper.toDTOList(reviews);
    }

    // Lấy danh sách đánh giá theo movieId
    public List<ReviewDTO> getReviewsByMovieId(String movieId) {
        List<ReviewEntity> reviews = reviewRepository.findByMovie_MovieId(movieId);
        return reviewMapper.toDTOList(reviews);
    }
}
