package br.com.mcoder.cli.starter.controller;

import br.com.mcoder.cli.starter.dto.ClientDTO;
import br.com.mcoder.cli.starter.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/clients")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @GetMapping(value = "/{id}") //Configurando o padrão da rota
    public ResponseEntity<ClientDTO> findById(@PathVariable Long id){
        ClientDTO clientDTO = clientService.findById(id);
        return ResponseEntity.ok(clientDTO);
    }

    @GetMapping
    public ResponseEntity<Page<ClientDTO>> findAll(Pageable pageable){
       Page<ClientDTO> clientDTOPage = clientService.findAll(pageable);
        return ResponseEntity.ok(clientDTOPage);
    }
}
