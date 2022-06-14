package entities.dto;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.sql.Timestamp;

@Data
@SuperBuilder
public class ExamDTO {
    private Integer id;
    private String name;
    private Integer professorId;
    private Timestamp date;
}