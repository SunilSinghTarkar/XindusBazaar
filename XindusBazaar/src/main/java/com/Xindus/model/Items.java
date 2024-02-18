package com.Xindus.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Items {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int itemId;
	@NotBlank(message = "Item name is required")
	public String itemName;
	
	@NotBlank(message = "Category is required")
	public String category;

	@NotNull(message = "Price is required")
	@Positive(message = "Price must be a positive value")
	public double price;

}
