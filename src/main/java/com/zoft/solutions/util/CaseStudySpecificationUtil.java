package com.zoft.solutions.util;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import com.zoft.solutions.entity.CaseStudyDetails;

public class CaseStudySpecificationUtil {

	public static Specification<CaseStudyDetails> findByTitleAndStatus(String caseTitle, String status) {
        return (root, query, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();

            if (caseTitle != null && !caseTitle.isEmpty()) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(root.get("caseTitle"), "%" + caseTitle + "%"));
            }

            if (status != null && !status.isEmpty()) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("status"), status));
            }

            return predicate;
        };
    }
}
