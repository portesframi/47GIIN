package main;

import com.formdev.flatlaf.FlatLightLaf;
import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import javax.swing.UIManager;
import vista.VistaLogin;

/** Asignatura Proyecto de ingenieria de software
 *
 * @author frami
 */
public class VIUProyecto {

    private static void personalizarLookAndFeel() {
        try {
            // Personalizar el color principal (color de acento)
            Color accentColor = new Color(102, 204, 255); // Azul claro
            UIManager.put("Component.focusColor", accentColor);

            // Personalizar la fuente predeterminada
            Font font = new Font("Segoe UI", Font.PLAIN, 14);
            UIManager.put("defaultFont", font);

            // Personalizar las líneas de las tablas
            Color tableLineColor = new Color(230, 230, 230); // Color de línea gris claro
            UIManager.put("Table.gridColor", tableLineColor);
            UIManager.put("Table.showHorizontalLines", true);
            UIManager.put("Table.showVerticalLines", true);

            // Personalizar el color de fondo de las celdas seleccionadas en las tablas
            Color selectionColor = new Color(51, 51, 51); // Color oscuro
            UIManager.put("Table.selectionBackground", selectionColor);

            // Personalizar el color de texto de las celdas seleccionadas en las tablas
            Color selectionTextColor = Color.white; // Blanco
            UIManager.put("Table.selectionForeground", selectionTextColor);

            // Personalizar el color de fondo de los botones
            Color buttonColor = new Color(240, 240, 240); // Color claro
            UIManager.put("Button.background", buttonColor);

            // Personalizar el color de texto de los botones
            Color buttonTextColor = new Color(51, 51, 51); // Color oscuro
            UIManager.put("Button.foreground", buttonTextColor);

            // Personalizar el estilo de la fuente del texto de los botones
            Font buttonFont = new Font("Segoe UI", Font.BOLD, 14);
            UIManager.put("Button.font", buttonFont);

            // Personalizar el color de texto de los botones deshabilitados
            Color disabledButtonTextColor = new Color(170, 170, 170); // Color de texto gris
            UIManager.put("Button.disabledText", disabledButtonTextColor);

            // Personalizar el color de texto de las etiquetas
            Color labelTextColor = new Color(51, 51, 51); // Color de texto oscuro
            UIManager.put("Label.foreground", labelTextColor);

            // Personalizar el estilo de la fuente de las etiquetas
            Font labelFont = new Font("Segoe UI", Font.PLAIN, 16);
            UIManager.put("Label.font", labelFont);

            // Establecer FlatLaf como el Look and Feel
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception e) {
            // Manejo de excepciones si ocurre algún error al configurar el Look and Feel
            e.printStackTrace();
        }
    }

    /**Main del sistema que presenta el login de acceso
     * 
     * @param args argumento
     * @throws IOException error
     */
    public static void main(String[] args) throws IOException {
        personalizarLookAndFeel();
        // Crear e iniciar la ventana principal
        VistaLogin vl = new VistaLogin();
        // ...
        vl.setVisible(true);
    }
}
