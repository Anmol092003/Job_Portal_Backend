package com.jobportal.jobmicroservice.clients;

import com.jobportal.jobmicroservice.external.Review;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "REVIEWMICROSERVICE")
public interface ReviewClient {

    @GetMapping("api/reviews/by-company")
    List<Review> getReviews(@RequestParam("companyId") Long companyId);
}
