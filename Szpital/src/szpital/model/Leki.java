package szpital.model;

import java.sql.Date;

import javafx.beans.property.*;
import javafx.scene.chart.PieChart.Data;

public class Leki {
	private IntegerProperty id;
        private IntegerProperty idPacjenta;
	private StringProperty nazwa;
        private IntegerProperty idNazwaLeku;
	private Date odTermin;
        private StringProperty sOdTermin;
	private Date doTermin;
        private StringProperty sDoTermin;
	private StringProperty dawkowanie;
	
	public Leki(Integer id, Integer idPacjenta, String nazwa, Integer idNazwaLeku, Date odTermin, Date doTermin, String dawkowanie) {
		this.id=new SimpleIntegerProperty(id);
                this.idPacjenta = new SimpleIntegerProperty(idPacjenta);
		this.nazwa=new SimpleStringProperty(nazwa);
                this.idNazwaLeku = new SimpleIntegerProperty(idNazwaLeku);
		this.odTermin=odTermin;
                this.sOdTermin = new SimpleStringProperty(odTermin.toString());
                
		this.doTermin=doTermin;
                this.sDoTermin = new SimpleStringProperty(doTermin.toString());
		this.dawkowanie= new SimpleStringProperty(dawkowanie);
	}
        
        public Leki(Integer id, Integer idPacjenta, String nazwa,  Date odTermin, Date doTermin, String dawkowanie) {
		this.id=new SimpleIntegerProperty(id);
		this.nazwa=new SimpleStringProperty(nazwa);
		this.odTermin=odTermin;
                this.sOdTermin = new SimpleStringProperty(odTermin.toString());
                
		this.doTermin=doTermin;
                this.sDoTermin = new SimpleStringProperty(doTermin.toString());
		this.dawkowanie= new SimpleStringProperty(dawkowanie);
	}
        

	public IntegerProperty getId() {
		return id;
	}

	public void setId(IntegerProperty id) {
		this.id = id;
	}

	public StringProperty getNazwa() {
		return nazwa;
	}

	public void setNazwa(StringProperty nazwa) {
		this.nazwa = nazwa;
	}

	public StringProperty getOdTermin() {
		return sOdTermin;
	}

	public void setOdTermin(Date odTermin) {
		this.odTermin = odTermin;
	}

	public StringProperty getDoTermin() {
		return sDoTermin;
	}

	public void setDoTermin(Date doTermin) {
		this.doTermin = doTermin;
	}

	public StringProperty getDawkowanie() {
		return dawkowanie;
	}

	public void setDawkowanie(StringProperty dawkowanie) {
		this.dawkowanie = dawkowanie;
	}

    /**
     * @return the idPacjenta
     */
    public IntegerProperty getIdPacjenta() {
        return idPacjenta;
    }

    /**
     * @param idPacjenta the idPacjenta to set
     */
    public void setIdPacjenta(IntegerProperty idPacjenta) {
        this.idPacjenta = idPacjenta;
    }

    /**
     * @return the idNazwaLeku
     */
    public IntegerProperty getIdNazwaLeku() {
        return idNazwaLeku;
    }

    /**
     * @param idNazwaLeku the idNazwaLeku to set
     */
    public void setIdNazwaLeku(IntegerProperty idNazwaLeku) {
        this.idNazwaLeku = idNazwaLeku;
    }
	
	
}
