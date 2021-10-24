package br.com.personmanager.personapi.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonDTO {
    private Long id;

    @NotEmpty(message = "cant be empty")
    @Size(min = 2, max = 100)
    private String firstName;

    @NotEmpty(message = "cant be empty")
    @Size(min = 2, max = 100)
    private String lastName;

    @NotEmpty(message = "cant be empty")
    @CPF(message = "Invalid CPF")
    private String cpf;

    private String birthDate;

    @Valid
    @NotEmpty
    private List<PhoneDTO> phones;
}
