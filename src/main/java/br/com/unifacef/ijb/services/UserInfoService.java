package br.com.unifacef.ijb.services;

import br.com.unifacef.ijb.helpers.OptionalHelper;
import br.com.unifacef.ijb.mappers.UserInfoMapper;
import br.com.unifacef.ijb.models.dtos.UserInfoCreateDTO;
import br.com.unifacef.ijb.models.dtos.UserInfoDTO;
import br.com.unifacef.ijb.models.entities.UserInfo;
import br.com.unifacef.ijb.models.idClasses.UserInfoId;
import br.com.unifacef.ijb.repositories.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInfoService {
    @Autowired
    private UserInfoRepository repository;
    @Autowired
    private UserService userService;

    public UserInfo save(UserInfo userInfo) {
        return repository.save(userInfo);
    }

    public UserInfoDTO getUserInfoDTOByEmail(String email) {
        UserInfo userInfo = getUserInfoByEmail(email);

        return UserInfoMapper.convertUserInfoIntoUserInfoDTO(userInfo);
    }

    public UserInfo getUserInfoByEmail(String email) {
        return OptionalHelper.getOptionalEntity(repository.findByUserEmail(email));
    }

    public UserInfoDTO createUserInfo(UserInfoCreateDTO userInfoCreate) {
//        OptionalHelper.verifyEntityAlreadyExists(repository.findByUserEmail(userInfoCreate.getUser().getEmail()));

        UserInfo userInfo = UserInfoMapper.convertUserInfoCreateDTOIntoUserInfo(userInfoCreate);
        userService.save(userInfo.getUser());
        userInfo.setId(new UserInfoId(userInfo.getUser().getId()));
        return UserInfoMapper.convertUserInfoIntoUserInfoDTO(save(userInfo));
    }
}
