package br.com.mcoder.cli.starter.dto;

import br.com.mcoder.cli.starter.entities.Client;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;

// design pattern utilizado para transferir dados entre subsistemas que se
// comunicam por meio de serviços ou interfaces.
// não é gerenciado pela JPA
@Getter
public class ClientDTO {

    private Long id;
    @Size(min = 3, max = 80, message = "Nome precisa ter de 3 a 80 caracteres")
    @NotBlank(message = "CAMPO REQUERIDO")
    private String name;
    private String cpf;
    private Double income;
    @PastOrPresent(message = "A data deve estar no passado ou no presente.")
    private LocalDate birthDate;
    private Integer children;

    public ClientDTO(Long id, String name, String cpf, Double income, LocalDate birthDate, Integer children) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.income = income;
        this.birthDate = birthDate;
        this.children = children;
    }

    public ClientDTO(Client client){
        id = client.getId();
        name = client.getName();
        cpf = client.getCpf();
        income = client.getIncome();
        birthDate = client.getBirthDate();
        children = client.getChildren();
    }
}
