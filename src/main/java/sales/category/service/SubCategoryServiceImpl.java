package sales.category.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sales.category.domain.SubCategory;
import sales.category.repository.CategoryRepository;
import sales.category.repository.SubCategoryRepository;

import java.util.List;

/**
 * Created by taras on 11.08.15.
 */
@Service("subCategory")
public class SubCategoryServiceImpl implements SubCategoryService {

    @Autowired
    SubCategoryRepository subCategoryRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public List<SubCategory> findAll(Long id) {
        return subCategoryRepository.findByCategory(categoryRepository.findOne(id));
    }

    @Override
    public SubCategory findOne(Long id, Long idS) {
        return subCategoryRepository.findByCategoryAndId(categoryRepository.findOne(id), idS);
    }
}
