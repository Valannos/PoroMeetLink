export class PropositionCandidature {

  id: PropositionCandidatureId;
  idCandidat: number;
  idAnnonce: number;
  idCandidature: number;
  dateCreation: string;
  accroche: string;
  auteurCandidat: boolean;
}

export class PropositionCandidatureId {
  idCandidat: number;
  idAnnonce: number;

  constructor(idCandidat: number, idAnnonce: number) {
    this.idCandidat = idCandidat;
    this.idAnnonce = idAnnonce;
  }
}
