package org.silverstar.category.domain;

import java.util.Objects;

public class CategoryState {

    private final Boolean useYn;
    private final Boolean dispYn;

    public Boolean isUseYn() {
        return useYn;
    }

    public Boolean isDispYn() {
        return dispYn;
    }

    public static CategoryState create(Boolean useYn, Boolean dispYn) {
        if (Objects.isNull(useYn) || Objects.isNull(dispYn)) {
            throw new IllegalArgumentException("사용여부와 전시여부를 선택해주세요.");
        }
        return new CategoryState(useYn, dispYn);
    }

    private CategoryState(Boolean useYn, Boolean dispYn) {
        this.useYn = useYn;
        this.dispYn = dispYn;
    }

}
