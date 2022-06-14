package entities;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExamResult {
    private Exam exam;
    private Student student;
    private Integer grade;
}
