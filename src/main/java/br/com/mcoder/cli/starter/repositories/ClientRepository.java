package br.com.mcoder.cli.starter.repositories;

import br.com.mcoder.cli.starter.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

// Operações do banco de dados
public interface ClientRepository extends JpaRepository<Client, Long> {
}
