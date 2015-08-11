package sales.category.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sales.category.domain.Category;
import sales.category.repository.CategoryRepository;

import java.util.List;

/**
 * Created by taras on 11.08.15.
 */
@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findOne(Long id) {
        return categoryRepository.findOne(id);
    }
}
