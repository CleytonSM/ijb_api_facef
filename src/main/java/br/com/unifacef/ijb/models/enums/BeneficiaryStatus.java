package br.com.unifacef.ijb.models.enums;

import lombok.Getter;

@Getter
public enum BeneficiaryStatus {
    ACTIVE("ativo"),
    INACTIVE("inativo"),
    NEED_ATENTION("precisa_de_atencao"),;


    private final String status;

    BeneficiaryStatus(String status) {
        this.status = status;
    }

}
