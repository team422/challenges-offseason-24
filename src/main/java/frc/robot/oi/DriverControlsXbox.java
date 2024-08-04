package frc.robot.oi;

import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.util.TrackingTrigger;

public class DriverControlsXbox implements DriverControls {
  private CommandXboxController m_controller;
  private TrackingTrigger m_runFlywheel;

  public DriverControlsXbox(int port) {
    m_controller = new CommandXboxController(port);
    m_runFlywheel = new TrackingTrigger(m_controller.a());
  }

  @Override
  public Trigger runFlywheel() {
    return m_runFlywheel;
  }
}
