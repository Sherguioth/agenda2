package co.edu.unibague.agenda2.category.infrastructure.api;

import co.edu.unibague.agenda2.category.domain.SubCategory;
import co.edu.unibague.agenda2.category.domain.usecases.CreateSubCategory;
import co.edu.unibague.agenda2.category.domain.usecases.RetrieveCategory;
import co.edu.unibague.agenda2.category.domain.usecases.RetrieveSubCategory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/subcategories")
public class SubCategoryController {

    private final CreateSubCategory subCategoryCreator;
    private final RetrieveSubCategory subCategoryRetriever;
    private final RetrieveCategory categoryRetriever;

    public SubCategoryController(CreateSubCategory subCategoryCreator, RetrieveSubCategory subCategoryRetriever, RetrieveCategory categoryRetriever) {
        this.subCategoryCreator = subCategoryCreator;
        this.subCategoryRetriever = subCategoryRetriever;
        this.categoryRetriever = categoryRetriever;
    }

    @PostMapping
    public ResponseEntity<Void> createSubCategory(@RequestBody SubCategoryInput subCategoryInput) {
        var category = categoryRetriever.getCategory(subCategoryInput.categoryId()).orElseThrow();
        subCategoryCreator.createSubCategory(SubCategory.createSubCategory(subCategoryInput.id(), subCategoryInput.name(), category));
        log.info("Sub category created: {}", subCategoryInput);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<SubCategoryResponse>> getSubCategories() {
        List<SubCategory> subCategoryList = subCategoryRetriever.getAllSubCategories();
        List<SubCategoryResponse> subCategoryResponses = subCategoryList.stream().map(
                subCategory -> new SubCategoryResponse(
                        subCategory.getId(),
                        subCategory.getName(),
                        subCategory.getCategory().getId(),
                        subCategory.getCategory().getName()
                )
        ).toList();
        log.info("Sub categories retrieved: {}", subCategoryResponses.size());
        return ResponseEntity.ok().body(subCategoryResponses);
    }
}
