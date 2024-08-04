package frc.robot.subsystems.intake;

import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.wpilibj.simulation.DCMotorSim;

public class IntakeIOSim implements IntakeIO {
  private DCMotorSim m_sim;
  private double m_rollerVoltage;

  public IntakeIOSim() {
    DCMotor gearbox = DCMotor.getNEO(1);
    double gearing = 1.0;
    double moi = 0.000075;

    m_sim = new DCMotorSim(gearbox, gearing, moi);
  }

  @Override
  public void updateInputs(IntakeInputs inputs) {
    m_sim.update(0.02);

    inputs.rollerVoltage = m_rollerVoltage;
    inputs.rollerVelocity = m_sim.getAngularVelocityRPM();
    inputs.rollerCurrent = m_sim.getCurrentDrawAmps();
  }

  @Override
  public void setRollerVoltage(double voltage) {
    m_rollerVoltage = voltage;
    m_sim.setInputVoltage(voltage);
  }

  @Override
  public double getRollerVoltage() {
    return m_rollerVoltage;
  }

  @Override
  public double getRollerVelocityRPM() {
    return m_sim.getAngularVelocityRPM();
  }
}
