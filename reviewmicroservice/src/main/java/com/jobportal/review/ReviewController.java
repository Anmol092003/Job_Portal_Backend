package com.jobportal.review;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@CrossOrigin(origins = "*")
public class ReviewController {
    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    // 1️⃣ CREATE REVIEW FOR A COMPANY
    @PostMapping("/companies/{companyId}")
    public ResponseEntity<Review> createReview(
            @PathVariable Long companyId,
            @RequestBody Review review) {

        Review createdReview = reviewService.createReview(companyId, review);
        return new ResponseEntity<>(createdReview, HttpStatus.CREATED);
    }

    // 2️⃣ GET REVIEW BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Review> getReviewById(@PathVariable Long id) {
        Review review = reviewService.getReviewById(id);
        return ResponseEntity.ok(review);
    }

    // 3️⃣ GET ALL REVIEWS
    @GetMapping("")
    public ResponseEntity<List<Review>> getAllReviews() {
        return ResponseEntity.ok(reviewService.getAllReviews());
    }

    // 4️⃣ GET REVIEWS BY COMPANY ID
    @GetMapping("/by-company")
    public ResponseEntity<List<Review>> getReviewsByCompanyId(
            @RequestParam Long companyId) {

        return ResponseEntity.ok(
                reviewService.getReviewsByCompanyId(companyId));
    }

    // 5️⃣ UPDATE REVIEW
    @PutMapping("/{id}")
    public ResponseEntity<Review> updateReview(
            @PathVariable Long id,
            @RequestBody Review updatedReview) {

        Review review = reviewService.updateReview(id, updatedReview);
        return ResponseEntity.ok(review);
    }

    // 6️⃣ DELETE REVIEW
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteReview(@PathVariable Long id) {
        reviewService.deleteReview(id);
        return ResponseEntity.ok("Review deleted successfully");
    }
}
