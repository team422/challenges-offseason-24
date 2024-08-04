package frc.robot.subsystems.intake.pivot;

import edu.wpi.first.math.geometry.Rotation2d;
import org.littletonrobotics.junction.AutoLog;

public interface PivotIO {
  @AutoLog
  public static class PivotInputs {
    public double angleRad;
    public double voltage;
    public double velocityRadPerSec;
  }

  public void updateInputs(PivotInputs inputs);

  public void setVoltage(double voltage);

  public double getVoltage();

  public double getVelocityRadPerSec();

  public Rotation2d getAngle();
}
