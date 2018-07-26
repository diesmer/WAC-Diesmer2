package nl.hu.v1wac.firstapp.webservices;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.security.RolesAllowed;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import nl.hu.v1wac.firstapp.webservices.Country;
import nl.hu.v1wac.firstapp.webservices.ServiceProvider;
import nl.hu.v1wac.firstapp.webservices.WorldService;

@Path("/countries")
public class WolrdResource {
	private WorldService service = ServiceProvider.getWorldService();

	@GET
	@Produces("application/json")
	public String getCountries() {
		WorldService service = ServiceProvider.getWorldService();
		JsonArray countryArray = buildJsonCountryArray(service.getAllCountries());

		return countryArray.toString();
	}

	@POST
	@RolesAllowed("admi")
	@Path("/new")
	@Produces("application/json")
	public Response addCountry(String data) throws SQLException {
		String[] allParams = data.split(",");
		System.out.print(data);
		Country c = new Country(allParams[0], allParams[1], allParams[2], allParams[3], allParams[4], allParams[5],
				Double.parseDouble(allParams[6]), Integer.parseInt(allParams[7]), allParams[8],
				Double.parseDouble(allParams[9]), Double.parseDouble(allParams[10]));

		boolean succes = service.addCountry(c);
		return Response.status(200).entity(succes).build();
	}

	private JsonArray buildJsonCountryArray(List<Country> countries) {
		JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();

		for (Country c : countries) {

			jsonArrayBuilder.add(buildJsonCountry(c));
		}

		return jsonArrayBuilder.build();
	}

	private JsonObjectBuilder buildJsonCountry(Country c) {
		JsonObjectBuilder job = Json.createObjectBuilder();

		job.add("capital", c.getCapital());
		job.add("code", c.getCode());
		job.add("continent", c.getContinent());
		job.add("government", c.getGovernment());
		job.add("iso3", c.getIso3());
		job.add("latitude", c.getLatitude());
		job.add("longitude", c.getLongitude());
		job.add("name", c.getName());
		job.add("population", c.getPopulation());
		job.add("region", c.getRegion());
		job.add("surface", c.getSurface());
		return job;
	}

	@GET
	@Path("{code}")
	@Produces("Application/json")
	public String getCountryInfo(@PathParam("code") String code) {
		WorldService service = ServiceProvider.getWorldService();
		Country world = service.getCountryByCode(code);
		JsonObjectBuilder job = null;
		if (world != null) {
			job = buildJsonCountry(world);
		}
		return job != null ? job.build().toString()
				: Json.createObjectBuilder().add("error", "not there").build().toString();

	}

	@PUT
	@RolesAllowed("admi")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/update/{data}")
	public Response updateCountry(String data) throws SQLException {
		String[] allParams = data.split(",");
		System.out.print(data);
		
		Country c = service.getCountryByCode(allParams[0]);
		c.setName(allParams[2]);
		c.setCapital(allParams[3]);
		c.setContinent(allParams[4]);
		c.setRegion(allParams[5]);
		c.setSurface(Double.parseDouble(allParams[6]));
		c.setPopulation(Integer.parseInt(allParams[7]));
		c.setGovernment(allParams[8]);
		c.setLatitude(Double.parseDouble(allParams[9]));
		c.setLongitude(Double.parseDouble(allParams[10]));

		boolean succes = service.updateCountry(c);
		return Response.status(200).entity(succes).build();
	}

	@DELETE
	@Path("/delete/{code}")
	public Response deletecountry(@PathParam("code") String code) {
		WorldService service = ServiceProvider.getWorldService();
		return Response.status(200).entity(service.deleteCountry(code)).build();
	}

}
