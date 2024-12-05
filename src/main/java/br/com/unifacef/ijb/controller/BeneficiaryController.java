package br.com.unifacef.ijb.controller;

import java.util.List;
import java.util.Optional;

import br.com.unifacef.ijb.mappers.BeneficiaryMapper;
import br.com.unifacef.ijb.models.dtos.BenficiaryPlusFamiliarsDTO;
import br.com.unifacef.ijb.models.dtos.UserPlusUserInfoDTO;
import br.com.unifacef.ijb.models.entities.Beneficiary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.unifacef.ijb.models.dtos.BeneficiaryDTO;
import br.com.unifacef.ijb.services.BeneficiaryService;

@RestController
@RequestMapping("/beneficiario")
public class BeneficiaryController {

    @Autowired
    BeneficiaryService service;

    @PostMapping
    public ResponseEntity<BeneficiaryDTO> createBeneficiaryC(@RequestBody BeneficiaryDTO beneficiaryDTO){
        return new ResponseEntity<>(service.createBeneficiary(beneficiaryDTO), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BeneficiaryDTO> getBeneficiaryById(@PathVariable Integer id){
        return new ResponseEntity<>(BeneficiaryMapper.convertBeneficiaryIntoBeneficiaryDTO(service.getById(id)), HttpStatus.OK);
    }

    @GetMapping("/beneficiario?/getAllData/getUserInfos/{id}")
    public ResponseEntity<UserPlusUserInfoDTO> getUserPlusUserInfoData(@PathVariable Integer id){
        return new ResponseEntity<>(service.sendUserAndUserInfoByBeneficiaryId(id), HttpStatus.OK);
    }

    @GetMapping("/beneficiarios?")
    public ResponseEntity<List<BeneficiaryDTO>> getAllBeneficiaries(){
        return new ResponseEntity<>(service.getAllBeneficiaries(), HttpStatus.OK);
    }

    @GetMapping("/beneficiarios?/getAllData/{id}")
    public ResponseEntity<BenficiaryPlusFamiliarsDTO> getAllBeneficiariesDatas(@PathVariable Integer id){
        return new ResponseEntity<>(service.sendAllBeneficiaryWithAllStatus(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBeneficiary(@PathVariable Integer id){
        service.deleteBeneficiary(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<BeneficiaryDTO> updateBeneficiary(BeneficiaryDTO beneficiaryDTO){
        return new ResponseEntity<>(service.updateBeneficiary(beneficiaryDTO), HttpStatus.OK);
    }


}
