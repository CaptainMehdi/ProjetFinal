class HoraireGrid {
  constructor({ name, from, to, activitePiscineId, bassin, longueur, day }) {
    this.name = name;
    this.from = from;
    this.to = to;
    this.activitePiscineId = activitePiscineId;
    this.bassin = bassin;
    this.longueur = longueur;
    this.day = day;
  }
}

export default HoraireGrid;
