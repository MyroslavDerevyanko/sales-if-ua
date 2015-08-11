package sales.category.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sales.category.domain.Category;
import sales.category.domain.SubCategory;

import java.util.List;

/**
 * Created by taras on 11.08.15.
 */
public interface SubCategoryRepository extends JpaRepository<SubCategory, Long> {
    List<SubCategory> findByCategory(Category category);

    SubCategory findByCategoryAndId(Category category, Long id);
}
