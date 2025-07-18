package com.product.category.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.product.Exceptions.CategoryDatabaseOperationException;
import com.product.Exceptions.CategoryRequestDatabaseOperationException;
import com.product.Exceptions.CategoryRequestNotFoundException;
import com.product.Exceptions.InvalidCategoryRequestException;
import com.product.category.dao.CategoryDAO;
import com.product.category.domain.Category;
import com.product.category.domain.CategoryRequest;
import com.product.category.domain.CategoryRequestSearchCriteria;
import com.product.category.domain.CategorySearchCriteria;
import com.product.category.service.CategoryService;
import com.product.enums.RequestStatus;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryDAO categoryDAO;

	private final List<Category> categoryList = new ArrayList<>();
	private final List<CategoryRequest> categoryRequestList = new ArrayList<>();

	@Override
	@Transactional
	public void createCategoryRequestService(CategoryRequest categoryRequest)
			throws InvalidCategoryRequestException, CategoryRequestDatabaseOperationException

	{
		if (categoryRequest == null) {
			throw new InvalidCategoryRequestException("Category request must not be null.");
		}
		if (categoryRequest.getCategoryName() == null || categoryRequest.getCategoryName().trim().isEmpty()) {
			throw new InvalidCategoryRequestException("Category name must not be null or empty.");
		}

		try {
			categoryDAO.createCategoryRequest(categoryRequest);
		} catch (Exception e) {
			throw new CategoryRequestDatabaseOperationException("Failed to create category request.", e);
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void processCategoryRequest(List<Long> requestIds, Integer approvedBy, RequestStatus status)
			throws CategoryRequestNotFoundException, CategoryRequestDatabaseOperationException {

		try {
			categoryDAO.updateCategoryRequest(requestIds, approvedBy, status);

			if (status == RequestStatus.APPROVED) {
				List<String> allCategoryNames = new ArrayList<>();

				for (Long requestId : requestIds) {
					CategoryRequestSearchCriteria criteria = new CategoryRequestSearchCriteria();
					criteria.setRequestId(requestId);

					List<CategoryRequest> requests = categoryDAO.getRequest(criteria);

					if (requests == null || requests.isEmpty()) {
						throw new CategoryRequestNotFoundException(
								"No category request found for request ID: " + requestId);
					}

	                for (CategoryRequest req : requests) {
	                    if (req.getCategoryName() != null && !req.getCategoryName().trim().isEmpty()) {
	                        allCategoryNames.add(req.getCategoryName().trim());
	                    }
	                }
	            }

				List<String> uniqueCategoryNames = allCategoryNames.stream().distinct().collect(Collectors.toList());

				if (!uniqueCategoryNames.isEmpty()) {
					System.out.println("not empty list");
					for (String categoryName : uniqueCategoryNames) {
						Category category = new Category();
						category.setCategoryName(categoryName);
						category.setCreatedAtDate(LocalDateTime.now());
						categoryDAO.createCategory(category);
					}
				}
			}

		} catch (Exception e) {
			throw new CategoryRequestDatabaseOperationException("Failed to process category requests", e);
		}
	}

	@Override
	@Transactional
	public void updateCategoryRequestService(List<Long> requestIds, Integer approvedBy, RequestStatus status)
			throws CategoryRequestNotFoundException, CategoryRequestDatabaseOperationException {

		if (requestIds == null || requestIds.isEmpty()) {
			throw new CategoryRequestNotFoundException("Request IDs must not be null or empty.");
		}
		if (approvedBy == null || approvedBy <= 0) {
			throw new CategoryRequestNotFoundException("ApprovedBy must be a valid user ID.");
		}

		try {
			categoryDAO.updateCategoryRequest(requestIds, approvedBy, status);
		} catch (Exception e) {
			throw new CategoryRequestDatabaseOperationException("Failed to approve category requests.", e);
		}
	}

	@Override
	@Transactional
	public List<CategoryRequest> getCategoryRequestService(CategoryRequestSearchCriteria searchCriteria)
			throws CategoryRequestNotFoundException, CategoryRequestDatabaseOperationException {

		if (searchCriteria == null) {
			throw new CategoryRequestNotFoundException("Search criteria must not be null.");
		}

		try {
			System.out.println("search criteria created");
			List<CategoryRequest> result = categoryDAO.getRequest(searchCriteria);
			System.out.println("search criteria executed");

			if (result == null || result.isEmpty()) {
				throw new CategoryRequestNotFoundException("No category requests found for the given criteria.");
			}

			return result;
		} catch (CategoryRequestNotFoundException e) {
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new CategoryRequestDatabaseOperationException("Failed to retrieve category requests.", e);
		}
	}

	@Override
	@Transactional
	public void createCategoryService(Category category) throws CategoryDatabaseOperationException {
		if (category == null) {
			throw new CategoryDatabaseOperationException("Category must not be null.");
		}
		if (category.getCategoryName() == null || category.getCategoryName().trim().isEmpty()) {
			throw new CategoryDatabaseOperationException("Category name must not be null or empty.");
		}

		try {
			categoryDAO.createCategory(category);
		} catch (Exception e) {
			throw new CategoryDatabaseOperationException("Failed to create category.", e);
		}
	}

	@Override
	@Transactional
	public List<Category> getCategoryService(CategorySearchCriteria searchCriteria)
			throws CategoryDatabaseOperationException {

		if (searchCriteria == null) {
			throw new CategoryDatabaseOperationException("Search criteria must not be null.");
		}

		try {
			System.out.println("service called");
			List<Category> result = categoryDAO.get(searchCriteria);

			System.out.println(result);

			if (result == null || result.isEmpty()) {
				throw new CategoryDatabaseOperationException("No categories found for the given criteria.");
			}

			return result;
		} catch (Exception e) {
			throw new CategoryDatabaseOperationException("Failed to retrieve categories.", e);
		}
	}
}
