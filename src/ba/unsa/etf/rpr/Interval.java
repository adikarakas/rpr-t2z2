package ba.unsa.etf.rpr;

public class Interval {

    private double pocetak, kraj;
    private boolean pocetna, krajnja;


    public Interval() {
        this.pocetak=0;
        this.kraj=0;
        this.pocetna=false;
        this.krajnja=false;
    }
    public Interval (double pocetak, double kraj, boolean pocetna, boolean krajnja) {
        if (pocetak>kraj) throw new IllegalArgumentException("Pocetna tacka veca od krajnje!");
        setPocetak(pocetak);
        setKraj(kraj);
        setPocetna(pocetna);
        setKrajnja(krajnja);
    }

    public double getPocetak() {
        return pocetak;
    }

    public void setPocetak(double pocetak) {
        this.pocetak = pocetak;
    }

    public double getKraj() {
        return kraj;
    }

    public void setKraj(double kraj) {
        this.kraj = kraj;
    }

    public boolean isPocetna() {
        return pocetna;
    }

    public void setPocetna(boolean pocetna) {
        this.pocetna = pocetna;
    }

    public boolean isKrajnja() {
        return krajnja;
    }

    public void setKrajnja(boolean krajnja) {
        this.krajnja = krajnja;
    }

    public boolean isIn(double v) {
        return ((v > pocetak || (v == pocetak && pocetna)) && (v < kraj || (v == kraj && krajnja)));
    }

    public boolean isNull() {
        return pocetak == 0 && kraj == 0 && !pocetna && !krajnja;
    }

    public Interval intersect (Interval i) {
        Interval presjek = new Interval();
        if (i.getPocetak()>this.getPocetak()) {
            presjek.pocetak=i.pocetak;
            presjek.pocetna=i.pocetna;
        }
        else if (i.pocetak<this.pocetak) {
            presjek.pocetak=this.pocetak;
            presjek.pocetna=this.pocetna;
        }
        else {
            if (i.isPocetna() && this.isPocetna()) presjek.pocetna=true;
            else presjek.pocetna=false;
        }
        if (i.getKraj()<this.getKraj()) {
            presjek.kraj=i.kraj;
            presjek.krajnja=i.krajnja;
        }
        else if (i.kraj>this.kraj) {
            presjek.kraj=this.kraj;
            presjek.krajnja=this.krajnja;
        }
        else {
            if (i.isKrajnja() && this.isKrajnja()) presjek.krajnja=true;
            else presjek.krajnja=false;
        }
        return presjek;
    }

    public static Interval intersect (Interval i1, Interval i2) {
        Interval presjek = new Interval();
        if (i1.pocetak>i2.pocetak) {
            presjek.pocetak=i1.pocetak;
            presjek.pocetna=i1.pocetna;
        }
        else if (i1.pocetak<i2.pocetak) {
            presjek.pocetak=i2.pocetak;
            presjek.pocetna=i2.pocetna;
        }
        else {
            if (i1.pocetna && i2.pocetna) presjek.pocetna=true;
            else presjek.pocetna=false;
        }
        if (i1.kraj<i2.kraj) {
            presjek.kraj=i1.kraj;
            presjek.krajnja=i1.krajnja;
        }
        else if (i1.kraj>i2.kraj) {
            presjek.kraj=i2.kraj;
            presjek.krajnja=i2.krajnja;
        }
        else {
            if (i1.krajnja && i2.krajnja) presjek.krajnja=true;
            else presjek.krajnja=false;
        }
        return presjek;
    }

    public String toString() {
        if (this.isNull()) return "()";
        String povrat="";
        if (pocetna) povrat=povrat+"[";
        else povrat=povrat+"(";
        povrat=povrat+pocetak+","+kraj;
        if (krajnja) povrat=povrat+"]";
        else povrat=povrat+")";
        return povrat;
    }

    public boolean equals(Interval i) {
        if (this.toString().equals(i.toString())) return true;
        return false;
    }

}
