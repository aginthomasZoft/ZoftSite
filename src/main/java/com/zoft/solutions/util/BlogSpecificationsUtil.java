package com.zoft.solutions.util;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import com.zoft.solutions.entity.BlogDetails;

public class BlogSpecificationsUtil {

	public static Specification<BlogDetails> findByTitleAndStatus(String title, String status) {
        return (root, query, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();

            if (title != null && !title.isEmpty()) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(root.get("title"), "%" + title + "%"));
            }

            if (status != null && !status.isEmpty()) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("status"), status));
            }

            return predicate;
        };
    }
}
