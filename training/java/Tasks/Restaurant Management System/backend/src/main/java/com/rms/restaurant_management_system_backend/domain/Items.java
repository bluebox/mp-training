package com.rms.restaurant_management_system_backend.domain;

import java.util.Objects;

import com.rms.restaurant_management_system_backend.constant.ItemAvailability;
import com.rms.restaurant_management_system_backend.constant.ItemCategory;
import com.rms.restaurant_management_system_backend.constant.ItemStatus;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class Items {

	private int id;

	@NotBlank(message = "Name cannot be Empty")
	@Size(min = 3, max = 50, message = "Name must be between 3 and 50 length")
	@Pattern(regexp = "[a-zA-Z -.()0-9]{0,50}", message = "Name only contain aplhabates,numbers -.()")
	private String name;

	private String imageUrl;

	@NotBlank(message = "Description cannot be Empty")
	@Size(min = 3, max = 100, message = "Description must be between 3 and 100 length")
	@Pattern(regexp = "[a-zA-Z0-9 :\\-.'&/,?!+]{0,100}", message = "Title only contain aplhabates,numbers -.&,?!+")
	private String description;

	@NotNull(message = "Price is required")
	private Double price;

	private ItemStatus status;

	private ItemAvailability available;

	@NotNull(message = "Category is required")
	private ItemCategory category;

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Items other = (Items) obj;
		return category == other.category && Objects.equals(description, other.description)
				&& Objects.equals(name, other.name) && Objects.equals(price, other.price)
				&& Objects.equals(imageUrl, other.imageUrl);
	}

	@Override
	public int hashCode() {
		return Objects.hash(category, description, name, price);
	}

}
