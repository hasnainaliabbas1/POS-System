
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import javax.swing.*;

public class ProductViewTest {

    @Test
    public void testProductViewComponents() {
        // Create an instance of ProductView
        ProductView productView = new ProductView();

        // Verify the frame properties
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(new JPanel());
        assertNotNull(frame, "Product Management frame should be created.");
        assertEquals("Product Management", frame.getTitle(), "Frame title should be 'Product Management'.");
        assertEquals(JFrame.EXIT_ON_CLOSE, frame.getDefaultCloseOperation(), "Frame default close operation should be EXIT_ON_CLOSE.");
        assertEquals(new java.awt.Dimension(1000, 700), frame.getSize(), "Frame size should be 1000x700.");

        // Check the table existence
        JTable productTable = productView.productTable;
        assertNotNull(productTable, "Product table should be created.");

        // Verify table properties
        assertEquals(9, productTable.getColumnCount(), "Product table should have 9 columns.");
        assertEquals("ID", productTable.getColumnName(0), "First column should be 'ID'.");
        assertEquals("Name", productTable.getColumnName(1), "Second column should be 'Name'.");
        assertEquals("Category", productTable.getColumnName(2), "Third column should be 'Category'.");
        assertEquals("Original Price", productTable.getColumnName(3), "Fourth column should be 'Original Price'.");
        assertEquals("Sale Price", productTable.getColumnName(4), "Fifth column should be 'Sale Price'.");
        assertEquals("Price Per Unit", productTable.getColumnName(5), "Sixth column should be 'Price Per Unit'.");
        assertEquals("Price Per Carton", productTable.getColumnName(6), "Seventh column should be 'Price Per Carton'.");
        assertEquals("Vendor ID", productTable.getColumnName(7), "Eighth column should be 'Vendor ID'.");
        assertEquals("Stock Quantity", productTable.getColumnName(8), "Ninth column should be 'Stock Quantity'.");

        // Check back button existence and properties
        JButton backButton = (JButton) frame.getContentPane().getComponent(1).getComponent(0);
        assertNotNull(backButton, "Back button should be created.");
        assertEquals("Back", backButton.getText(), "Back button text should be 'Back'.");
        assertEquals(java.awt.Color.RED, backButton.getBackground(), "Back button background color should be red.");
        assertEquals(java.awt.Color.WHITE, backButton.getForeground(), "Back button text color should be white.");
    }
}
