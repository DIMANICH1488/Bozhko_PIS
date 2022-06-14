package entities.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExamResultDTO {
    private ExamResultKey id;
    private Integer grade;
}
