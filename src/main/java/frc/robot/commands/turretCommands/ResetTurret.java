// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.turretCommands;

import edu.wpi.first.wpilibj2.command.InstantCommand;

import static frc.robot.Constants.Turret.*;
import frc.robot.subsystems.TurretSubsystem;

public class ResetTurret extends InstantCommand {
  private final TurretSubsystem turret = TurretSubsystem.getInstance();

  public ResetTurret() {
    this.addRequirements(turret);

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    turret.putTurretInAngle(TurretResetDegree);
  }
}