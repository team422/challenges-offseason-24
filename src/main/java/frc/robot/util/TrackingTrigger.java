package frc.robot.util;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BooleanSupplier;

public class TrackingTrigger extends Trigger {
  // i cant believe i made this entire class just for one part of one challenge
  public static enum TriggerType {
    ON_TRUE,
    ON_FALSE,
    WHILE_TRUE,
    WHILE_FALSE
  }

  private Map<Command, List<TriggerType>> m_commands = new HashMap<>();

  public TrackingTrigger(BooleanSupplier condition) {
    super(condition);
  }

  public TrackingTrigger(Trigger trigger) {
    super(trigger);
  }

  @Override
  public Trigger onTrue(Command command) {
    List<TriggerType> triggerTypes = m_commands.get(command);
    if (triggerTypes == null) {
      triggerTypes = new ArrayList<>();
      m_commands.put(command, triggerTypes);
    }
    triggerTypes.add(TriggerType.ON_TRUE);
    return this;
  }

  @Override
  public Trigger onFalse(Command command) {
    List<TriggerType> triggerTypes = m_commands.get(command);
    if (triggerTypes == null) {
      triggerTypes = new ArrayList<>();
      m_commands.put(command, triggerTypes);
    }
    triggerTypes.add(TriggerType.ON_FALSE);
    return this;
  }

  @Override
  public Trigger whileTrue(Command command) {
    List<TriggerType> triggerTypes = m_commands.get(command);
    if (triggerTypes == null) {
      triggerTypes = new ArrayList<>();
      m_commands.put(command, triggerTypes);
    }
    triggerTypes.add(TriggerType.WHILE_TRUE);
    return this;
  }

  @Override
  public Trigger whileFalse(Command command) {
    List<TriggerType> triggerTypes = m_commands.get(command);
    if (triggerTypes == null) {
      triggerTypes = new ArrayList<>();
      m_commands.put(command, triggerTypes);
    }
    triggerTypes.add(TriggerType.WHILE_FALSE);
    return this;
  }

  public Map<Command, List<TriggerType>> getCommands() {
    return m_commands;
  }
}
