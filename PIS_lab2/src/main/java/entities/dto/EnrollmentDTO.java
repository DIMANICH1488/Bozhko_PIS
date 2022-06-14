package entities.dto;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class EnrollmentDTO {
    private EnrollmentKey id;
    private Integer priority;
}
