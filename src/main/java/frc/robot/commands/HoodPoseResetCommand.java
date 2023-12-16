// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.HoodSubsystem;
import edu.wpi.first.wpilibj2.command.Command;

public class HoodPoseResetCommand extends Command {
  private final HoodSubsystem m_subsystem;

  /** Creates a new HoodPoseReset. */
  public HoodPoseResetCommand(HoodSubsystem subsystem) {
    m_subsystem = subsystem;
    addRequirements(subsystem);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_subsystem.HoodPoseReset();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (m_subsystem.getHoodPose() == 0.0) {
      return true;
    }

    else {
      return false;
    }

  }
}
