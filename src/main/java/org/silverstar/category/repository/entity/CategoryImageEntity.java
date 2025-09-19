package org.silverstar.category.repository.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.silverstar.category.domain.CategoryImage;
import org.silverstar.category.repository.common.EntityBase;

@Entity
@Table(name = "tb_category_image")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class CategoryImageEntity extends EntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String imageUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private CategoryEntity category;

    public static CategoryImageEntity createCategoryImage(CategoryImage domain, CategoryEntity category) {
        CategoryImageEntity entity = new CategoryImageEntity();
        entity.imageUrl = domain.getImageUrl();
        entity.category = category;
        return entity;
    }

    public void updateCategoryImage(CategoryImage domain) {
        this.imageUrl = domain.getImageUrl();
    }

    public CategoryImage toCategoryImage() {
        return CategoryImage.create(
                this.imageUrl
        );
    }
}
