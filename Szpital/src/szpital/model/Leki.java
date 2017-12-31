package szpital.model;

import java.sql.Date;

import javafx.beans.property.*;
import javafx.scene.chart.PieChart.Data;

public class Leki {
	private IntegerProperty id;
	private StringProperty nazwa;
	private Date odTermin;
        private StringProperty sOdTermin;
	private Date doTermin;
        private StringProperty sDoTermin;
	private StringProperty dawkowanie;
	
	public Leki(Integer id, String nazwa, Date odTermin, Date doTermin, String dawkowanie) {
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
	
	
}
