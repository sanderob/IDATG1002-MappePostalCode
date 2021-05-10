package no.ntnu.idatg2001.postalCodes.components;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PostalCodeRegistryTest {

    PostalCodeRegistry registry;
    PostalCodeRegistry registry2;
    PostalCode postalCode;

    @BeforeEach
    void initialize() {
        registry2 = PostalCodeRegistry.getInstance();
        registry = PostalCodeRegistry.getInstance();
        registry.getList().clear();
        postalCode = new PostalCode("0595", "Oslo", "0301", "Tønsberg", "G");
    }

    @Test
    @DisplayName("Positive and negative tests verifies that the singleton instantiation is working, and that all objects are the same")
    void singletonInstantiationTest() {
        assertSame(registry, PostalCodeRegistry.getInstance());
        assertSame(registry, PostalCodeRegistry.getInstance());
    }

    @Test
    @DisplayName("Positive and negative test verifies that the getList-method works")
    void getListTest() {
        registry.addPostalCode(postalCode);
        assertSame(registry.getList(), registry2.getList());
        assertEquals(registry2.getList().size(), registry.getList().size());
        assertEquals(registry2.getList(), registry.getList());
        assertFalse(registry.getList().size() != (registry2.getList().size()));
    }

    @Test
    @DisplayName("Positive and negative test verifies that the add-method works")
    void addPostalCodeTest() {
        assertEquals(registry.getList().size(), 0);
        assertEquals(registry2.getList().size(), 0);
        registry.addPostalCode(postalCode);
        assertEquals(registry.getList().size(), 1);
        assertNotEquals(registry2.getList().size(), 0);
        registry2.addPostalCode(new PostalCode("0595", "Oslo", "0301", "Tønsberg", "G"));
        assertEquals(registry2.getList().size(), 2);
        assertNotEquals(registry.getList().size(), 1);
    }

    @Test
    @DisplayName("Positive and negative test verifies that the remove-method works")
    void removePostalCodeTest() {
        //Adds postalCode to the list
        registry.addPostalCode(postalCode);
        //Asserts same size on both lists
        assertEquals(registry.getList().size(), 1);
        assertEquals(registry2.getList().size(), 1);
        //Removes postalCode from one list
        registry.removePostalCode(postalCode);
        //Asserts same size on both lists
        assertEquals(registry.getList().size(), 0);
        assertNotEquals(registry2.getList().size(), 1);
        //Adds postalCode again
        registry2.addPostalCode(postalCode);
        //Asserts same size
        assertEquals(registry.getList().size(), 1);
        assertEquals(registry2.getList().size(), 1);
        //Removes postalCode from different registry-list
        registry2.removePostalCode(postalCode);
        //Asserts same size again, and proves they are the same object with the same list
        assertEquals(registry2.getList().size(), 0);
        assertNotEquals(registry.getList().size(), 1);
    }

    @Test
    @DisplayName("Positive and negative test verifies that the postalCodeExists-method workds")
    void postalCodeExists() {
        assertFalse(registry.postalCodeExists(postalCode));
        assertNotEquals(registry2.postalCodeExists(postalCode), true);
        registry.addPostalCode(postalCode);
        assertTrue(registry2.postalCodeExists(postalCode));
        assertNotEquals(registry.postalCodeExists(postalCode), false);
    }
}
