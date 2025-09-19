package org.silverstar.category.repository.entity;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.silverstar.category.domain.Category;
import org.silverstar.category.domain.CategoryImage;
import org.silverstar.category.repository.common.EntityBase;
import org.silverstar.category.repository.common.StateVO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name="tb_category")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class CategoryEntity extends EntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

    @Embedded
    private StateVO state;

    @Column(name = "parent_id")
    private Long parentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="parent_id",
            foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT),
            insertable = false, updatable = false
    )
    private CategoryEntity parent;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CategoryImageEntity> images = new ArrayList<>();

    public static CategoryEntity createCategory(Category category) {
        return new CategoryEntity(category);
    }

    private CategoryEntity(Category category) {
        this.id = category.getId();
        this.name = category.getName();
        this.state = new StateVO(category.getState());
        this.parentId = category.getParentId();
        for (CategoryImage img : category.getImages()) {
            CategoryImageEntity entity = CategoryImageEntity.createCategoryImage(img, this);
            this.images.add(entity);
        }
    }

    public void updateCategory(Category category) {
        this.name = category.getName();
        this.parentId = category.getParentId();
        this.state = new StateVO(category.getState());

        for (int i = 0; i < this.images.size(); i++) {
            this.images.get(i).updateCategoryImage(category.getImages().get(i));
        }
    }

    public Category toCategory() {
        return Category.create(
                this.id,
                this.name,
                this.parentId,
                this.state.toCategoryState(),
                this.images.stream().map(CategoryImageEntity::toCategoryImage).collect(Collectors.toList())
        );
    }

}
