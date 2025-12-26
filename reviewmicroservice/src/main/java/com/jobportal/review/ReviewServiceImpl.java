package com.jobportal.review;


import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }


    // CREATE
    @Override
    public Review createReview(Long companyId, Review review) {
         if(companyId==null || review==null){
             throw new RuntimeException("null is not recomened");
         }
        review.setCompanyId(companyId);
        return reviewRepository.save(review);

    }

    // READ BY ID
    @Override
    public Review getReviewById(Long id) {
        return reviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Review not found with id: " + id));
    }

    // READ ALL
    @Override
    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    // READ BY COMPANY ID
    @Override
    public List<Review> getReviewsByCompanyId(Long companyId) {
        return reviewRepository.findByCompanyId(companyId);
    }

    // UPDATE
    @Override
    public Review updateReview(Long id, Review updatedReview) {
        Review existingReview = getReviewById(id);

        existingReview.setTittle(updatedReview.getTittle());
        existingReview.setDescription(updatedReview.getDescription());
        existingReview.setRating(updatedReview.getRating());

        return reviewRepository.save(existingReview);
    }

    // DELETE
    @Override
    public void deleteReview(Long id) {
        reviewRepository.deleteById(id);
    }
}
