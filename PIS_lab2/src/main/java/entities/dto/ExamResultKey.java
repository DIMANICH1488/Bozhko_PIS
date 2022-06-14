package entities.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExamResultKey {
    private Integer studentId;
    private Integer examId;
}
