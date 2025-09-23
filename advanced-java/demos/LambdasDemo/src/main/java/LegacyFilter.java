import java.util.ArrayList;
import java.util.List;

public class LegacyFilter {

    AddressCollection addresses = new AddressCollection();
    public void filterAddresses() {
        List<Address> addressesByCity = new ArrayList<>();
        for (Address a : addresses.addressList) {
            if (a.getCity().equalsIgnoreCase("Philadelphia")) {
                addressesByCity.add(a);
            }
        }

        List<Address> addressesByState = new ArrayList<>();
        for (Address a : addresses.addressList) {
            if (a.getState().equals("PA")) {
                addressesByState.add(a);
            }
        }

        List<Address> addressesByPostalCode = new ArrayList<>();
        for (Address a : addresses.addressList) {
            if (a.getPostalCode().equals("18031")) {
                addressesByPostalCode.add(a);
            }
        }

        List<Address> addressesByStateAndStreetName = new ArrayList<>();
        for (Address a : addresses.addressList) {
            if (a.getState().equals("PA")
                    && a.getStreet().contains("Jackson")) {
                addressesByStateAndStreetName.add(a);
            }
        }

        System.out.println("Completed Filters");
    }
}
