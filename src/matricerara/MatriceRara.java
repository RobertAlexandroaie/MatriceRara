/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package matricerara;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @version 1.0
 *
 * @author Echipa
 */
public class MatriceRara {

    HashMap<Coordonate, Double> mapa;
    int linii;
    int coloane;

    /**
     * Metoda genereaza o matrice rara pe baza valorilor parametrilor.
     * 
     * @param linii numarul de linii a noii matrici.
     * @param coloane numarul de coloane a noii matrici.
     * @param nrValori numarul de elemente nenule ale noii matrici.
     * @param inf limita inferioara a intervalului din care iau valori elementele.
     * @param sup limita superioara a intervalului din care iau valori elementele.
     * @return un nou obiect de tip MatriceRara.
     */
    public static MatriceRara generateSparseMatrix(int linii, int coloane, int nrValori, double inf, double sup) {
        MatriceRara result = new MatriceRara();
        double sizeInterval = sup - inf;
        double value;
        int i = 0;
        int generatedLine;
        int generatedColumn;
        result.setLinii(linii);
        result.setColoane(coloane);
        if (linii * coloane < nrValori * 4) {
            throw new RuntimeException("Matricea data nu este rara!");
        }
        while (i < nrValori) {
            value = ((Math.random() * sizeInterval) + inf);
            generatedLine = (int) (Math.random() * linii);
            generatedColumn = (int) (Math.random() * coloane);
            if (result.getValue(generatedLine, generatedColumn) == 0) {
                result.setValue(generatedLine, generatedColumn, value);
                i++;
            }
        }
        return result;
    }

    /**
     *Constructor fara argumente. Initializeaza argumentele linii si coloane cu 0.
     * 
     */
    MatriceRara() {
        mapa = new HashMap<Coordonate, Double>();
        linii = 0;
        coloane = 0;
    }

    /**
     * Constructor cu un argument (tablou unidimensional). Initializeaza obiectul curent cu valorile nenule din tabloul dat
     * ca parametru.
     * 
     * @param secondMatrix
     */
    MatriceRara(double[] secondMatrix) {
        int i;
        mapa = new HashMap<Coordonate, Double>();
        coloane = secondMatrix.length;
        linii = 1;
        for (i = 0; i < coloane; i++) {
            if (secondMatrix[i] != 0) {
                mapa.put(new Coordonate(0, i), secondMatrix[i]);
            }
        }
    }

    /**
     * Constructor cu un parametru (tablou unidemensional). Initializeaza obiectul curent cu valorile nenule din tabloul
     * dat ca argument cat si linii si coloanele obiectului curent.
     * 
     * @param secondMatrix
     */
    MatriceRara(double[][] secondMatrix) {
        int i;
        int j;
        coloane = secondMatrix.length;
        linii = secondMatrix[0].length;
        mapa = new HashMap<Coordonate, Double>();
        for (i = 0; i < linii; i++) {
            for (j = 0; j < coloane; j++) {
                if (secondMatrix[i][j] != 0) {
                    mapa.put(new Coordonate(i, j), secondMatrix[i][j]);
                }
            }
        }
    }

    /**
     * Metoda returneaza atributul mapa.
     * 
     * @return mapa
     */
    public HashMap<Coordonate, Double> getMapa() {
        return mapa;
    }

    /**
     * Metoda seteaza atributul mapa cu valoarea parametrului
     * 
     * @param mapa valoarea cu care se seteaza atributul mapa
     */
    public void setMapa(HashMap<Coordonate, Double> mapa) {
        this.mapa = mapa;
    }

    /**
     * Metoda returneaza atributul linii.
     * 
     * @return linii
     */
    public int getLinii() {
        return linii;
    }

    /**
     * Metoda seteaza atributul linii.
     * 
     * @param linii valoarea cu care se initializeaza atributul linii.
     */
    public void setLinii(int linii) {
        this.linii = linii;
    }

    /**
     * Metoda returneaza atributul coloane.
     * 
     * @return coloane
     */
    public int getColoane() {
        return coloane;
    }
    
    
    /**
     * Metoda afiseaza la ecran in format tablou bidimensional matricea.
     */
    public void afisare() {
        int i;
        int j;
        for (i = 0; i < linii; i++) {
            for (j = 0; j < coloane; j++) {
                System.out.print(this.getValue(i, j) + "   ");
            }
            System.out.println();
        }
    }

    /**
     * Metoda seteaza atributul coloane.
     * 
     * @param coloane valoarea cu care se initializeaza atributul coloane.
     */
    public void setColoane(int coloane) {
        this.coloane = coloane;
    }

    /**
     * Metoda adauga un nou element in HashMap
     * 
     * @param linie linia la care se gaseste valoarea.
     * @param coloana coloana la care se gaseste valoarea. 
     * @param valoare valoarea elementului nenul.
     */
    public void setValue(int linie, int coloana, double valoare) {
        mapa.put(new Coordonate(linie, coloana), valoare);
    }

    /**
     * @param linie
     * @param coloana
     * @return
     */
    public double getValue(int linie, int coloana) {
        if (linie < 0 || linie >= linii) {
            throw new RuntimeException("Numar de linie invalid!");
        }
        if (coloana < 0 || coloana >= coloane) {
            throw new RuntimeException("Numar de coloana invalid!");
        }
        Coordonate temp = new Coordonate(linie, coloana);
        if (mapa.containsKey(temp)) {
            return mapa.get(temp);
        } else {
            return 0;
        }
    }

    /**
     * @param matrice
     */
    public void addMatrix(MatriceRara matrice) {
        Iterator it = matrice.getMapa().entrySet().iterator();
        if (linii != matrice.getLinii() || coloane != matrice.getColoane()) {
            throw new RuntimeException("Matrici incompatibile!");
        }
        while (it.hasNext()) {
            Map.Entry<Coordonate, Double> value = (Map.Entry) it.next();
            if (mapa.containsKey(value.getKey())) {
                mapa.put(value.getKey(), value.getValue() + mapa.get(value.getKey()));
            } else {
                mapa.put(value.getKey(), value.getValue());
            }
        }
    }

    /**
     * @return
     */
    public MatriceRara transpose() {
        MatriceRara result = new MatriceRara();
        Iterator it = mapa.entrySet().iterator();
        result.setColoane(linii);
        result.setLinii(coloane);
        while (it.hasNext()) {
            Map.Entry<Coordonate, Double> value = (Map.Entry) it.next();
            result.setValue(value.getKey().getColoana(), value.getKey().getLinie(), value.getValue());
        }
        return result;
    }

    /**
     * @param matrice
     */
    public void mulMatrix(MatriceRara matrice) {
        if (coloane != matrice.getLinii()) {
            throw new RuntimeException("Matricele nu pot fi inmultite!");
        }
        MatriceRara newMatrix = new MatriceRara();
        newMatrix.setColoane(matrice.getColoane());
        newMatrix.setLinii(linii);
        Iterator itA = mapa.entrySet().iterator();
        while (itA.hasNext()) {
            Iterator itB = matrice.getMapa().entrySet().iterator();
            Map.Entry<Coordonate, Double> valueA = (Map.Entry) itA.next();
            while (itB.hasNext()) {
                Map.Entry<Coordonate, Double> valueB = (Map.Entry) itB.next();
                if (valueB.getKey().getLinie() == valueA.getKey().getColoana()) {
                    double positionValue = newMatrix.getValue(valueA.getKey().getLinie(), valueB.getKey().getColoana());
                    if (positionValue == 0) {
                        newMatrix.setValue(valueA.getKey().getLinie(), valueB.getKey().getColoana(), valueA.getValue() * valueB.getValue());
                    } else {
                        newMatrix.setValue(valueA.getKey().getLinie(), valueB.getKey().getColoana(), positionValue + (valueA.getValue() * valueB.getValue()));
                    }
                }
            }
        }
        mapa = newMatrix.getMapa();
        coloane = matrice.getColoane();
    }

    /**
     * @param constant
     */
    public void mulMatrix(double constant) {
        Iterator it = mapa.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<Coordonate, Double> value = (Map.Entry) it.next();
            mapa.put(value.getKey(), value.getValue() * constant);
        }
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + coloane;
        result = prime * result + linii;
        result = prime * result + ((mapa == null) ? 0 : mapa.hashCode());
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
        if (!(obj instanceof MatriceRara)) {
            return false;
        }
        MatriceRara other = (MatriceRara) obj;
        if (coloane != other.coloane) {
            return false;
        }
        if (linii != other.linii) {
            return false;
        }
        if (mapa == null) {
            if (other.mapa != null) {
                return false;
            }
        } else if (!mapa.equals(other.mapa)) {
            return false;
        }
        return true;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "MatriceRara [mapa=" + mapa + ", linii=" + linii + ", coloane="
                + coloane + "]";
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        MatriceRara m;
        MatriceRara n;
        double p[][] = new double[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                p[i][j] = i * 2 + j;
            }
        }
        m = new MatriceRara(p);
        n = new MatriceRara(p[2]);
        System.out.println("Afisare m:");
        m.afisare();
        System.out.println("\nAfisare n:");
        n.afisare();
        m.setColoane(3);
        m.setLinii(3);
        n.setColoane(3);
        n.setLinii(3);
        System.out.println("\nInmultire cu matrice:");
        m.mulMatrix(n);
        m.afisare();
        System.out.println("\nInmultire cu scalar:");
        m.mulMatrix(2);
        m.afisare();
        System.out.println("\nAdunare:");
        m.addMatrix(m);
        m.afisare();
        System.out.println("\nTranspusa:");
        n = m.transpose();
        n.afisare();
        System.out.println("\nMatrice generata:");
        MatriceRara.generateSparseMatrix(7, 7, 10, 2.0, 7.0).afisare();
    }
}
