/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package matricerara;

/**
 * @version 1.0
 * 
 *
 * @author Echipa
 */
public class Coordonate {

    int linie;
    int coloana;

    Coordonate(int xcoordonate, int ycoordonate) {
        linie = xcoordonate;
        coloana = ycoordonate;
    }

    Coordonate() {
    }

    /**
     * @return the x
     */
    public int getLinie() {
        return linie;
    }

    /**
     * @param x the x to set
     */
    public void setLinie(int x) {
        this.linie = x;
    }

    /**
     * @return the y
     */
    public int getColoana() {
        return coloana;
    }

    /**
     * @param y the y to set
     */
    public void setColoana(int y) {
        this.coloana = y;
    }
    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + coloana;
        result = prime * result + linie;
        return result;
    }
    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Coordonate)) {
            return false;
        }
        Coordonate other = (Coordonate) obj;
        if (coloana != other.coloana) {
            return false;
        }
        if (linie != other.linie) {
            return false;
        }
        return true;
    }
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */

    @Override
    public String toString() {
        return "Coordonate [linie=" + linie + ", coloana=" + coloana + "]";
    }
}
