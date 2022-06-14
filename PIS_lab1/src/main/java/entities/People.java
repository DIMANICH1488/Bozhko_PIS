package entities;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class People {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
