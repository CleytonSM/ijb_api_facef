package br.com.unifacef.ijb.helpers;

import br.com.unifacef.ijb.mappers.UserMapper;
import br.com.unifacef.ijb.models.dtos.UserCreateDTO;
import br.com.unifacef.ijb.models.dtos.VolunteerRegisterDTO;

public class UserHelper {

    public static UserCreateDTO setUpUserCreateDTO(VolunteerRegisterDTO volunteerRegister) {
        return UserMapper.convertVolunteerRegisterDTOIntoUserCreateDTO(volunteerRegister);
    }
}
