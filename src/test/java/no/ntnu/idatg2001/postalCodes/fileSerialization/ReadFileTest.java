package no.ntnu.idatg2001.postalCodes.fileSerialization;

import no.ntnu.idatg2001.postalCodes.components.PostalCodeRegistry;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class ReadFileTest {

    PostalCodeRegistry postalCodeRegistry;

    @BeforeAll
    static void setTest() {
        ReadFile.test = true;
    }

    @BeforeEach
    void initialize() {
        postalCodeRegistry = PostalCodeRegistry.getInstance();
        postalCodeRegistry.getList().clear();
    }

    @Test
    @DisplayName("Test if the import from a document works")
    void importFromDocumentTest() {
        assertEquals(0, postalCodeRegistry.getList().size());
        ReadFile.importFromFile(ReadType.PUBLIC);
        assertNotEquals(0, postalCodeRegistry.getList().size());
    }

    @Test
    @DisplayName("Test if import from empty document works")
    void importFromEmptyDocumentTest() {
        assertEquals(0, postalCodeRegistry.getList().size());
        ReadFile.importFromFile(ReadType.EMPTY);
        assertEquals(0, postalCodeRegistry.getList().size());
    }

    @AfterAll
    static void finish() {
        ReadFile.test = false;
    }

}
