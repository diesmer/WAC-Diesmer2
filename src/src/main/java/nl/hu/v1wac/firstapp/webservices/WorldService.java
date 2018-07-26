package nl.hu.v1wac.firstapp.webservices;

import java.sql.SQLException;
import java.util.List;

//import domain.Account;
import nl.hu.v1wac.firstapp.persistence.CountryDao;
import nl.hu.v1wac.firstapp.persistence.CountryPostgresDaoImpl;

public class WorldService {
	
	CountryDao countryDAO = new CountryPostgresDaoImpl();
	//private UserDao userDAO = new UserPostgresDaoImpl();

	
	public List<Country> getAllCountries() {
		return countryDAO.findAll();
	}
	
	public List<Country> get10LargestPopulations() {
		return countryDAO.find10LargestPopulations();
	}
	
	public List<Country> find10LargestSurfaces() {
		return countryDAO.find10LargestSurfaces();
	}

	public Country getCountryByCode(String code) {
		Country result = null;
		
		for (Country c : countryDAO.findAll()) {
			if (c.getCode().equals(code)) {
				result = c;
				break;
			}
		}
		
		return result;
	}

	public boolean updateCountry(Country c) throws SQLException {
		boolean resultaat = false;
			if(countryDAO.update(c)) {
				resultaat = countryDAO.update(c);
			} else
				System.out.println("lukt nie");
			
		
		return resultaat;
	}
	
	
	public boolean addCountry(Country c) throws SQLException {
		boolean resultaat = false;
		if(c!=null) {
			resultaat = countryDAO.create(c);
		} else
			throw new IllegalArgumentException("Toevoegen niet gelukt");
		return resultaat;
	}
	
	
	public boolean deleteCountry(String code) {
		boolean resultaat = false;
		Country c = countryDAO.findByCode(code);
		if (c != null) {
			resultaat = countryDAO.delete(c);
		} else {
			throw new IllegalArgumentException("Code bestaat niet!");
		}
		return resultaat;
	}
	

	public Country createCountry(String nm) {
		// TODO Auto-generated method stub
		return null;
	}
}
