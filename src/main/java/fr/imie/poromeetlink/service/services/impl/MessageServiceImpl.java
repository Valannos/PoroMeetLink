package fr.imie.poromeetlink.service.services.impl;

import fr.imie.poromeetlink.domain.repositories.MessageRepository;
import fr.imie.poromeetlink.outils.exceptions.EntryNotFound;
import fr.imie.poromeetlink.outils.exceptions.InvalidFieldException;
import fr.imie.poromeetlink.outils.exceptions.InvalidRoleException;
import fr.imie.poromeetlink.service.dto.MessageDto;
import fr.imie.poromeetlink.service.mappers.MessageMapper;
import fr.imie.poromeetlink.service.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl extends AbstractService<MessageRepository> implements MessageService {

    @Autowired
    MessageMapper mapper;

    @Override
    public List<MessageDto> getAll() {


        return mapper.messageToDto(repository.findAll());
    }

    @Override
    public MessageDto getOne(Long id) throws EntryNotFound {
        return null;
    }

    @Override
    public MessageDto saveOne(MessageDto dto) throws InvalidRoleException, InvalidFieldException, NoSuchFieldException {
        return null;
    }

    @Override
    public MessageDto updateOne(MessageDto dto) throws EntryNotFound, InvalidFieldException, NoSuchFieldException {
        return null;
    }

    @Override
    public Boolean delete(Long id) throws EntryNotFound, InvalidFieldException, InvalidRoleException {
        return null;
    }

    @Override
    public void validator(MessageDto dto) throws NoSuchFieldException {


    }
}
