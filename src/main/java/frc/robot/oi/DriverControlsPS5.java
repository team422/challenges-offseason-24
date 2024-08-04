package frc.robot.oi;

import edu.wpi.first.wpilibj2.command.button.CommandPS5Controller;
import edu.wpi.first.wpilibj2.command.button.Trigger;

public class DriverControlsPS5 implements DriverControls {
  private CommandPS5Controller m_controller;

  public DriverControlsPS5(int controllerPort) {
    m_controller = new CommandPS5Controller(controllerPort);
  }

  @Override
  public Trigger revFlywheelButton() {
    return m_controller.R2();
  }
}
