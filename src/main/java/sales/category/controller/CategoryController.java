package sales.category.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import sales.category.domain.Category;
import sales.category.domain.SubCategory;
import sales.category.service.CategoryService;
import sales.category.service.SubCategoryService;

import java.util.List;

/**
 * Created by taras on 11.08.15.
 */
@RestController
@RequestMapping(value = "/category")
public class CategoryController {
    final static Logger logger = LoggerFactory.getLogger(CategoryController.class);

    @Autowired
    CategoryService categoryService;

    @Autowired
    SubCategoryService subCategoryService;

    @RequestMapping(
            method = RequestMethod.GET,
            value = "",
            produces = "application/json")
    public List<Category> getAllCategories() {
        logger.info("Get list of categories");
        return categoryService.findAll();
    }

    @RequestMapping(
            method = RequestMethod.GET,
            value = "/{id}",
            produces = "application/json")
    public Category getCategory(@PathVariable("id") Long id) {
        logger.info("Get category");
        return categoryService.findOne(id);
    }

    @RequestMapping(
            method = RequestMethod.GET,
            value = "/{id}/subcategory",
            produces = "application/json")
    public List<SubCategory> getAllSubCategories(@PathVariable("id") Long id) {
        logger.info("Get subCategory");
        return subCategoryService.findAll(id);
    }

    @RequestMapping(
            method = RequestMethod.GET,
            value = "/{id}/subcategory/{idS}",
            produces = "application/json")
    public SubCategory getSubCategory(@PathVariable("id") Long id,
                                    @PathVariable("idS") Long idS) {
        logger.info("Get category");
        return subCategoryService.findOne(id, idS);
    }
}
