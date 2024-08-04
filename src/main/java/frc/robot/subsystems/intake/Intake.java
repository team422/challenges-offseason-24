package frc.robot.subsystems.intake;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.subsystems.intake.pivot.PivotIO;
import frc.robot.subsystems.intake.pivot.PivotInputsAutoLogged;
import frc.robot.subsystems.intake.rollers.RollerIO;
import frc.robot.subsystems.intake.rollers.RollerInputsAutoLogged;

public class Intake extends SubsystemBase {
  private PivotIO m_pivotIO;
  private RollerIO m_rollerIO;
  public final PivotInputsAutoLogged m_pivotInputs;
  public final RollerInputsAutoLogged m_rollerInputs;

  private PIDController m_pivotController;

  public Intake(PivotIO pivotIO, RollerIO rollerIO, PIDController pivotController) {
    m_pivotIO = pivotIO;
    m_rollerIO = rollerIO;
    m_pivotController = pivotController;

    m_pivotInputs = new PivotInputsAutoLogged();
    m_rollerInputs = new RollerInputsAutoLogged();
  }

  @Override
  public void periodic() {
    m_pivotIO.updateInputs(m_pivotInputs);
    m_rollerIO.updateInputs(m_rollerInputs);

    double pivotOutput = m_pivotController.calculate(m_pivotInputs.angleRad);
    m_pivotIO.setVoltage(pivotOutput);
  }

  public void setRollerVoltage(double voltage) {
    m_rollerIO.setVoltage(voltage);
  }

  public void setPivotAngle(Rotation2d angle) {
    m_pivotController.setSetpoint(angle.getRadians());
  }
}
