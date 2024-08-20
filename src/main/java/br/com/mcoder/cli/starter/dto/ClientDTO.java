package br.com.mcoder.cli.starter.dto;

import br.com.mcoder.cli.starter.entities.Client;
import lombok.*;

import java.time.LocalDate;

// design pattern utilizado para transferir dados entre subsistemas que se
// comunicam por meio de serviços ou interfaces.
// não é gerenciado pela JPA
@Getter
public class ClientDTO {

    private Long id;
    private String name;
    private String cpf;
    private Double income;
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
