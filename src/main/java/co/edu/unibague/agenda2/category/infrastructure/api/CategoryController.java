package co.edu.unibague.agenda2.category.infrastructure.api;

import co.edu.unibague.agenda2.category.domain.Category;
import co.edu.unibague.agenda2.category.domain.usecases.CreateCategory;
import co.edu.unibague.agenda2.category.domain.usecases.RetrieveCategoryGroup;
import co.edu.unibague.agenda2.category.domain.usecases.RetrieveCategory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CreateCategory categoryCreator;
    private final RetrieveCategory categoryRetriever;
    private final RetrieveCategoryGroup categoryGroupRetriever;

    public CategoryController(CreateCategory categoryCreator, RetrieveCategory categoryRetriever, RetrieveCategoryGroup categoryGroupRetriever) {
        this.categoryCreator = categoryCreator;
        this.categoryRetriever = categoryRetriever;
        this.categoryGroupRetriever = categoryGroupRetriever;
    }

    @PostMapping
    public ResponseEntity<Void> createCategory(@RequestBody CategoryInput categoryInput) {
        var category = categoryGroupRetriever.getCategoryGroup(categoryInput.categoryGroupId()).orElseThrow();
        categoryCreator.createCategory(Category.createCategory(categoryInput.id(), categoryInput.name(), category));
        log.info("Sub category created: {}", categoryInput);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponse>> getSCategories() {
        List<Category> categoryList = categoryRetriever.getAllCategories();
        List<CategoryResponse> categoryResponses = categoryList.stream().map(
                category -> new CategoryResponse(
                        category.getId(),
                        category.getName(),
                        category.getCategoryGroup().getId(),
                        category.getCategoryGroup().getName()
                )
        ).toList();
        log.info("Sub categories retrieved: {}", categoryResponses.size());
        return ResponseEntity.ok().body(categoryResponses);
    }
}
