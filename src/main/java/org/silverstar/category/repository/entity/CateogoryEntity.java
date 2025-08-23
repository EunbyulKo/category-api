package org.silverstar.category.repository.entity;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.silverstar.category.domain.Category;
import org.silverstar.category.repository.EntityBase;
import org.silverstar.category.repository.StateVO;

@Entity
@Table(name="tb_category")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class CateogoryEntity extends EntityBase {

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
    private CateogoryEntity parent;

    public CateogoryEntity(Category category) {
        this.id = category.getId();
        this.name = category.getName();
        this.state = new StateVO(category.getState());
        this.parentId = category.getParentId();

    }

}
