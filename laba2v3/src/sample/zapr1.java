package sample;

public class zapr1 {
    public String naim;
    public String nazvanie;
    public String kolvo;
    public String fio_zaved;
    public String otdel_id;

    public zapr1(String naim, String nazvanie) {
        this.naim = naim;
        this.nazvanie = nazvanie;
    }
    public zapr1(){}

    public String getNaim() {
        return naim;
    }

    public String getNazvanie() {
        return nazvanie;
    }

    public String getKolvo() {
        return kolvo;
    }

    public String getFio_zaved() {
        return fio_zaved;
    }

    public String getOtdel_id() {
        return otdel_id;
    }

    public void setNaim(String naim) {
        this.naim = naim;
    }

    public void setNazvanie(String nazvanie) {
        this.nazvanie = nazvanie;
    }

    public void setKolvo(String kolvo) {
        this.kolvo = kolvo;
    }

    public void setFio_zaved(String fio_zaved) {
        this.fio_zaved = fio_zaved;
    }

    public void setOtdel_id(String otdel_id) {
        this.otdel_id = otdel_id;
    }
}
