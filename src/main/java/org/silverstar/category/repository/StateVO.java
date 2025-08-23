package org.silverstar.category.repository;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.silverstar.category.domain.CategoryState;

@Embeddable
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StateVO {

    @Column(nullable = false, length = 1)
    @Convert(converter = StateYNConverter.class)
    private boolean useYn;

    @Column(nullable = false, length = 1)
    @Convert(converter = StateYNConverter.class)
    private boolean dispYn;

    public StateVO(CategoryState state) {
        this.useYn = state.isUseYn();
        this.dispYn = state.isDispYn();
    }

}
