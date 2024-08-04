// Copyright 2021-2024 FRC 6328
// http://github.com/Mechanical-Advantage
//
// This program is free software; you can redistribute it and/or
// modify it under the terms of the GNU General Public License
// version 3 as published by the Free Software Foundation or
// available in the root directory of this project.
//
// This program is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
// GNU General Public License for more details.

package frc.robot;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.Commands;
import frc.robot.oi.DriverControls;
import frc.robot.oi.DriverControlsPS5;
import frc.robot.subsystems.flywheel.Flywheel;
import frc.robot.subsystems.flywheel.FlywheelIONeo;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // Subsystems
  private Flywheel m_flywheel;

  // Controller
  private DriverControls m_driverControls;

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    configureSubsystems();
    configureControllers();
    configureButtonBindings();
  }

  private void configureSubsystems() {
    m_flywheel = new Flywheel(new FlywheelIONeo(3), new PIDController(0.3, 0.01, 0.0));
  }

  private void configureControllers() {
    m_driverControls = new DriverControlsPS5(0);
  }

  private void configureButtonBindings() {
    m_driverControls
        .revFlywheelButton()
        .whileTrue(Commands.runOnce(() -> m_flywheel.setDesiredVelocity(5000)))
        .whileFalse(Commands.runOnce(() -> m_flywheel.setDesiredVelocity(0)));
  }
}
