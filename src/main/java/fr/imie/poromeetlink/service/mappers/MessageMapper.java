package fr.imie.poromeetlink.service.mappers;

import fr.imie.poromeetlink.domain.entities.Message;
import fr.imie.poromeetlink.service.dto.MessageDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {UtilisateurMapper.class, CandidatureMapper.class})
public interface MessageMapper {

    Message dtoToMessage(MessageDto dto);

    List<Message> dtoToMessage(List<MessageDto> dto);

    MessageDto messageToDto(Message message);

    List<MessageDto> messageToDto(List<Message> messages);


}
