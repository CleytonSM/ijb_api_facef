package br.com.unifacef.ijb.services;


import br.com.unifacef.ijb.helpers.UserInfoHelper;
import br.com.unifacef.ijb.mappers.*;
import br.com.unifacef.ijb.models.dtos.*;
import br.com.unifacef.ijb.models.entities.UserInfo;
import br.com.unifacef.ijb.models.enums.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.unifacef.ijb.helpers.OptionalHelper;
import br.com.unifacef.ijb.models.entities.Beneficiary;
import br.com.unifacef.ijb.models.enums.BeneficiaryStatus;
import br.com.unifacef.ijb.repositories.BeneficiaryRepository;
import jakarta.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class BeneficiaryService {
    @Autowired
    private BeneficiaryRepository repository;
    @Autowired
    private FamiliarService familiarService;
    @Autowired
    private UserService userService;
    @Autowired
    private AuthorityService authorityService;
    @Autowired
    private UserInfoService userInfoService;

    public Beneficiary save(Beneficiary beneficiary){
        return repository.save(beneficiary);
    }

    public Beneficiary getById(Integer id){
        return OptionalHelper.getOptionalEntity(repository.findById(id));
    }

    // ----------------------------------------------------------------

    public Beneficiary registerBeneficiary(BeneficiaryRegisterDTO beneficiaryRegister) {
        AuthorityDTO authorityDTO = authorityService.findAuthorityRole(Role.ROLE_BENEFICIARIO);
        UserInfoCreateDTO userInfoCreateDTO = UserInfoHelper.setUpUserInfoCreateDTO(authorityDTO, beneficiaryRegister);
        UserInfo userInfo = userInfoService.createUserInfo(userInfoCreateDTO);

        Beneficiary beneficiary = new Beneficiary();
        beneficiary.setUser(userInfo.getUser());
        beneficiary.setName(beneficiaryRegister.getName() + " " + beneficiaryRegister.getLastName());
        beneficiary.setCreatedAt(LocalDateTime.now());
        beneficiary.setUpdatedAt(LocalDateTime.now());
        beneficiary.setIndicationDate(LocalDateTime.now());
        beneficiary.setMeetDescription("Whatsapp");


        return save(beneficiary);
    }


    public List<BeneficiaryDTO> getAllBeneficiaries(){
        return BeneficiaryMapper.convertListBeneficiaryIntoListBeneficiaryDTO(repository.findAll());
    }


    public void updateRetrievedBenefEntity(BeneficiaryDTO beneficiaryDTO, Beneficiary beneficiary){
        BeneficiaryMapper.updateBeneficiary(beneficiaryDTO, beneficiary);
    }
    
    public BeneficiaryDTO updateBeneficiary(BeneficiaryDTO beneficiaryDTO){
        Beneficiary beneficiary = getById(beneficiaryDTO.getId());
        updateRetrievedBenefEntity(beneficiaryDTO, beneficiary);
        
        return BeneficiaryMapper.convertBeneficiaryIntoBeneficiaryDTO(save(beneficiary));
    }

    private Beneficiary changeBeneficiaryStatus(BeneficiaryStatus status, Beneficiary beneficiary){
        beneficiary.setStatus(status);

        return beneficiary;
    }

    public BenficiaryPlusFamiliarsDTO sendAllBeneficiaryWithAllStatus(Integer id){
        List<FamiliarDTO> familiars = new ArrayList<FamiliarDTO>();
        Beneficiary beneficiary = getById(id);
        BeneficiaryDTO beneficiaryDTO = BeneficiaryMapper.convertBeneficiaryIntoBeneficiaryDTO(beneficiary);
        BenficiaryPlusFamiliarsDTO benficiaryPlusFamiliarsDTO = new BenficiaryPlusFamiliarsDTO();


        familiars = familiarService.getAllFamiliarsByBeneficiaryID(beneficiary.getId());
        benficiaryPlusFamiliarsDTO = BeneficiaryPlusFamiliarsMapper.createBenefPlusFamil(beneficiaryDTO, familiars);

        return benficiaryPlusFamiliarsDTO;
    }




    @Transactional
    public void deleteBeneficiary(Integer id){
        Beneficiary beneficiary = getById(id);
        save(changeBeneficiaryStatus(BeneficiaryStatus.INACTIVE, beneficiary));
    }

}
