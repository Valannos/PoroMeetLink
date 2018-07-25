package fr.imie.poromeetlink.service.dto;

import javax.validation.constraints.NotNull;

public class ContacfIdDto {

    @NotNull
    private Long idCandidat;

    private Long idEmploye;

    public Long getIdCandidat() {
        return idCandidat;
    }

    public void setIdCandidat(Long idCandidat) {
        this.idCandidat = idCandidat;
    }

    public Long getIdEmploye() {
        return idEmploye;
    }

    public void setIdEmploye(Long idEmploye) {
        this.idEmploye = idEmploye;
    }
}
