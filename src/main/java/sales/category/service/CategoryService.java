package sales.category.service;

import sales.category.domain.Category;

import java.util.List;

/**
 * Created by taras on 11.08.15.
 */
public interface CategoryService {

   List<Category> findAll();

   Category findOne(Long id);
}
