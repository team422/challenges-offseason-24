package frc.robot.subsystems;

import org.littletonrobotics.junction.AutoLog;

public interface FlywheelIO {
  @AutoLog
  public static class FlywheelInputs {
    public double voltage;
    public double current;
    public double velocity;
  }

  public void updateInputs(FlywheelInputs inputs);

  public void setVoltage(double voltage);
}
