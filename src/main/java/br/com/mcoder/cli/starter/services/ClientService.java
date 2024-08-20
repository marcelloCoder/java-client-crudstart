package br.com.mcoder.cli.starter.services;

import br.com.mcoder.cli.starter.dto.ClientDTO;
import br.com.mcoder.cli.starter.entities.Client;
import br.com.mcoder.cli.starter.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Transactional(readOnly = true)
    public ClientDTO findById(Long id){
        Optional<Client> result = clientRepository.findById(id);
        Client client = result.get();
        ClientDTO clientDTO = new ClientDTO(client);
        return clientDTO;
    }

    @Transactional(readOnly = true)
    public Page<ClientDTO> findAll(Pageable pageable){
        Page<Client> clientPage = clientRepository.findAll(pageable);
        return clientPage.map(x -> new ClientDTO(x));
    }

}
