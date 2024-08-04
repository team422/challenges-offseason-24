package frc.robot.subsystems.wrist;

import edu.wpi.first.math.geometry.Rotation2d;
import org.littletonrobotics.junction.AutoLog;

public interface WristIO {
  @AutoLog
  public static class WristInputs {
    public double angleRad;
    public double voltage;
    public double angularVelocityRPM;
    public double current;
  }

  public void updateInputs(WristInputs inputs);

  public void setVoltage(double voltage);

  public Rotation2d getAngle();
}
