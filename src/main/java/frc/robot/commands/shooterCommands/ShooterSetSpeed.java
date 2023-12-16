// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.shooterCommands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.ShooterSubsystem;

public class ShooterSetSpeed extends InstantCommand {
  /** Creates a new ShooterSetSpeed. */
  private final ShooterSubsystem shooterSubsystem = ShooterSubsystem.getInstance();
  double spped;

  public ShooterSetSpeed() {
    this.addRequirements(shooterSubsystem);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    // set shooter speed from constants
    this.shooterSubsystem.setShooterSpeed(spped);
  }
}
