package szpital.model;

import java.sql.Date;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.chart.PieChart.Data;

public class Badania {
	
		private IntegerProperty id;
		private StringProperty imiePacjenta;
		private StringProperty nazwiskoPacjenta;
		private StringProperty nazwaBadania;
		private Date dataBadania;
		private StringProperty wynikBadania;
		
		public Badania(int id, String imiePacjenta,String nazwiskoPacjenta, String nazwaBadania, Date dataBadania, String wynikBadania) {
			this.id=new SimpleIntegerProperty(id);
			this.imiePacjenta=new SimpleStringProperty(imiePacjenta);
			this.nazwiskoPacjenta=new SimpleStringProperty(nazwiskoPacjenta);
			this.nazwaBadania=new SimpleStringProperty(nazwaBadania);
			this.dataBadania=dataBadania;
			this.wynikBadania=new SimpleStringProperty(wynikBadania);
		}

		public IntegerProperty getId() {
			return id;
		}

		public void setId(IntegerProperty id) {
			this.id = id;
		}

		public StringProperty getImiePacjenta() {
			return imiePacjenta;
		}

		public void setImiePacjenta(StringProperty imiePacjenta) {
			this.imiePacjenta = imiePacjenta;
		}

		public StringProperty getNazwiskoPacjenta() {
			return nazwiskoPacjenta;
		}

		public void setNazwiskoPacjenta(StringProperty nazwiskoPacjenta) {
			this.nazwiskoPacjenta = nazwiskoPacjenta;
		}

		public StringProperty getNazwaBadania() {
			return nazwaBadania;
		}

		public void setNazwaBadania(StringProperty nazwaBadania) {
			this.nazwaBadania = nazwaBadania;
		}

		public Date getDataBadania() {
			return dataBadania;
		}

		public void setDataBadania(Date dataBadania) {
			this.dataBadania = dataBadania;
		}

		public StringProperty getWynikBadania() {
			return wynikBadania;
		}

		public void setWynikBadania(StringProperty wynikBadania) {
			this.wynikBadania = wynikBadania;
		}
		
		
}
