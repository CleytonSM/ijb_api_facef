package br.com.unifacef.ijb.models.idClasses;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class AddressUserId {
    private Integer address;
    private Integer user;
}
