package entities;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Builder
@Data
public class Exam {
    private Integer id;
    private String name;
    private Professor professor;
    private Timestamp date;
}
