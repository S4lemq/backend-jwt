package pl.bykowski.backendjwt.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.bykowski.backendjwt.dtos.UserDto;
import pl.bykowski.backendjwt.entities.UserEntity;
import pl.bykowski.backendjwt.entities.repositories.UserRepo;
import pl.bykowski.backendjwt.mappers.UserMapper;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserDetailsService {

    private final UserRepo userRepo;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepo userRepo, UserMapper userMapper) {
        this.userRepo = userRepo;
        this.userMapper = userMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<UserEntity> byEmail = userRepo.findByEmail(email);
        if(byEmail.isPresent()){
            return byEmail.get();
        } else {
            throw new UsernameNotFoundException(email);
        }
    }

    public void saveUser(UserDto user){
        UserEntity userEntity = userMapper.fromDtoToEntity(user);
        userRepo.save(userEntity);
    }
}
