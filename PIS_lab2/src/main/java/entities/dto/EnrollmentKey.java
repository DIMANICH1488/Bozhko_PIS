package entities.dto;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class EnrollmentKey {
    Integer studentId;
    Integer specializationId;
}
