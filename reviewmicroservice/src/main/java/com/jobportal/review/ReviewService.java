package com.jobportal.review;

import java.util.List;

public interface ReviewService {
    Review createReview(Long companyId, Review review);

    Review getReviewById(Long id);

    List<Review> getAllReviews();

    List<Review> getReviewsByCompanyId(Long companyId);

    Review updateReview(Long id, Review updatedReview);

    void deleteReview(Long id);

}
