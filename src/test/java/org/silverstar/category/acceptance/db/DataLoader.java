package org.silverstar.category.acceptance.db;

import org.silverstar.category.controller.dto.CreateCategoryRequestDto;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

import static org.silverstar.category.acceptance.steps.CategoryAcceptanceSteps.createCategory;

@Component
public class DataLoader {

    public void loadData() {
        CreateCategoryRequestDto dto = new CreateCategoryRequestDto(
                "카테고리", null, true, true, Arrays.asList("imageUrl1", "imageUrl2")
        );
        createCategory(dto);
    }
}