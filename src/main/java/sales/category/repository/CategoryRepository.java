package sales.category.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sales.category.domain.Category;

/**
 * Created by taras on 11.08.15.
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
