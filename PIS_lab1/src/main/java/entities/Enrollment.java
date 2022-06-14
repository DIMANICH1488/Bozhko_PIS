package entities;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Enrollment {
    private Student student;
    private Specialization specialization;
    private Integer priority;
}
