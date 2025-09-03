package com.rms.restaurant_management_system_backend.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.rms.restaurant_management_system_backend.constant.ItemAvailability;
import com.rms.restaurant_management_system_backend.constant.ItemCategory;
import com.rms.restaurant_management_system_backend.domain.Items;
import com.rms.restaurant_management_system_backend.exception.RestaurantOperationException;
import com.rms.restaurant_management_system_backend.service.ItemsService;
import com.rms.restaurant_management_system_backend.utilities.CustomResponse;

import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/items")
public class ItemsController {

	private static final String UPLOAD_DIR = "uploads/dynamic/";
	private final ItemsService itemsService;

	public ItemsController(ItemsService itemsService) {
		this.itemsService = itemsService;
	}

	private String uploadImage(MultipartFile file) {
		String fileUrl;
		if (file != null && !file.isEmpty()) {
			String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
			Path filePath = Paths.get(UPLOAD_DIR, fileName);

			try {
				Files.write(filePath, file.getBytes());
				fileUrl = "http://localhost:8080/uploads/dynamic/" + fileName;
			} catch (IOException e) {
				throw new RestaurantOperationException("image storing error");
			}

		} else {
			fileUrl = "http://localhost:8080/uploads/default/profile123.png";
		}
		return fileUrl;
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping(value = "/addItem", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<CustomResponse> addItem(@RequestPart(value = "file", required = false) MultipartFile file,   
			@Valid @RequestPart("item") Items items) {

		String fileUrl = uploadImage(file);

		items.setImageUrl(fileUrl);

		Items item = itemsService.saveItem(items);
		CustomResponse response = new CustomResponse(true, "Item Saved Succussfully", item);
		return ResponseEntity.ok(response);

	}

	@GetMapping("getItem/{id}")
	public ResponseEntity<CustomResponse> getItemById(@PathVariable int id) {

		Items item = itemsService.getItemById(id);

		CustomResponse response = new CustomResponse(true, "Item Details Retrived Successfully", item);
		return ResponseEntity.ok(response);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping(value = "/updateItem/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<CustomResponse> updateItem(@PathVariable int id,
			@RequestPart(value = "file", required = false) MultipartFile file,
			@Valid @RequestPart("item") Items items) {

		if (file != null && (!file.isEmpty())) {
			items.setImageUrl(uploadImage(file));
		}

		Items item = itemsService.updateItem(id, items);
		CustomResponse response = new CustomResponse(true, "Item Updated Successfully", item);

		return ResponseEntity.ok(response);
	}

	@PostMapping("/availability/{id}")
	public ResponseEntity<CustomResponse> changeAvailability(@PathVariable int id) {

		Items item = itemsService.changeAvailability(id);
		CustomResponse response = new CustomResponse(true, "Item availability updated", item);
		return ResponseEntity.ok(response);

	}

	@GetMapping("/all")
	public ResponseEntity<CustomResponse> getAllItems() {
		List<Items> items = itemsService.getAllItems();
		CustomResponse response = new CustomResponse(true, "Items Retrived Successufully", items);
		return ResponseEntity.ok(response);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("delete/{id}")
	public ResponseEntity<CustomResponse> deleteItem(@PathVariable int id) {

		Items item = itemsService.deleteItem(id);
		CustomResponse response = new CustomResponse(true, "Item marked as INACTIVE", item);
		return ResponseEntity.ok(response);
	}

	@GetMapping("/categories")
	public ResponseEntity<CustomResponse> getCategories() {
		List<String> categories = Stream.of(ItemCategory.values()).map(category -> category.getName()).toList();
		CustomResponse response = new CustomResponse(true, "Item categories Retrived successfully!", categories);
		return ResponseEntity.ok(response);

	}

	@GetMapping("/getAvailabilityOptions")
	public ResponseEntity<CustomResponse> getAvailabilityOptions() {
		List<String> availabilities = Stream.of(ItemAvailability.values()).map(avilable -> avilable.getName()).toList();
		CustomResponse response = new CustomResponse(true, "Items Availabilities Retrived successfully!",
				availabilities);
		return ResponseEntity.ok(response);
	}

	@GetMapping("/availableItems")
	public ResponseEntity<CustomResponse> getAvailableItems() {
		List<Items> items = itemsService.getAllItems().stream()
				.filter(item -> item.getAvailable().equals(ItemAvailability.AVAILABLE)).toList();
		CustomResponse response = new CustomResponse(true, "Items Retrived Successufully", items);
		return ResponseEntity.ok(response);
	}

	@GetMapping("/searchItems")
	public ResponseEntity<CustomResponse> getSearchItems(@RequestParam("search") String search,
			@RequestParam("category") String category) {
		List<Items> items = itemsService.getSearchItems(search, category);
		CustomResponse response = new CustomResponse(true, "Searched Items Retrived Successufully", items);
		return ResponseEntity.ok(response);
	}

}
