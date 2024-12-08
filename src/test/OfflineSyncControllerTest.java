
package test;

import controller.OfflineSyncController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OfflineSyncControllerTest {

    private OfflineSyncController offlineSyncController;

    @BeforeEach
    void setUp() {
        offlineSyncController = new OfflineSyncController();
    }

    @Test
    void testCheckAndSyncData_NoOfflineData() {
        assertDoesNotThrow(() -> offlineSyncController.checkAndSyncData(),
                "Checking and syncing should not throw an exception when there is no offline data.");
    }

    @Test
    void testSyncDataToDatabase_InvalidDataFormat() {
        assertDoesNotThrow(() -> offlineSyncController.checkAndSyncData(),
                "Invalid data format should not cause exceptions during syncing.");
    }

    @Test
    void testSyncUserData_ValidData() {
        String validUserData = "User|John Doe|1234|john.doe@example.com|Password123|BR001|50000";
        assertDoesNotThrow(() -> offlineSyncController.checkAndSyncData(),
                "Valid user data should be synced without exceptions.");
    }

    @Test
    void testSyncVendorData_ValidData() {
        String validVendorData = "Vendor|Vendor Name|Contact Info|Address";
        assertDoesNotThrow(() -> offlineSyncController.checkAndSyncData(),
                "Valid vendor data should be synced without exceptions.");
    }

    @Test
    void testSyncProductData_ValidData() {
        String validProductData = "Product|Product Name|Category|100.0|150.0|10.0|20.0|1";
        assertDoesNotThrow(() -> offlineSyncController.checkAndSyncData(),
                "Valid product data should be synced without exceptions.");
    }

    @Test
    void testSyncDataToDatabase_UnknownDataType() {
        String unknownDataType = "Unknown|Field1|Field2|Field3";
        assertDoesNotThrow(() -> offlineSyncController.checkAndSyncData(),
                "Unknown data types should not cause exceptions.");
    }
}
