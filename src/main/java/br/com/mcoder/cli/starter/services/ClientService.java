package br.com.mcoder.cli.starter.services;

import br.com.mcoder.cli.starter.dto.ClientDTO;
import br.com.mcoder.cli.starter.entities.Client;
import br.com.mcoder.cli.starter.repositories.ClientRepository;
import br.com.mcoder.cli.starter.services.exceptions.DatabaseException;
import br.com.mcoder.cli.starter.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
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

    @Transactional
    public ClientDTO insert(ClientDTO clientDTO){
        Client client = new Client();
        copyDtoEntity(clientDTO, client);
        client= clientRepository.save(client);
        return new ClientDTO(client);
    }

    @Transactional
    public ClientDTO update(Long id, ClientDTO clientDTO){
        try {
            Client client = clientRepository.getReferenceById(id);
            copyDtoEntity(clientDTO, client);
            client = clientRepository.save(client);
            return new ClientDTO(client);
        }catch (EntityNotFoundException e){
            throw new ResourceNotFoundException("RECURSO NÃO ENCONTRADO");
        }
    }
    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id){
        if (!clientRepository.existsById(id)){
            throw new ResourceNotFoundException("RECURSO NÃO ENCONTRADO");
        }try {
            clientRepository.deleteById(id);
        }catch (DataIntegrityViolationException e){
            throw new DatabaseException("FALHA DE INTEGRIDADE REFERENCIAL");
        }
    }

    private void copyDtoEntity(ClientDTO clientDTO, Client client) {
        client.setName(clientDTO.getName());
        client.setCpf(clientDTO.getCpf());
        client.setIncome(clientDTO.getIncome());
        client.setBirthDate(clientDTO.getBirthDate());
        client.setChildren(clientDTO.getChildren());
    }

}
