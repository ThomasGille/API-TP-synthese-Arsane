/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

/**
 *
 * @author REZIGA
 */
public class PartAchete {
    private Achete achete;
    
    private float montantPercu;

    public PartAchete() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public PartAchete(Achete achete) {
        this.achete = achete;
    }

    public Achete getAchete() {
        return achete;
    }

    public void setAchete(Achete achete) {
        this.achete = achete;
    }

    public float getMontantPercu() {
        return montantPercu;
    }

    public void setMontantPercu(float montantPercu) {
        this.montantPercu = montantPercu;
    }

    public PartAchete(Achete achete, float montantPercu) {
        this.achete = achete;
        this.montantPercu = montantPercu;
    }
}
