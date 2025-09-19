package org.silverstar.category.domain;

import org.apache.logging.log4j.util.Strings;

public class CategoryImage {

    private final String imageUrl;

    public String getImageUrl() {
        return imageUrl;
    }

    public static CategoryImage create(String imageUrl) {
        if (Strings.isEmpty(imageUrl)) {
            throw new IllegalArgumentException("이미지URL를 선택해주세요.");
        }
        return new CategoryImage(imageUrl);
    }

    private CategoryImage(String imageUrl) {
        this.imageUrl = imageUrl;
    }

}
