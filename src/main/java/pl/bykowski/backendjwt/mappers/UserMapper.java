package pl.bykowski.backendjwt.mappers;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;
import pl.bykowski.backendjwt.dtos.UserDto;
import pl.bykowski.backendjwt.entities.UserEntity;

@Component
public class UserMapper {
    public UserEntity fromDtoToEntity(UserDto userDto){
        var entity = new UserEntity();
        entity.setEmail(userDto.getEmail());
        entity.setPassword(encodedPassword(userDto.getPassword()));
        entity.setRole(userDto.getRole());
        entity.setAccountVerified(userDto.isAccountVerified());
        return entity;
    }

    public UserDto fromEntityToDto(UserEntity userEntity){
        var dto = new UserDto();
        dto.setEmail(userEntity.getEmail());
        dto.setPassword(userEntity.getPassword());
        dto.setRole(userEntity.getRole());
        dto.setAccountVerified(userEntity.isAccountVerified());
        return dto;
    }

    private String encodedPassword(String password){
        var salt = BCrypt.gensalt();
        return BCrypt.hashpw(password, salt);
    }
}
