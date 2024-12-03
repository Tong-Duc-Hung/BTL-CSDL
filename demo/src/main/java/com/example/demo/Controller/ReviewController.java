package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.DTO.ReviewDTO.*;
import com.example.demo.Service.ReviewService;
import com.example.demo.Config.RequiresRole;
import com.example.demo.Model.Entity.UserEntity.Role;
import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
    
    @Autowired
    private ReviewService reviewService;

    /**
     * Tạo đánh giá mới - chỉ cho phép vai trò CUSTOMER.
     * 
     * @param createReviewDTO Thông tin đánh giá cần tạo.
     * @return Thông tin đánh giá vừa được tạo.
     */
    @RequiresRole(Role.customer)
    @PostMapping("/create")
    public ResponseEntity<ReviewDTO> createReview(@RequestBody ReviewDTO createReviewDTO) {
        ReviewDTO createdReview = reviewService.createReview(createReviewDTO);
        return ResponseEntity.ok(createdReview);
    }

    /**
     * Lấy thông tin đánh giá theo ID - chỉ cho phép vai trò CUSTOMER.
     * 
     * @param reviewId ID của đánh giá.
     * @return Thông tin đánh giá tương ứng với ID.
     */
    @RequiresRole(Role.customer)
    @GetMapping("/{reviewId}")
    public ResponseEntity<ReviewDTO> getReviewById(@PathVariable int reviewId) {
        ReviewDTO review = reviewService.getReviewById(reviewId);
        return ResponseEntity.ok(review);
    }

    /**
     * Cập nhật đánh giá theo ID - chỉ cho phép vai trò CUSTOMER.
     * 
     * @param reviewId ID của đánh giá cần cập nhật.
     * @param updateReviewDTO Thông tin đánh giá cần cập nhật.
     * @param userId ID của người dùng thực hiện cập nhật.
     * @return Thông tin đánh giá sau khi cập nhật.
     */
    @RequiresRole(Role.customer)
    @PutMapping("/{reviewId}/{userId}")
    public ResponseEntity<ReviewDTO> updateReview(@PathVariable int reviewId, @RequestBody ReviewDTO updateReviewDTO, @PathVariable int userId) {
        ReviewDTO updatedReview = reviewService.updateReview(reviewId, updateReviewDTO, userId);
        return ResponseEntity.ok(updatedReview);
    }

    /**
     * Xóa đánh giá theo ID - chỉ cho phép vai trò ADMIN.
     * 
     * @param reviewId ID của đánh giá cần xóa.
     * @return Trả về mã trạng thái 200 khi xóa thành công.
     */
    @RequiresRole(Role.admin)
    @DeleteMapping("/{reviewId}")
    public ResponseEntity<Void> deleteReview(@PathVariable int reviewId) {
        reviewService.deleteReview(reviewId);
        return ResponseEntity.ok().build();
    }

    /**
     * Lấy danh sách tất cả đánh giá.
     * 
     * @return Danh sách tất cả các đánh giá.
     */
    @GetMapping
    public ResponseEntity<List<ReviewDTO>> getAllReviews() {
        List<ReviewDTO> reviews = reviewService.getAllReviews();
        return ResponseEntity.ok(reviews);
    }

    /**
     * Lấy tất cả đánh giá theo phim.
     * 
     * @param movieId ID của phim.
     * @return Danh sách các đánh giá tương ứng với movieId.
     */
    @GetMapping("/movie/{movieId}")
    public ResponseEntity<List<ReviewDTO>> getReviewsByMovieId(@PathVariable String movieId) {
        List<ReviewDTO> reviews = reviewService.getReviewsByMovieId(movieId);
        return ResponseEntity.ok(reviews);
    }
}
