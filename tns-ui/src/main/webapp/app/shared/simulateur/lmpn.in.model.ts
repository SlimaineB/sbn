export class LmpnIn {
    public prixAchat?: any;
    public tauxImpostion?: any;
    public loyerNet?: any;

    constructor(
        prixAchat?: any,
        tauxImpostion?: any,
        loyerNet?: any
    ) {
        this.prixAchat = prixAchat ? prixAchat : null;
        this.tauxImpostion = tauxImpostion ? tauxImpostion : null;
        this.loyerNet = loyerNet ? loyerNet : null;
    }
}
