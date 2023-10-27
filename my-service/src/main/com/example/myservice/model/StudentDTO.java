package example.myservice.model;

import lombok.Data;

@Data
public class StudentDTO {
    private int id;
    private String firstName;
    private String middleName;
    private String lastName;
}
