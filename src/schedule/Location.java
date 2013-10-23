package schedule;

import com.google.gson.annotations.SerializedName;

public class Location {
	String additional;
	String city;
	String country;
	@SerializedName("country_abbr")
	String countryAbbr;
	String latitude;
	String longitude;
	String name;
	@SerializedName("postal_code")
	String postalCode;
	String state;
	@SerializedName("state_abbr")
	String stateAbbr;
	String street;

	public Location() {

	}
}
