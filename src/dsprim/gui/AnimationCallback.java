/**
 * title: Animation Callback Interface
 * description: Allows other classes to alert the GUI to take action.
 * @author Dominic Evans
 * @date February 24, 2026
 * @version 1.0
 * @copyright 2026 Dominic Evans
 */

package dsprim.gui;

public interface AnimationCallback {
	void onStepCompleted();
	void onRunStart();
}