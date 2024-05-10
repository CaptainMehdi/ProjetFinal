class HoraireGrid {
  constructor({ from, to, activitePiscineId, bassin, longueur, day, nom }) {
    this.from = from;
    this.to = to;
    this.activitePiscineId = activitePiscineId;
    this.nom = nom;
    this.bassin = bassin;
    this.longueur = longueur;
    this.day = day;
  }
}

export default HoraireGrid;
