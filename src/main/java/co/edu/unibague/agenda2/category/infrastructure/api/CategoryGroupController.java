package co.edu.unibague.agenda2.category.infrastructure.api;

import co.edu.unibague.agenda2.category.domain.CategoryGroup;
import co.edu.unibague.agenda2.category.domain.usecases.CreateCategoryGroup;
import co.edu.unibague.agenda2.category.domain.usecases.RetrieveCategoryGroup;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/categoryGroups")
public class CategoryGroupController {

    private final CreateCategoryGroup categoryCreator;
    private final RetrieveCategoryGroup categoryRetriever;

    public CategoryGroupController(CreateCategoryGroup categoryCreator, RetrieveCategoryGroup categoryRetriever) {
        this.categoryCreator = categoryCreator;
        this.categoryRetriever = categoryRetriever;
    }

    @PostMapping
    public ResponseEntity<Void> createCategory(@RequestBody CategoryGroupInput categoryGroupInput) {
        categoryCreator.createCategoryGroup(CategoryGroup.createCategoryGroup(categoryGroupInput.id(), categoryGroupInput.name()));
        log.info("Category created: {}", categoryGroupInput);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<CategoryGroupResponse>> getCategories() {
        List<CategoryGroup> categoryGroupList = categoryRetriever.getAllCategoryGroups();
        List<CategoryGroupResponse> categoryGroupResponses = categoryGroupList.stream().map(
                category -> new CategoryGroupResponse(
                        category.getId(),
                        category.getName()
                )
        ).toList();
        log.info("Category retrieved: {}", categoryGroupResponses.size());
        return ResponseEntity.ok().body(categoryGroupResponses);
    }
}
