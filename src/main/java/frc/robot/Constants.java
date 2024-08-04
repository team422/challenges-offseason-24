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
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.simulation.SingleJointedArmSim;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  public static final Mode currentMode = Mode.REAL;

  public static enum Mode {
    /** Running on a real robot. */
    REAL,

    /** Running a physics simulator. */
    SIM,

    /** Replaying from a log file. */
    REPLAY
  }

  public static final class IntakeConstants {
    public static final double kPivotP = 0.65;
    public static final double kPivotI = 0.0;
    public static final double kPivotD = 0.5;
    public static final PIDController pivotController =
        new PIDController(kPivotP, kPivotI, kPivotD);

    public static final double kPivotGearing = 1.0;
    public static final double kPivotLength = 0.21;
    public static final double kPivotMass = 4.0;
    public static final double kPivotJKgMetersSquared =
        SingleJointedArmSim.estimateMOI(kPivotLength, kPivotMass);
    public static final double kPivotMinAngle = Units.degreesToRadians(5);
    public static final double kPivotMaxAngle = Units.degreesToRadians(125);

    public static final double kRollerGearing = 1.0;
    public static final double kRollerJKgMetersSquared = 0.00075;
  }
}
