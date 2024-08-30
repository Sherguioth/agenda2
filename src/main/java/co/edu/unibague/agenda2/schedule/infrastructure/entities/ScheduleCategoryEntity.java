package co.edu.unibague.agenda2.schedule.infrastructure.entities;

import co.edu.unibague.agenda2.category.infrastructure.entities.CategoryEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "\"Schedule_Category\"")
public class ScheduleCategoryEntity {

    @Id
    @Column(name = "schedule_category_id")
    private UUID id;

    @Column(name = "is_mandatory")
    private boolean isMandatory;

    @ManyToOne
    @JoinColumn(name = "category_id", updatable = false)
    private CategoryEntity categoryEntity;

    @ManyToOne
    @JoinColumn(name = "schedule_id", updatable = false)
    private ScheduleEntity scheduleEntity;
}
