/**
 * title: DSPrim Graph Solver
 * description: Simple entry class for the application.
 * @author Dominic Evans
 * @date February 25, 2026
 * @version 1.0
 * @copyright 2026 Dominic Evans
 */

package dsprim;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import dsprim.gui.DSPrimGUI;

public class DSPrim {
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		SwingUtilities.invokeLater(() -> {
			DSPrimGUI app = new DSPrimGUI();
			app.setVisible(true);
		});
	}
}