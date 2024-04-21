package com.att.tdp.bisbis10.dto;

public class DishDto {
//    @NotBlank(message = "Name is required. Please add dish name.")
    private String name;

//    @NotBlank(message = "Description is required. Please add dish description")
    private String description;

//    @NotNull(message = "Price is required. Please add dish price.")
//    @DecimalMin(value = "0.0", inclusive = true, message = "Price must be positive")
//    @DecimalMax(value = Float.MAX_VALUE, message = "Price must not exceed " + Float.MAX_VALUE)
    private float price;

    public DishDto() {
    }

    public DishDto(String name, String description, float price) {
        setName(name);
        setDescription(description);
        setPrice(price);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) { this.name = name; }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
