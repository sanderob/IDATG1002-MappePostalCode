package no.ntnu.idatg2001.postalCodes.components;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PostalCodeTest {

    PostalCode postalCode;

    @BeforeEach
    void initialize() {
        postalCode = new PostalCode("0595", "Oslo", "0301", "Tønsberg", "G");
    }

    @Test
    @DisplayName("Test verifies that the object is created correctly in the constructor")
    void postalCodeConstructorTest() {
        assertEquals("0595", postalCode.getPostalCodeNumber());
        assertEquals("Oslo", postalCode.getPostalArea());
        assertEquals("0301", postalCode.getMunicipalityCode());
        assertEquals("Tønsberg", postalCode.getMunicipalityName());
        assertEquals("G", postalCode.getCategory());
    }

    @Test
    @DisplayName("Test verifies that the toString method works")
    void toStringTest() {
        assertEquals("PostalCode{postalCodeNumber='0595', postalArea='Oslo', municipalityCode='0301', municipalityName='Tønsberg', category='G'}", postalCode.toString());
    }
}
