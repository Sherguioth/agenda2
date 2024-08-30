package co.edu.unibague.agenda2.schedule.application;

import co.edu.unibague.agenda2.category.domain.Category;
import co.edu.unibague.agenda2.schedule.domain.Schedule;
import co.edu.unibague.agenda2.schedule.domain.ScheduleRepository;
import co.edu.unibague.agenda2.schedule.domain.valueobjects.ScheduleCategory;
import co.edu.unibague.agenda2.user.domain.User;
import co.edu.unibague.agenda2.user.domain.valueojects.UserCategory;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ScheduleRetrieverTest {

    @Test
    void get_schedules_by_user_test() {
        var user = User.create("37dacb55-7e6c-4486-ba43-5ce496288983",
                "test@gmial.com",
                "123",
                "User",
                "Test",
                "2000-01-01");

        var javaCategory = Category.createCategory("7e70b595-6d4d-4c51-9050-33f5347d38d0", "Java", null);
        var springCategory = Category.createCategory("eb766bf3-b9c6-4157-8825-df2739dd26ce", "Spring", null);
        var sqlCategory = Category.createCategory("b6088781-8a8e-4ee0-a52e-59567421eaaa", "SQL", null);
        var javaScriptCategory = Category.createCategory("e8b56a82-b7e2-4217-adef-c2801033b46d", "JavaScript", null);
        var pythonCategory = Category.createCategory("385acde6-db02-45b6-8782-727013e165b4", "Python", null);
        var englishCategory = Category.createCategory("baddd1e6-cd90-4004-84f1-332a8b0532e1", "English", null);

        var userCategories = new HashSet<UserCategory>();
        userCategories.add(new UserCategory(javaCategory, false));
        userCategories.add(new UserCategory(sqlCategory, false));
        userCategories.add(new UserCategory(englishCategory, false));
        user.addCategories(userCategories);

        var scheduleJavaBasic = Schedule.create("64a2d352-39f2-4b42-a49b-07c5224fcae1", "Java Basic", null);
        scheduleJavaBasic.addCategories(Set.of(new ScheduleCategory(javaCategory, false)));

        var scheduleJavaIntermediate = Schedule.create("c5493134-4b73-47e4-85ed-accf6241691f", "Java Intermediate", null);
        scheduleJavaIntermediate.addCategories(Set.of(new ScheduleCategory(javaCategory, true), new ScheduleCategory(sqlCategory, false)));


        var scheduleJavaAdvanced = Schedule.create("60d3b3a4-68a1-458b-b47c-9038cb7866d1", "Java Advanced", null);
        scheduleJavaAdvanced.addCategories(Set.of(new ScheduleCategory(javaCategory, true),
                new ScheduleCategory(sqlCategory, true), new ScheduleCategory(springCategory, false)));

        var scheduleJavaScriptIntermediate = Schedule.create("2973b5a4-e097-403a-b836-d88aefaeaf2d", "JavaScript Intermediate", null);
        scheduleJavaScriptIntermediate.addCategories(Set.of(new ScheduleCategory(javaScriptCategory, true)));

        var scheduleAlgorithms = Schedule.create("09e1ddeb-cd25-4670-bd20-4c321969e443", "Algorithms", null);
        scheduleAlgorithms.addCategories(Set.of(
                new ScheduleCategory(javaCategory, true),
                new ScheduleCategory(javaScriptCategory, false),
                new ScheduleCategory(pythonCategory, false),
                new ScheduleCategory(englishCategory, true)
        ));

        var schedules = List.of(scheduleJavaBasic, scheduleJavaIntermediate, scheduleJavaAdvanced, scheduleAlgorithms,
                scheduleJavaScriptIntermediate);

        var scheduleRepository = mock(ScheduleRepository.class);
        when(scheduleRepository.findAll()).thenReturn(schedules);

        var scheduleRetriever = new ScheduleRetriever(scheduleRepository);
        var result = scheduleRetriever.getSchedulesByUserCategories(user);

        assertThat(result).isNotNull().isNotEmpty();
        assertThat(result).containsExactlyInAnyOrder(scheduleJavaBasic, scheduleJavaIntermediate, scheduleJavaAdvanced, scheduleAlgorithms);
    }
}