package com.example.thenewz;

public class CategoryRVModal {

    private String category;
    private String category_imageUrl;

    public CategoryRVModal(String category, String category_imageUrl) {
        this.category = category;
        this.category_imageUrl = category_imageUrl;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategory_imageUrl() {
        return category_imageUrl;
    }

    public void setCategory_imageUrl(String category_imageUrl) {
        this.category_imageUrl = category_imageUrl;
    }
}
