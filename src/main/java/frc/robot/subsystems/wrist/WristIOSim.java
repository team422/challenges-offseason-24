package frc.robot.subsystems.wrist;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.simulation.SingleJointedArmSim;

public class WristIOSim implements WristIO {
  private SingleJointedArmSim m_sim;
  private double m_voltage = 0.0;

  public WristIOSim() {
    DCMotor gearbox = DCMotor.getFalcon500(1);
    double gearing = 1.0;
    double armLengthMeters = 0.4;
    double moi = SingleJointedArmSim.estimateMOI(armLengthMeters, 5.5);
    double minAngleRads = Units.degreesToRadians(-180.0);
    double maxAngleRads = Units.degreesToRadians(180.0);
    boolean simulateGravity = false;
    double startingAngleRads = 0.0;
    m_sim =
        new SingleJointedArmSim(
            gearbox,
            gearing,
            moi,
            armLengthMeters,
            minAngleRads,
            maxAngleRads,
            simulateGravity,
            startingAngleRads);
  }

  @Override
  public void updateInputs(WristInputs inputs) {
    m_sim.update(0.02);

    inputs.voltage = m_voltage;
    inputs.angleRad = m_sim.getAngleRads();
    inputs.angularVelocityRPM =
        Units.radiansPerSecondToRotationsPerMinute(m_sim.getVelocityRadPerSec());
    inputs.current = m_sim.getCurrentDrawAmps();
  }

  @Override
  public void setVoltage(double voltage) {
    m_sim.setInputVoltage(voltage);
    m_voltage = voltage;
  }

  @Override
  public Rotation2d getAngle() {
    return Rotation2d.fromRadians(m_sim.getAngleRads());
  }
}
