package br.com.unifacef.ijb.models.dtos;

import br.com.unifacef.ijb.models.dtos.VolunteerDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VoluntaryVisitDTO {
    private Integer id;
    private VolunteerDTO volunteerDTO;
}
