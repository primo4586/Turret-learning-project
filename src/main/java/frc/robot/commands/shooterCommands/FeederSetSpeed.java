// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.shooterCommands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.ShooterSubsystem;

import static frc.robot.Constants.ShooterConstants.*;

public class FeederSetSpeed extends InstantCommand {
  private final ShooterSubsystem shooterSubsystem = ShooterSubsystem.getInstance();
  // double speedPrecent;
  /** Creates a new FeederSetSpeed. */
  public FeederSetSpeed() {
    this.addRequirements(shooterSubsystem);
    // Use addRequirents() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    //set feeder speed from constants
    this.shooterSubsystem.setFeederSpeed(FeederSpeed);
  }
}
