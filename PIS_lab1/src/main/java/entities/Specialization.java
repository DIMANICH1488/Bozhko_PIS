package entities;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Specialization {
    private Integer id;
    private String name;
    private Integer maxStudentNumber;
}
