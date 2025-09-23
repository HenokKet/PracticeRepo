import java.util.ArrayList;
import java.util.List;

public class AddressCollection {
    List<Address> addressList = new ArrayList<>();

    public AddressCollection() {
        this.addressList = getAddresses();
    }

    private List<Address> getAddresses(){
        List<Address> returnValue = new ArrayList<>();

        Address address = new Address();
        address.setCity("Philadelphia");
        address.setState("PA");
        address.setStreet("425 Jackson Street");
        address.setPostalCode("19148");
        addressList.add(address);

        address = new Address();
        address.setCity("Springfield");
        address.setState("PA");
        address.setStreet("505 Philmar Court");
        address.setPostalCode("19130");
        addressList.add(address);

        address = new Address();
        address.setCity("Allentown");
        address.setState("PA");
        address.setStreet("123 Hamilton Blvd.");
        address.setPostalCode("18031");
        addressList.add(address);

        return addressList;
    }
}


