package frc.robot.subsystems.flywheel;

import frc.lib.advantagekit.LoggedIO;
import org.littletonrobotics.junction.AutoLog;

public interface FlywheelIO extends LoggedIO<FlywheelIO.FlywheelInputs> {
  @AutoLog
  public static class FlywheelInputs {
    public double curVelocity;
    public double curVoltage;
    public double curAmps;
  }

  public void setFlywheelVoltage(double voltage);
}
