// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.HoodSubsystem;
import edu.wpi.first.wpilibj2.command.InstantCommand;

public class HoodPoseResetCommand extends InstantCommand {
  private final HoodSubsystem m_subsystem;
  HoodSubsystem subsystem = HoodSubsystem.getInstance();

  /** Creates a new HoodPoseReset. */
  public HoodPoseResetCommand() {
    m_subsystem = subsystem;
    addRequirements(subsystem);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_subsystem.HoodPoseReset();
  }
}
