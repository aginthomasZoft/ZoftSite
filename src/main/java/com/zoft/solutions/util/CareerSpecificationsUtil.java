package com.zoft.solutions.util;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import com.zoft.solutions.entity.CareersDetails;

public class CareerSpecificationsUtil {

	public static Specification<CareersDetails> findByRoleAndStatus(String role, String status) {
        return (root, query, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();

            if (role != null && !role.isEmpty()) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(root.get("role"), "%" + role + "%"));
            }

            if (status != null && !status.isEmpty()) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("status"), status));
            }

            return predicate;
        };
    }
}
